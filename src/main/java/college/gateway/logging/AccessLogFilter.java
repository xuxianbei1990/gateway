package college.gateway.logging;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * User: EDY
 * Date: 2024/3/30
 * Time: 15:28
 * Version:V1.0
 */
@Slf4j
@Component
public class AccessLogFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        //tode: traceid
        AccessLog gatewayLog = new AccessLog();
        gatewayLog.setRequestMethod(request.getMethod().name());
        gatewayLog.setRequestUrl(request.getURI().getRawPath());
        log.info("[writeAccessLog][网关日志：{}]", JSONObject.toJSONString(gatewayLog));
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
