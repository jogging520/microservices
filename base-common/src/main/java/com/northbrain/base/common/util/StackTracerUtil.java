package com.northbrain.base.common.util;

import com.northbrain.base.common.model.bo.Errors;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 类名：StackTracerUtil
 * 用途：跟踪栈打印信息，将栈调用信息打印到日志中。默认情况下输出到标准Out设备中，提供静态方法供全局使用。
 * @author Jiakun
 * @version 1.0
 */
public class StackTracerUtil
{
    private static Logger logger = Logger.getLogger(StackTracerUtil.class);

    /**
     * 方法：获取异常Exception详细信息
     * @param exception 异常
     * @return 异常信息转换成字符串
     */
    public static String getExceptionInfo(Exception exception)
    {
        StringWriter stringWriter;
        PrintWriter printWriter;

        try
        {
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);

            exception.printStackTrace(printWriter);

            return "\r\n" + stringWriter.toString() + "\r\n";
        }
        catch(Exception error)
        {
            logger.error(Errors.ERROR_SYSTEM_PROCESS_EXCEPTION_CATCH);
            logger.error(error);
        }

        return Errors.ERROR_SYSTEM_PROCESS_EXCEPTION_CATCH.getDesc();
    }

    /**
     * 方法：获取异常Throwable详细信息
     * @param throwable 异常
     * @return 异常信息转换成字符串
     */
    public static String getExceptionInfo(Throwable throwable)
    {
        StringWriter stringWriter;
        PrintWriter printWriter;

        try
        {
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);

            throwable.printStackTrace(printWriter);

            return "\r\n" + stringWriter.toString() + "\r\n";
        }
        catch(Exception exception)
        {
            logger.error(Errors.ERROR_SYSTEM_PROCESS_EXCEPTION_CATCH);
            logger.error(exception);
        }

        return Errors.ERROR_SYSTEM_PROCESS_EXCEPTION_CATCH.getDesc();
    }
}
