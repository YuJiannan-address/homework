package com.lagou.dubbo.service;

import com.lagou.api.InterfaceA;
import com.lagou.api.TestTP;
import org.apache.dubbo.config.annotation.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service(interfaceClass = TestTP.class)
public class TestTPImpl implements TestTP {
    /**
     * 随机获取 0 ~ s 的整数
     */
    private int randomInt(int s) {
        return (int) (Math.random() * s);
    }

    @Override
    public void methodA() {
        try {
            int s = randomInt(100);
            TimeUnit.MILLISECONDS.sleep(s);
            System.out.println("methodA 休眠了 " + s + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void methodB() {
        try {
            int s = randomInt(100);
            TimeUnit.MILLISECONDS.sleep(s);
            System.out.println("methodB 休眠了 " + s + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void methodC() {
        try {
            int s = randomInt(100);
            TimeUnit.MILLISECONDS.sleep(s);
            System.out.println("methodC 休眠了 " + s + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
