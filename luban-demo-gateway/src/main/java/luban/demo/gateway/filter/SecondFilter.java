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
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

public class SecondFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        boolean intercept=false;
        if (intercept){
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
        return chain.filter(exchange);

    }
    @Override
    public int getOrder() {
        return -10;
    }
}
