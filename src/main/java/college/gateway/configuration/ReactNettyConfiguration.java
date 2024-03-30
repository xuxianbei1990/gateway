package college.gateway.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ReactorResourceFactory;

/**
 * User: EDY
 * Date: 2024/3/23
 * Time: 13:30
 * Version:V1.0
 */
@Configuration
public class ReactNettyConfiguration {

    @Value("${reactor.netty.ioWorkerCount}")
    private String ioWorkerCount;

    @Bean
    public ReactorResourceFactory reactorResourceHandlerMapping() {
        System.setProperty("reactor.netty.ioWorkerCount", ioWorkerCount);
        return new ReactorResourceFactory();
    }

}
