package com.northbrain.general.gateway.filter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.northbrain.base.common.model.bo.Constants;

/**
 * 类名：调用关系过滤类
 * 用途：对请求的uri进行过滤，添加或者过滤部分属性
 * @author Jiakun
 * @version 1.0
 */
@Component
public class CallGraphFilter extends ZuulFilter
{
    private static Logger logger = Logger.getLogger(CallGraphFilter.class);

    @Autowired
    private Tracer tracer;

    /**
     * 方法：设置过滤类型为事后过滤
     * @return 过滤类型
     */
    @Override
    public String filterType()
    {
        return FilterConstants.POST_TYPE;
    }

    /**
     * 方法：执行优先级
     * 优先级为0，数字越大，优先级越低
     * @return 执行优先级序号
     */
    @Override
    public int filterOrder()
    {
        return Constants.SYSTEM_GATEWAY_FILTER_CALL_GRAPH_ORDER;
    }

    /**
     * 方法：是否执行该过滤器，此处为true，说明需要过滤
     *
     * @return 是否执行该过滤器
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // TODO 这部分要完善。
    @Override
    public Object run() {
        tracer.addTag("operator","forezp");
        System.out.print(tracer.getCurrentSpan().traceIdString());
        return null;
    }
}
