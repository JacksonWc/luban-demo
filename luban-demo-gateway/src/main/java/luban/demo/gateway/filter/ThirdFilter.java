package luban.demo.gateway.filter;

import com.alibaba.fastjson.JSON;
import luban.demo.gateway.result.Result;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Supplier;

/**
 * 需求: 模拟JWT解析 解析成功 存储认证对象,放行,解析失败,拦截返回失败响应
 *
 * 请求进入网关,在网关编写过滤器.过滤逻辑,判断请求参数中是否存在token这个值.
 *
 * 如果存在,请判断满足 10位以内的整数正则.
 *
 * 如果满足上述条件 过滤器正常放行.
 *
 * 如果不满足上述条件,使用response返回一个result对象 提示message: 认证失败.
 */
@Component
public class ThirdFilter implements GlobalFilter, Ordered {




    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //10位以内的整数正则
        String regex="^\\d{1,10}$";
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        List<String> tokenValueList = queryParams.get("token");
        if (tokenValueList!=null){
            //从请求参数中获取到的token对应的值
            String  tokenValue=tokenValueList.get(0);
            //int parseInt = Integer.parseInt(tokenValue);
            boolean isMatch = tokenValue.matches(regex);
            if (!isMatch){
                //从exchange获取响应
                ServerHttpResponse response = exchange.getResponse();
                //因为需要响应数据 result对象json字符串 产生中文乱码的问题.
                //只需要在响应头中添加一个 applicaiton/json;charset=utf-8 ContentType
                //拿到响应头
                HttpHeaders headers = response.getHeaders();
                //设置新定义的响应头 contentype
                headers.setContentType(MediaType.APPLICATION_JSON);

                Result<String> result=new Result<>();
                result.setCode("200");
                result.setMessage("你违反了规则");
                result.setData(null);


                //进入这个过滤器,使用响应返回result对象数据
                //获取一个publisher对象 定义输出数据,定义输出格式
                Mono<DataBuffer> writeData = Mono.fromSupplier(new Supplier<DataBuffer>() {
                    public DataBuffer get() {
                        //封装result到响应数据
                        String json = JSON.toJSONString(result);
                        DataBufferFactory dataBufferFactory = response.bufferFactory();
                        //使用工厂类包裹json字符串
                        return dataBufferFactory.wrap(json.getBytes(StandardCharsets.UTF_8));
                    }
                });


                return response.writeWith(writeData);
            }

        }


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -20;
    }
}
