package com.northbrain.base.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.vo.orch.OrchAccessControlVO;
import com.northbrain.base.gateway.service.IGatewayService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 类名：JsonWebToken过滤类
 * 用途：对请求的uri进行过滤，校验JWT
 * @author Jiakun
 * @version 1.0
 */
@Component
public class JsonWebTokenFilter extends ZuulFilter
{
    private static Logger logger = Logger.getLogger(JsonWebTokenFilter.class);
    private final IGatewayService gatewayService;

    @Autowired
    public JsonWebTokenFilter(IGatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    /**
     * 方法：设置过滤类型为事前过滤
     * @return 过滤类型
     */
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

        logger.debug(httpServletRequest);
        //如果请求的URI中包含登入、注册的服务，直接通过。
        for(String filter: Constants.BUSINESS_COMMON_JWT_REQUEST_URI_FILTER)
        {
            if(httpServletRequest.getRequestURI().contains(filter))
            {
                // 对该请求进行路由
                requestContext.setSendZuulResponse(true);
                // 应答码设置为成功
                requestContext.setResponseStatusCode(Constants.BUSINESS_COMMON_JWT_RESPONSE_SUCCESS);
                // 设值，让下一个Filter看到上一个Filter的状态
                requestContext.set(Constants.BUSINESS_COMMON_JWT_RESPONSE_FILTER_RESULT, true);
                return null;
            }
        }

        //获取认证信息
        String authorization = httpServletRequest.getHeader(Constants.BUSINESS_COMMON_JWT_HEADER_PARAM_AUTHORIZATION);

        if(authorization == null || authorization.equals("") ||
                !authorization.startsWith(Constants.BUSINESS_COMMON_JWT_HEADER_PARAM_AUTHORIZATION_STARTER) ||
                gatewayService == null)
        {
            // 对该请求进行路由
            requestContext.setSendZuulResponse(false);
            // 应答码设置为未认证
            requestContext.setResponseStatusCode(Constants.BUSINESS_COMMON_JWT_RESPONSE_UNAUTHORIZED);
            // 设值，让下一个Filter看到上一个Filter的状态
            requestContext.set(Constants.BUSINESS_COMMON_JWT_RESPONSE_FILTER_RESULT, false);
            return null;
        }

        //调用编排服务对token进行校验
        OrchAccessControlVO orchAccessControlVO = new OrchAccessControlVO();
        orchAccessControlVO.setJsonWebToken(authorization);
        orchAccessControlVO.setUri(httpServletRequest.getRequestURI());
        orchAccessControlVO.setHttpMethod(httpServletRequest.getMethod());
        orchAccessControlVO.setDescription(Constants.BUSINESS_COMMON_SERVICE_GATEWAY_DESCRIPTION);

        if(gatewayService.readAccessControl(orchAccessControlVO))
        {
            requestContext.setSendZuulResponse(true);
            requestContext.setResponseStatusCode(Constants.BUSINESS_COMMON_JWT_RESPONSE_SUCCESS);
            requestContext.set(Constants.BUSINESS_COMMON_JWT_RESPONSE_FILTER_RESULT, true);
            return null;
        }

        // 默认情况下拒绝
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(Constants.BUSINESS_COMMON_JWT_RESPONSE_UNAUTHORIZED);
        requestContext.set(Constants.BUSINESS_COMMON_JWT_RESPONSE_FILTER_RESULT, false);
        return null;
    }
}
