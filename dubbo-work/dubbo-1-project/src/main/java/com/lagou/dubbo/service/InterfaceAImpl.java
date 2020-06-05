package com.lagou.dubbo.service;

import com.lagou.api.InterfaceA;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

@Service(interfaceClass = InterfaceA.class)
public class InterfaceAImpl implements InterfaceA {
    @Override
    public void sayHello() {
        String ip = RpcContext.getContext().getAttachment("ip");
        System.out.println("InterfaceA 终端IP：" + ip + ", Hello");
    }
}
