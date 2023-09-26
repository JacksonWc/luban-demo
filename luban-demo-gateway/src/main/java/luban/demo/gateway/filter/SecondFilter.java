package luban.demo.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class SecondFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("进入到secondFilter,但是什么都没做");
        //过滤链调用后续过滤逻辑
        Mono<Void> mono = chain.filter(exchange);
        return mono;
    }
    @Override
    public int getOrder() {
        return -10;
    }
}
