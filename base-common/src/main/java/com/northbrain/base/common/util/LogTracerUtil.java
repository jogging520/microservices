package com.northbrain.base.common.util;

import com.northbrain.base.common.model.bo.Hints;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 类名：LogTracerUtil
 * 用途：日志跟踪器，利用spring的aop打印方法调用的开始时间和结束时间，以及耗时。
 * @author Jiakun
 * @version 1.0
 */
@Aspect
@Component
@Order(1)
public class LogTracerUtil
{
    private Logger logger = Logger.getLogger(getClass());

    /**
     * 定义一个切入点.
     * 解释下：
     *
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(* com.northbrain..*.*(..))")
    public void invokeLog(){}

    /**
     * 方法：在被调用方法的前后打印时间及耗时
     * @param proceedingJoinPoint 处理连接点
     * @return 被调用方法的处理结果
     * @throws Throwable 异常
     */
    @Around("invokeLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        long startTime = System.currentTimeMillis();
        
        logger.info(Hints.HINT_SYSTEM_PROCESS_BEGIN_INVOKE_METHOD + proceedingJoinPoint.toString());

        Object result = proceedingJoinPoint.proceed();

        long finishTime = System.currentTimeMillis();
        logger.info(Hints.HINT_SYSTEM_PROCESS_END_INVOKE_METHOD + proceedingJoinPoint.toString() + Hints.HINT_SYSTEM_PROCESS_INVOKE_METHOD_COST + (finishTime-startTime));

        return result;
    }
}
