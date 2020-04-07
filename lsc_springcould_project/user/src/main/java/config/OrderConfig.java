package config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

     //采用轮询的ribbon负载均衡策略
    @Bean
    public IRule iRule(){
        return new RandomRule();
    }
}
