package com.lagou.web.controller;

import com.lagou.api.InterfaceA;
import com.lagou.api.InterfaceB;
import com.lagou.web.util.ThreadLocalHolder;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MyController {
    @Reference
    private InterfaceA interfaceA;

    @Reference
    private InterfaceB interfaceB;

    @GetMapping("/a")
    public String callInterfaceA(HttpServletRequest request) {
        ThreadLocalHolder.setIp(request.getRemoteAddr());
        // 调用dubbo接口
        interfaceA.sayHello();
        return "SUCCESS A";
    }

    @GetMapping("/b")
    public String callInterfaceB(HttpServletRequest request) {
        ThreadLocalHolder.setIp(request.getRemoteAddr());
        // 调用dubbo接口
        interfaceB.sayHello();
        return "SUCCESS B";
    }
}
