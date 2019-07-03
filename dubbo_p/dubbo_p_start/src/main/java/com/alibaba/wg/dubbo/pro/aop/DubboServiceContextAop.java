package com.alibaba.wg.dubbo.pro.aop;

import com.alibaba.dubbo.rpc.RpcContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wg
 * @date : 2019/7/3
 */

@Aspect
@Component
public class DubboServiceContextAop {

    @Pointcut("execution(* com.alibaba.wg.dubbo.pro.service.*(..))")
    public void serviceApi() {
    }

    @Before("serviceApi()")
    public void dubboContext(JoinPoint jp) {
        Map<String, String> context = new HashMap<>();
        context.put("traceId", UUID.randomUUID().toString());
        // todo you want do
        RpcContext.getContext().setAttachments(context);
    }
}
