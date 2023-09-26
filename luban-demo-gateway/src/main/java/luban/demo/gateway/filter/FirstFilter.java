package luban.demo.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;


@Component
public class FirstFilter implements GlobalFilter, Ordered {
    /** 网关中 编写过滤逻辑的方法
     * @param exchange webflux 底层给我们封装的一个对象 包装了请求和响应数据
     * @param chain 过滤链,贯穿整个web容器的一个对象，用于不同过滤器之间的连接
     * @return Mono SpringWebFlux 返回结果    对比SpringMVC有 Model ModelAndView
     */



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("进入到firstFilter,但是什么都没做");
        //System.out.println("进入到firstFilter,但是什么都没做");
        //request对象 包含请求url地址 path 请求query参数 请求头数据
        //从exchange拿到请求对象
        ServerHttpRequest request = exchange.getRequest();
        //请求路径
        RequestPath path = request.getPath();
        URI uri = request.getURI();
        System.out.println("获取uri:"+uri.toString());
        System.out.println("获取path:"+path.toString());
        //请求参数 localhost:10000/abc?name=haha&age=18&girlFriends[0]=王翠花&girlFriends[1]=刘翠花
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        //key 是string value List<String>
        //name=haha name=List<String>{"haha"}
        //name[0]=haha&name[1]=gaga 不会像springmvc一样整理成list数据,只会单独封装
        //query在当前方法的value值永远都是一个元素的list对象.
        List<String> name = queryParams.get("name");
        System.out.println("name值元素个数:"+name.size());
        System.out.println("数据分别是:"+name.toString());
        //jwt解析,获取的headers Authorization
        //获取头数据,打印展示
        HttpHeaders headers = request.getHeaders();
        //从所有头中,获取一个名称的头
        List<String> headerValues = headers.get("Accept");
        System.out.println("当前头的元素个数:"+headerValues.size());
        System.out.println(headerValues);
        //request其它api
        request.getId();
        //能解析客户端ip
        request.getRemoteAddress();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
