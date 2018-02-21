package com.northbrain.base.launcher.session;

import com.northbrain.base.common.exception.ParameterConfigException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.bo.Names;
import com.northbrain.base.common.model.bo.Parameters;
import com.northbrain.base.common.util.CommonUtil;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorFrameworkFactory.Builder;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;

/**
 * 类名：Zookeeper会话工厂类
 * 用途：创建、回收Zookeeper会话等
 * @author Jiakun
 * @version 1.0
 *
 */
public class ZooKeeperSessionFactory
{
    private static Logger logger = Logger.getLogger(ZooKeeperSessionFactory.class);
    private static final ThreadLocal<CuratorFramework> threadLocal = new ThreadLocal<>();	                //会话threadlocal
    private static Builder builder = null;				                                                    //构建器
    private static RetryPolicy retryPolicy = null; 		                                                    //重试策略

    /**
     * 方法：创建构造器实例
     * STORAGE_ZOOKEEPER_SERVERS配置多个服务器，随机轮询连接，不同的进程随机连接不同的服务器，分担服务端压力。
     */
    private static synchronized void build() throws Exception
    {
        String businessCommonProjectName = Parameters.get(Names.BUSINESS_COMMON_PROJECT_NAME);
    	String storageZookeeperBaseSleepTimeMs = Parameters.get(Names.STORAGE_ZOOKEEPER_BASE_SLEEP_TIME_MS);
        String storageZookeeperConnectMaxRetries = Parameters.get(Names.STORAGE_ZOOKEEPER_CONNECT_MAX_RETRIES);
        String storageZookeeperSessionTimeoutms = Parameters.get(Names.STORAGE_ZOOKEEPER_SESSION_TIMEOUT_MS);
        String storageZookeeperDomainNamespace = Parameters.get(Names.STORAGE_ZOOKEEPER_DOMAIN_NAMESPACE);
        String storageZookeeperServers = Parameters.get(Names.STORAGE_ZOOKEEPER_SERVERS_ADDRESS);
        
        if(businessCommonProjectName == null || businessCommonProjectName.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_PROJECT_NAME");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
        
        if(storageZookeeperBaseSleepTimeMs == null || storageZookeeperBaseSleepTimeMs.equals("") || Integer.parseInt(storageZookeeperBaseSleepTimeMs) < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_ZOOKEEPER_BASE_SLEEP_TIME_MS");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageZookeeperConnectMaxRetries == null || storageZookeeperConnectMaxRetries.equals("") || Integer.parseInt(storageZookeeperConnectMaxRetries) < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_ZOOKEEPER_CONNECT_MAX_RETRIES");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageZookeeperSessionTimeoutms == null || storageZookeeperSessionTimeoutms.equals("") || Integer.parseInt(storageZookeeperSessionTimeoutms) < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_ZOOKEEPER_SESSION_TIMEOUT_MS");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageZookeeperDomainNamespace == null || storageZookeeperDomainNamespace.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_ZOOKEEPER_DOMAIN_NAMESPACE_NAME");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageZookeeperServers == null || storageZookeeperServers.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_ZOOKEEPER_SERVERS_ADDRESS");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }   
        
        //项目的名称是在环境变量中配置
        storageZookeeperDomainNamespace = Constants.STORAGE_ZOOKEEPER_PROJECT_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR +
        		businessCommonProjectName + Constants.BUSINESS_COMMON_NODE_SEPARATOR +
        		storageZookeeperDomainNamespace;
        

        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_CONNECT_SERVER + storageZookeeperServers);
        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_DOMAIN_NAMESPACE + storageZookeeperDomainNamespace);

        if(retryPolicy == null)
        {
            retryPolicy = new ExponentialBackoffRetry(Integer.parseInt(storageZookeeperBaseSleepTimeMs), 
            		Integer.parseInt(storageZookeeperConnectMaxRetries),
            		Integer.parseInt(storageZookeeperBaseSleepTimeMs)*Integer.parseInt(storageZookeeperConnectMaxRetries));

        }

        if(builder == null)
        {
            builder = CuratorFrameworkFactory.builder()
                    .connectString(storageZookeeperServers)
                    .sessionTimeoutMs(Integer.parseInt(storageZookeeperSessionTimeoutms))
                    .retryPolicy(retryPolicy)
                    .namespace(storageZookeeperDomainNamespace);            
        }
    }

    /**
     * 方法：获取ZooKeeper会话，通过这个会话与Zookeeper服务器交互。
     * 连接服务最大尝试businessCommonMaxRetries次，只要有一次连接上，那么当前线程就用这个会话。
     * @return ZooKeeper会话
     */
    public static CuratorFramework getSession() throws Exception
    {
        CuratorFramework curatorFramework;
        int retries = 0;

        String businessCommonMaxRetries = Parameters.get(Names.BUSINESS_COMMON_MAX_RETRIES);

        if(businessCommonMaxRetries == null || businessCommonMaxRetries.equals("") || Integer.parseInt(businessCommonMaxRetries) < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_MAX_RETRIES");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        curatorFramework = threadLocal.get();

        while(curatorFramework == null && retries < Integer.parseInt(businessCommonMaxRetries))
        {
            if(builder == null)
            {
                build();
            }

            curatorFramework = (builder != null) ? builder.build() : null;
           
            if(curatorFramework != null)
            {
            	curatorFramework.start();
            	
            	logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_CONNECT_SESSION);
            	
            	curatorFramework.blockUntilConnected();
            	threadLocal.set(curatorFramework);
                
                break;
            }

            retries++;
            CommonUtil.sleeping();
        }

        return curatorFramework;
    }

    /**
     * 方法：关闭会话
     */
    public static void closeSession()
    {
        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_CLOSE_SESSION);

        CuratorFramework curatorFramework = threadLocal.get();

        threadLocal.set(null);

        if(curatorFramework != null)
            curatorFramework.close();
    }

    /**
     * 方法：获取ZooKeeper构建器
     * @return ZooKeeper构建器
     */
    public static Builder getBuilder()
    {
        return builder;
    }
}
