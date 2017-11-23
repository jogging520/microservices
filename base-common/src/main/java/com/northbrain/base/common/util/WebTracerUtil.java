package com.northbrain.base.common.util;

import com.northbrain.base.common.model.bo.Hints;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 类名：TracerUtil
 * 用途：web日志跟踪器，利用spring的aop打印web servlet日志。
 * @author Jiakun
 * @version 1.0
 */
@Aspect
@Component
@Order(0)
public class WebTracerUtil
{
    private Logger logger = Logger.getLogger(getClass());
    private ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<Long>();

    @Pointcut("execution(* com.northbrain..*Controller.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void invokeLog(){}

    @Before("invokeLog()")
    public void doBefore(JoinPoint joinPoint)
    {
        //设置请求时间
        startTimeThreadLocal.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        logger.info(Hints.HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST);
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();


        // 记录详细请求内容
        logger.info(Hints.HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST_URL + httpServletRequest.getRequestURL().toString());
        logger.info(Hints.HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST_HTTP_METHOD + httpServletRequest.getMethod());
        logger.info(Hints.HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST_IP + httpServletRequest.getRemoteAddr());
        logger.info(Hints.HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST_CLASS_METHOD + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info(Hints.HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST_ARGS + Arrays.toString(joinPoint.getArgs()));
        //获取所有参数：
        Enumeration<String> enumeration = httpServletRequest.getParameterNames();
        while(enumeration.hasMoreElements())
        {
            String paraName = enumeration.nextElement();
            logger.info(Hints.HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST_PARA_NAME + paraName + ": " + httpServletRequest.getParameter(paraName));
        }
    }

    @AfterReturning("invokeLog()")
    public void  doAfterReturning()
    {
        // 处理完请求，返回内容
        long startTime = startTimeThreadLocal.get();
        long finishTime = System.currentTimeMillis();

        logger.info(Hints.HINT_BUSINESS_COMMON_HTTP_SERVLET_REQUEST_AFTER_RETURNING + String.valueOf(finishTime-startTime));
    }
}
