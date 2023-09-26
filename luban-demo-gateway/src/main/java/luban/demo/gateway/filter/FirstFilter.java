package luban.demo.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


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
        //过滤链调用后续过滤逻辑
        Mono<Void> mono = chain.filter(exchange);
        return mono;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
