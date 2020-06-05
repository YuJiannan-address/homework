package com.lagou.web;

import com.lagou.web.service.ConsumerComponent;
import com.lagou.web.util.TPHelper;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TPMain {
    private static Executor executor = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        // 获取消费者组件
        ConsumerComponent consumerComponent = context.getBean(ConsumerComponent.class);
        // 开启一个单独的线程用于打印TP90、TP99
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    TPHelper.calcAndShow();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        for (; ; ) {
            executor.execute(() -> {
                consumerComponent.testMethodA();
            });
            executor.execute(() -> {
                consumerComponent.testMethodB();
            });
            executor.execute(() -> {
                consumerComponent.testMethodC();
            });
        }

    }

    @Configuration
    @PropertySource("classpath:/client.properties")
    @ComponentScan(basePackages = "com.lagou")
    @EnableDubbo
    static class ConsumerConfiguration {

    }
}

