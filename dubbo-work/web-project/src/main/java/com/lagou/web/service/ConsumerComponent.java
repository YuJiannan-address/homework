package com.lagou.web.service;

import com.lagou.api.TestTP;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class ConsumerComponent {
    private Executor executor = Executors.newFixedThreadPool(22);
    @Reference(interfaceClass = TestTP.class, loadbalance = "roundrobin")
    private TestTP testTP;

    public void testMethodA() {
        executor.execute(() -> testTP.methodA());
    }

    public void testMethodB() {
        executor.execute(() -> testTP.methodB());

    }

    public void testMethodC() {
        executor.execute(() -> testTP.methodC());

    }

}
