package com.northbrain.base.launcher.session;

import java.util.*;

import org.apache.log4j.Logger;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ParameterConfigException;
import com.northbrain.base.common.model.bo.*;
import com.northbrain.base.common.util.CommonUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * 类名：Redis会话工厂类
 * 用途：创建、回收Zookeeper会话等
 * @author Jiakun
 * @version 1.0
 *
 */
public class RedisSessionFactory
{
    private static Logger logger = Logger.getLogger(RedisSessionFactory.class);
    private static final Map<String, ThreadLocal<Jedis>> threadLocals = new HashMap<>();
    private static Set<String> sentinels = new HashSet<>();
    private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    private static final Map<String, JedisSentinelPool> jedisSentinelPools = new HashMap<>();

    /**
     * 方法：创建构造器实例
     * STORAGE_REDIS_SENTINEL_POOL配置多个服务器，达到高可用的目的。
     * Redis服务器的Master、Slaver由Sentinel集群管理，客户端连接Sentinel集群，由Sentinel集群管理各消息队列。
     * 每个分片的名称为：storageRedisSentinelPoolName+分片sharding
     * @param sharding 分片
     */
    private static synchronized void build(String sharding) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        String storageRedisServers = Parameters.get(Names.STORAGE_REDIS_SERVER);
        String storageRedisPoolMaxTotal = Parameters.get(Names.STORAGE_REDIS_POOL_MAX_TOTAL);
        String storageRedisPoolMaxIdle = Parameters.get(Names.STORAGE_REDIS_POOL_MAX_IDLE);
        String storageRedisPoolMinIdle = Parameters.get(Names.STORAGE_REDIS_POOL_MIN_IDLE);
        String storageRedisPoolMaxWaitMillis = Parameters.get(Names.STORAGE_REDIS_POOL_MAX_WAIT_MILLIS);
        String storageRedisPoolTestOnBorrow = Parameters.get(Names.STORAGE_REDIS_POOL_TEST_ON_BORROW);
        String storageRedisPoolTestOnReturn = Parameters.get(Names.STORAGE_REDIS_POOL_TEST_ON_RETURN);
        String storageRedisPoolTestOnCreate = Parameters.get(Names.STORAGE_REDIS_POOL_TEST_ON_CREATE);
        String storageRedisPoolJmxEnabled = Parameters.get(Names.STORAGE_REDIS_POOL_JMX_ENABLED);
        String storageRedisPoolTestWhileIdle = Parameters.get(Names.STORAGE_REDIS_POOL_TEST_WHILE_IDLE);
        String storageRedisPoolLifo = Parameters.get(Names.STORAGE_REDIS_POOL_LIFO);
        String storageRedisPoolTimeBetweenEvictionRunsMillis = Parameters.get(Names.STORAGE_REDIS_POOL_TIME_BETWEEN_EVICTION_RUNS_MILLIS);
        String storageRedisPoolNumTestsPerEvictionRun = Parameters.get(Names.STORAGE_REDIS_POOL_NUM_TESTS_PER_EVICTION_RUN);
        String storageRedisPoolMinEvictableIdleTimeMillis = Parameters.get(Names.STORAGE_REDIS_POOL_MIN_EVICTABLE_IDLE_TIME_MILLIS);
        String storageRedisPoolBlockWhenExhausted = Parameters.get(Names.STORAGE_REDIS_POOL_BLOCK_WHEN_EXHAUSTED);
        String storageRedisPoolSoftMinEvictableIdleTimeMillis = Parameters.get(Names.STORAGE_REDIS_POOL_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS);
        String storageRedisSentinelPool = Parameters.get(Names.STORAGE_REDIS_SENTINEL_POOL);
        String storageRedisSentinelPoolName = Parameters.get(Names.STORAGE_REDIS_SENTINEL_POOL_NAME);
        String businessCommonCommandEndSymbol = Parameters.get(Names.BUSINESS_COMMON_COMMAND_END_SYMBOL);

        if(storageRedisServers == null || storageRedisServers.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_SERVER");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolMaxTotal == null || storageRedisPoolMaxTotal.equals("") ||
                Integer.parseInt(storageRedisPoolMaxTotal) < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_MAX_TOTAL");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolMaxIdle == null || storageRedisPoolMaxIdle.equals("") ||
                Integer.parseInt(storageRedisPoolMaxIdle) < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_MAX_IDLE");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolMinIdle == null || storageRedisPoolMinIdle.equals("") ||
                Integer.parseInt(storageRedisPoolMinIdle) < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_MIN_IDLE");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolMaxWaitMillis == null || storageRedisPoolMaxWaitMillis.equals("") ||
                Long.parseLong(storageRedisPoolMaxWaitMillis) < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_MAX_WAIT_MILLIS");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolTestOnBorrow == null || storageRedisPoolTestOnBorrow.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_TEST_ON_BORROW");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolTestOnReturn == null || storageRedisPoolTestOnReturn.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_TEST_ON_RETURN");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolTestOnCreate == null || storageRedisPoolTestOnCreate.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_TEST_ON_CREATE");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolJmxEnabled == null || storageRedisPoolJmxEnabled.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_JMX_ENABLED");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolTestWhileIdle == null || storageRedisPoolTestWhileIdle.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_TEST_WHILE_IDLE");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolLifo == null || storageRedisPoolLifo.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_LIFO");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolTimeBetweenEvictionRunsMillis == null || storageRedisPoolTimeBetweenEvictionRunsMillis.equals("") || Long.parseLong(storageRedisPoolTimeBetweenEvictionRunsMillis) < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_TIME_BETWEEN_EVICTION_RUNS_MILLIS");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolNumTestsPerEvictionRun == null || storageRedisPoolNumTestsPerEvictionRun.equals("") ||
                Integer.parseInt(storageRedisPoolNumTestsPerEvictionRun) < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_NUM_TESTS_PER_EVICTION_RUN");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolMinEvictableIdleTimeMillis == null || storageRedisPoolMinEvictableIdleTimeMillis.equals("") || Long.parseLong(storageRedisPoolMinEvictableIdleTimeMillis) < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_MIN_EVICTABLE_IDLE_TIME_MILLIS");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolBlockWhenExhausted == null || storageRedisPoolBlockWhenExhausted.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_BLOCK_WHEN_EXHAUSTED");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisPoolSoftMinEvictableIdleTimeMillis == null || storageRedisPoolSoftMinEvictableIdleTimeMillis.equals("") ||
                Long.parseLong(storageRedisPoolSoftMinEvictableIdleTimeMillis) < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_POOL_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisSentinelPool == null || storageRedisSentinelPool.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "STORAGE_REDIS_SENTINEL_POOL");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(storageRedisSentinelPoolName == null || storageRedisSentinelPoolName.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "storageRedisSentinelPoolName");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(businessCommonCommandEndSymbol == null || businessCommonCommandEndSymbol.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_COMMAND_END_SYMBOL");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
        
        logger.debug(Hints.HINT_STORAGE_REDIS_BUILD_BUILDER);

        sentinels.clear();

        Collections.addAll(sentinels, storageRedisServers.split(businessCommonCommandEndSymbol));

        if(jedisPoolConfig == null)
        {
            jedisPoolConfig = new JedisPoolConfig();
        }
        else
        {
            jedisPoolConfig.setMaxTotal(Integer.parseInt(storageRedisPoolMaxTotal));
            jedisPoolConfig.setMaxIdle(Integer.parseInt(storageRedisPoolMaxIdle));
            jedisPoolConfig.setMinIdle(Integer.parseInt(storageRedisPoolMinIdle));
            jedisPoolConfig.setMaxWaitMillis(Long.parseLong(storageRedisPoolMaxWaitMillis));
            jedisPoolConfig.setTestOnBorrow(Constants.matchBoolean(storageRedisPoolTestOnBorrow));
            jedisPoolConfig.setTestOnReturn(Constants.matchBoolean(storageRedisPoolTestOnReturn));
            jedisPoolConfig.setTestOnCreate(Constants.matchBoolean(storageRedisPoolTestOnCreate));
            jedisPoolConfig.setTestWhileIdle(Constants.matchBoolean(storageRedisPoolTestWhileIdle));
            jedisPoolConfig.setJmxEnabled(Constants.matchBoolean(storageRedisPoolJmxEnabled));
            jedisPoolConfig.setLifo(Constants.matchBoolean(storageRedisPoolLifo));
            jedisPoolConfig.setTimeBetweenEvictionRunsMillis(Long.parseLong(storageRedisPoolTimeBetweenEvictionRunsMillis));
            jedisPoolConfig.setNumTestsPerEvictionRun(Integer.parseInt(storageRedisPoolNumTestsPerEvictionRun));
            jedisPoolConfig.setMinEvictableIdleTimeMillis(Long.parseLong(storageRedisPoolMinEvictableIdleTimeMillis));
            jedisPoolConfig.setBlockWhenExhausted(Constants.matchBoolean(storageRedisPoolBlockWhenExhausted));
            jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(Long.parseLong(storageRedisPoolSoftMinEvictableIdleTimeMillis));
        }

        if(jedisSentinelPools.get(sharding) == null)
        {
            JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(storageRedisSentinelPoolName+sharding, sentinels, jedisPoolConfig);
            jedisSentinelPools.put(sharding, jedisSentinelPool);
        }
    }

    /**
     * 方法：关闭连接池
     * @param sharding 分片
     * @throws ArgumentInputException  参数输入异常
     */
    public static void close(String sharding) throws ArgumentInputException
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        
        logger.debug(Hints.HINT_STORAGE_REDIS_CLOSE_POOL);

        if(jedisSentinelPools.get(sharding) == null)
        {
            jedisSentinelPools.get(sharding).destroy();
        }
    }

    /**
     * 方法：获取Redis会话，通过这个会话与Redis服务器交互。
     * 连接服务最大尝试maxRetries次，只要有一次连接上，那么当前线程就用这个会话。
     * @param sharding 分片
     * @return Redis会话
     */
    public static Jedis getSession(String sharding) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        Jedis jedis;
        int retries = 0;

        String businessCommonMaxRetries = Parameters.get(Names.BUSINESS_COMMON_MAX_RETRIES);

        if(businessCommonMaxRetries == null || businessCommonMaxRetries.equals("") || Integer.parseInt(businessCommonMaxRetries) < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_MAX_RETRIES");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        if(threadLocals.get(sharding) == null)
        {
            threadLocals.put(sharding, new ThreadLocal<>());
        }

        jedis = threadLocals.get(sharding).get();

        while(jedis == null && retries < Integer.parseInt(businessCommonMaxRetries))
        {
            logger.debug(Hints.HINT_STORAGE_REDIS_CONNECT_SESSION);

            if(jedisSentinelPools.get(sharding) == null)
            {
                build(sharding);
            }

            jedis = (jedisSentinelPools.get(sharding) != null) ? jedisSentinelPools.get(sharding).getResource() : null;
            threadLocals.get(sharding).set(jedis);

            if(jedis != null)
            {
                break;
            }

            retries++;
            CommonUtil.sleeping();
        }

        return jedis;
    }

    /**
     * 方法：关闭会话
     * @param sharding 分片
     * @throws ArgumentInputException 参数输入异常
     */
    public static void closeSession(String sharding) throws ArgumentInputException
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_STORAGE_REDIS_CLOSE_SESSION);

        Jedis jedis = threadLocals.get(sharding).get();

        threadLocals.get(sharding).set(null);

        if(jedis != null)
            jedis.close();
    }
}
