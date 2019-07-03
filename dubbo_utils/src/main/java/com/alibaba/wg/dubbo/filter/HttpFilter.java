package com.alibaba.wg.dubbo.filter;

import com.alibaba.dubbo.rpc.RpcContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : wanggang
 * @description : <一句话描述类的作用/>
 * @email : wg
 * @date : 2019/7/3
 */
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        chain.doFilter(request, httpServletResponse);
        String traceId = RpcContext.getContext().getAttachment("traceId");
        httpServletResponse.setHeader("x-trace-id", traceId);
    }

    @Override
    public void destroy() {

    }
}
