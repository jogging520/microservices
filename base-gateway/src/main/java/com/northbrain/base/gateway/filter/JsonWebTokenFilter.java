package com.northbrain.base.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Constants;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class JsonWebTokenFilter extends ZuulFilter
{
    private static Logger logger = Logger.getLogger(JsonWebTokenFilter.class);

    @Override
    public String filterType()
    {
        return BaseType.FILTERTYPE.pre.name();
    }

    /**
     * 方法：执行优先级
     * 优先级为0，数字越大，优先级越低
     * @return 执行优先级序号
     */
    @Override
    public int filterOrder()
    {
        return 0;
    }

    /**
     * 方法：是否执行该过滤器，此处为true，说明需要过滤
     * @return 是否执行该过滤器
     */
    @Override
    public boolean shouldFilter()
    {
        return true;
    }

    @Override
    public Object run()
    {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();

        logger.debug("");
        //如果请求的URI中包含登入、注册的服务，直接通过。
        for(String filter: Constants.BUSINESS_COMMON_JWT_REQUEST_URI_FILTER)
        {
            if(httpServletRequest.getRequestURI().contains(filter))
            {
                requestContext = setSuccessRequestContext(requestContext);
                return null;
            }
        }

        //获取认证信息
        String authorization = httpServletRequest.getHeader(Constants.BUSINESS_COMMON_JWT_HEADER_PARAM_AUTHORIZATION);

        if(authorization == null || authorization.equals("") ||
                !authorization.startsWith(Constants.BUSINESS_COMMON_JWT_HEADER_PARAM_AUTHORIZATION_STARTER))
        {
            requestContext = setUnauthorizedRequestContext(requestContext);
            return null;
        }

        //调用编排服务对token进行校验

        return null;
    }

    /**
     * 方法：设置成功应答上下文
     * @param requestContext 请求上下文
     * @return 设置后的请求上下文
     */
    private RequestContext setSuccessRequestContext(RequestContext requestContext)
    {
        // 对该请求进行路由
        requestContext.setSendZuulResponse(true);
        // 应答码设置为成功
        requestContext.setResponseStatusCode(Constants.BUSINESS_COMMON_JWT_RESPONSE_SUCCESS);
        // 设值，让下一个Filter看到上一个Filter的状态
        requestContext.set(Constants.BUSINESS_COMMON_JWT_RESPONSE_FILTER_RESULT, true);

        return requestContext;
    }

    /**
     * 方法：设置未鉴权成功上下文
     * @param requestContext 请求上下文
     * @return 设置后的请求上下文
     */
    private RequestContext setUnauthorizedRequestContext(RequestContext requestContext)
    {
        // 对该请求进行路由
        requestContext.setSendZuulResponse(false);
        // 应答码设置为成功
        requestContext.setResponseStatusCode(Constants.BUSINESS_COMMON_JWT_RESPONSE_UNAUTHORIZED);
        // 设值，让下一个Filter看到上一个Filter的状态
        requestContext.set(Constants.BUSINESS_COMMON_JWT_RESPONSE_FILTER_RESULT, false);

        return requestContext;
    }
}
