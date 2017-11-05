package com.northbrain.base.common.util;

import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Parameters;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ParameterConfigException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.bo.Names;

import org.apache.log4j.Logger;

/**
 * 类名：通用工具类
 * 用途：包括休眠、系统退出、判断是否有剩余内存空间、打印使用方法等，提供静态方法供全局使用。
 * @author Jiakun
 * @version 1.0
 */
public class CommonUtil
{
    private static Logger logger = Logger.getLogger(CommonUtil.class);    

    /**
     * 方法：获取时间间隔（不同类型的时间间隔不等）
     * @param name 名称
     * @return 时间间隔
     */
    private static long getInteval(Names name) throws Exception
    {
        if(name == null)
        {
        	logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "name");
        	throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        
        String syncIntervalMs = Parameters.get(name);

        if(syncIntervalMs == null || syncIntervalMs.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + name.getName());
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        return Long.parseLong(syncIntervalMs);
    }

    /**
     * 方法：休眠当前线程
     * @param name 间隔时长名称
     */
    public static void sleeping(Names name) throws Exception
    {
    	if(name == null)
        {
        	logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "name");
        	throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
    	
    	long inteval = getInteval(name);

        if(inteval == 0L)
            inteval = Constants.BUSINESS_COMMON_DEFAULT_INTEVALMS;

        logger.debug(Hints.HINT_SYSTEM_PROCESS_THREAD_SLEEP + String.valueOf(inteval));
        
        Thread.sleep(inteval);
    }

    /**
     * 方法：系统退出
     */
    public static void shutdown()
    {
        throw new RuntimeException(Hints.HINT_SYSTEM_PROCESS_SYSTEM_SHUTDOWN.getDesc());
    }

    /**
     * 方法：打印系统使用方法，方法在ZooKeeper中配置。
     */
    public static void printUsage() throws Exception
    {
        String systemUsagePrompt = Parameters.get(Names.SYSTEM_USAGE_PROMPT);

        if(systemUsagePrompt == null || systemUsagePrompt.equals("") || Long.parseLong(systemUsagePrompt) <= 0L)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "SYSTEM_USAGE_PROMPT");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        logger.info(systemUsagePrompt);
    }

    /**
     * 方法：判断当前JVM是否有剩余的内存供使用，最新内存量在ZooKeeper中配置
     * @return 是否有空余
     */
    public static boolean isFree() throws Exception
    {
        long freeMemory;

        String systemMinFreeMemory = Parameters.get(Names.SYSTEM_MIN_FREE_MEMORY);

        if(systemMinFreeMemory == null || systemMinFreeMemory.equals("") || Long.parseLong(systemMinFreeMemory) <= 0L)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "SYSTEM_MIN_FREE_MEMORY");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        freeMemory = Runtime.getRuntime().freeMemory();
        logger.info(Hints.HINT_SYSTEM_MONITOR_FREE_MEMORY_INFO + String.valueOf(freeMemory));

        return freeMemory > Long.parseLong(systemMinFreeMemory);
    }
}
