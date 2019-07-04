package com.alibaba.wg.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.*;

import java.util.UUID;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wg
 * @date : 2019/7/3
 */
@Activate(group = {Constants.CONSUMER,Constants.PROVIDER},order = -9999)
public class GlobalTraceFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = RpcContext.getContext().getAttachment("traceId");
        if(!StringUtils.isBlank(traceId)) {
            RpcContext.getContext().setAttachment("traceId",traceId);
        }else { // 第一次发起调用
            RpcContext.getContext().setAttachment("traceId", UUID.randomUUID().toString());
        }
        return invoker.invoke(invocation);
    }
}
