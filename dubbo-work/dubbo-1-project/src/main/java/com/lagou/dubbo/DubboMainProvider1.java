package com.lagou.dubbo;

import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

public class DubboMainProvider1 {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        System.in.read();
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "com.lagou.dubbo")
    @PropertySource("classpath:/provider1.properties")
    static class ProviderConfiguration {
        @Bean
        public RegistryConfig registryConfig() {
            RegistryConfig registryConfig = new RegistryConfig();
            registryConfig.setAddress("zookeeper://127.0.0.1:2181");
            return registryConfig;
        }
    }
}
