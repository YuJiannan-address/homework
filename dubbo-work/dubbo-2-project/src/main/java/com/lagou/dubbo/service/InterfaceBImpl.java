package com.lagou.dubbo.service;

import com.lagou.api.InterfaceA;
import com.lagou.api.InterfaceB;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

@Service(interfaceClass = InterfaceB.class)
public class InterfaceBImpl implements InterfaceB {
    @Override
    public void sayHello() {
        String ip = RpcContext.getContext().getAttachment("ip");
        System.out.println("InterfaceB 终端IP：" + ip + ", Hello");
    }
}
