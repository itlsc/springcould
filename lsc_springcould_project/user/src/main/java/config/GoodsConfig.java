package config;

import com.lsc.rule.LscjavaRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoodsConfig {

    //采用自定义的负载轮询策略
    @Bean
    public IRule iRule(){
        return new LscjavaRule();
    }


}
