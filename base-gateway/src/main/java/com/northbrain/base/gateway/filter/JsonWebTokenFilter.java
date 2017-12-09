package com.northbrain.base.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.util.JsonWebTokenUtil;
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
        return false;
    }

    @Override
    public Object run()
    {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
/*

        if (!httpServletRequest.getRequestURI().contains("login"))
        {
            String accessToken = httpServletRequest.getHeader("Authorization");

            if (accessToken == null || accessToken.isEmpty()) {
                Cookie[] cookies = httpServletRequest.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (CoreConstants.X_TOKEN.equals(cookie.getName())) {
                            accessToken = cookie.getValue();
                            break;
                        }
                    }
                }
            }
        }


            String authorization = httpServletRequest.getHeader("Authorization");

        if ((authorization != null) && (authorization.length() > 7))
        {
            String header = authorization.substring(0, 6).toLowerCase();

            if (header.compareTo("bearer") == 0)
            {
                authorization = authorization.substring(7, authorization.length());

                if (JsonWebTokenUtil.parseJWT(authorization, "ThisIsASecret") != null)
                {
                    // 对该请求进行路由
                    requestContext.setSendZuulResponse(true);
                    requestContext.setResponseStatusCode(200);
                    // 设值，让下一个Filter看到上一个Filter的状态
                    requestContext.set("isSuccess", true);
                    return null;
                }
                else
                {
                    requestContext.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
                    requestContext.setResponseStatusCode(401);// 返回错误码
                    requestContext.setResponseBody("{\"result\":\"username is not correct!\"}");// 返回错误内容
                    requestContext.set("isSuccess", false);
                    return null;
                }
            }

        }
        */

return null;
    }
}
