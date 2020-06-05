package com.lagou.web.dubbo.filter;

import com.lagou.web.util.TPHelper;
import com.lagou.web.util.ThreadLocalHolder;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Activate(group = {CommonConstants.CONSUMER})
public class TPMonitorFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result res;
        if (invocation.getMethodName().startsWith("method")) {
            long startTm = System.currentTimeMillis();
            invocation.getMethodName();
            res = invoker.invoke(invocation);
            // 将接口调用时间数据放入集合
            TPHelper.add(invocation.getMethodName(), System.currentTimeMillis() - startTm);
        } else {
            res = invoker.invoke(invocation);
        }
        return res;
    }
}