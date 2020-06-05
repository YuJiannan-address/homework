package com.lagou.web.dubbo.filter;

import com.lagou.web.util.ThreadLocalHolder;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;


@Activate(group = {CommonConstants.CONSUMER})
public class TransportIPFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String ip = ThreadLocalHolder.getIp();
        RpcContext.getContext().setAttachment("ip", ip);
        Result res = invoker.invoke(invocation);
        return res;
    }
}
