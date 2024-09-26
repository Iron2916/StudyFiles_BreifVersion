package com.iron.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RefreshScope   // 支持配置动态刷新
public class RestyTemplateConfig {

    @Bean
    @LoadBalanced   // 负载平衡
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
