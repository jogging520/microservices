package com.northbrain.base.common.util;

import com.northbrain.base.common.model.bo.Parameters;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.exception.ParameterConfigException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.bo.Names;

import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类名：基础缓冲池类
 * 用途：提供ZooKeeper缓存和生产任务运行的线程，提供静态方法供全局使用。
 * @author Jiakun
 * @version 1.0
 */
public class ThreadPoolUtil
{
    private static Logger logger = Logger.getLogger(ThreadPoolUtil.class);

    private static ExecutorService cacheExecutorService = null;	//用作ZooKeeper缓存的线程池
    private static ExecutorService taskExecutorService = null;	//用作生产任务运行的线程池

    /**
     * 方法：建立ZooKeeper缓存的线程池
     */
    private static synchronized void buildCacheExecutorService() throws Exception
    {
        if(cacheExecutorService != null)
        {
        	logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "cacheExecutorService");
        	throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        String storageZookeeperCacheThreadPoolNumber = Parameters.get(Names.STORAGE_ZOOKEEPER_CACHE_THREAD_POOL_NUMBER);

        if(storageZookeeperCacheThreadPoolNumber == null || storageZookeeperCacheThreadPoolNumber.equals("") || Integer.parseInt(storageZookeeperCacheThreadPoolNumber) < 1)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_ZOOKEEPER_CACHE_THREAD_POOL_NUMBER");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        logger.debug(Hints.HINT_SYSTEM_PROCESS_ESTABLISH_THREAD_POOL);

        if(cacheExecutorService == null)
        {
            cacheExecutorService = Executors.newFixedThreadPool(Integer.parseInt(storageZookeeperCacheThreadPoolNumber));
        }
    }

    /**
     * 方法：建立生产任务运行的线程池
     */
    private static synchronized void buildTaskExecutorService() throws Exception
    {
        if(taskExecutorService != null)
        {
        	logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "taskExecutorService");
        	throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        String businessCommonTaskThreadPoolNumber = Parameters.get(Names.BUSINESS_COMMON_TASK_THREAD_POOL_NUMBER);

        if(businessCommonTaskThreadPoolNumber == null || businessCommonTaskThreadPoolNumber.equals("") || Integer.parseInt(businessCommonTaskThreadPoolNumber) < 1)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_TASK_THREAD_POOL_NUMBER");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        logger.debug(Hints.HINT_SYSTEM_PROCESS_ESTABLISH_THREAD_POOL);

        if(taskExecutorService == null)
        {
            taskExecutorService = Executors.newFixedThreadPool(Integer.parseInt(businessCommonTaskThreadPoolNumber));
        }
    }

    /**
     * 方法：在缓存线程池中执行当前线程，超过线程池数后将阻塞等待。
     * @param executor 执行线程
     */
    public static void executeCache(Runnable executor) throws Exception
    {
        if(executor == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "executor");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        String businessCommonCacheThreadPoolNumber = Parameters.get(Names.BUSINESS_COMMON_CACHE_THREAD_POOL_NUMBER);

        if(businessCommonCacheThreadPoolNumber == null || businessCommonCacheThreadPoolNumber.equals("") || Integer.parseInt(businessCommonCacheThreadPoolNumber) < 1)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_CACHE_THREAD_POOL_NUMBER");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        buildTaskExecutorService();

        if(taskExecutorService == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "taskExecutorService");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }
        
        logger.debug(Hints.HINT_SYSTEM_PROCESS_THREAD_POOL_EXECUTE_TASK);

        cacheExecutorService.execute(executor);
    }

    /**
     * 方法：获取缓存线程池
     * @return 缓存线程池
     */
    public static ExecutorService getCacheExecutorService() throws Exception
    {
        if(cacheExecutorService == null)
            buildCacheExecutorService();

        return cacheExecutorService;
    }

    /**
     * 方法：获取任务线程池
     * @return 任务线程池
     */
    public static ExecutorService getTaskExecutorService() throws Exception
    {
        if(taskExecutorService == null)
            buildTaskExecutorService();

        return taskExecutorService;
    }
}
