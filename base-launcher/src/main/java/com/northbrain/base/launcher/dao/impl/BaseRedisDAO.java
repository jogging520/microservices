package com.northbrain.base.launcher.dao.impl;

import com.northbrain.base.launcher.dao.IBaseRedisDAO;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.launcher.session.RedisSessionFactory;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.RedisSessionException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 类名：基础Redis的DAO接口的实现类
 * 用途：存取Redis数据的基础方法，主要是队列和散列。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class BaseRedisDAO implements IBaseRedisDAO
{
    private static Logger logger = Logger.getLogger(BaseRedisDAO.class);

    /**
     * 方法：获取Redis会话
     * @param sharding 分片
     * @return Redis会话
     * @throws ArgumentInputException 参数输入异常
     */
    private Jedis getSession(String sharding) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        return RedisSessionFactory.getSession(sharding);
    }

    /**
     * 方法：右入队列
     * @param sharding 分片
     * @param key Key值
     * @param value 取值
     * @return 是否保存成功
     */
    @Override
    public boolean rpush(String sharding, String key, String value) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(key == null || key.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "key");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(value == null || value.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "value");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession(sharding) == null)
        {
            logger.error(Errors.ERROR_STORAGE_REDIS_SESSION_NULL);
            throw new RedisSessionException(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }

        if(getSession(sharding).rpush(key, value) > 0)
        {
            logger.debug(Hints.HINT_STORAGE_REDIS_SET_LIST_DATA_SUCCESS + key + ", " + value);
            return true;
        }

        return false;
    }

    /**
     * 方法：右入队列（批量）
     * @param sharding 分片
     * @param key Key值
     * @param values 多个取值
     * @return 是否保存成功
     */
    @Override
    public boolean rpush(String sharding, String key, String... values) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(key == null || key.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "key");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        for(String value: values)
        {
            if(value == null || value.equals(""))
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "values");
                throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
            }
        }

        if(getSession(sharding) == null)
        {
            logger.error(Errors.ERROR_STORAGE_REDIS_SESSION_NULL);
            throw new RedisSessionException(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }

        if(getSession(sharding).rpush(key, values) > 0)
        {
            logger.debug(Hints.HINT_STORAGE_REDIS_SET_LIST_DATA_SUCCESS + key + ", " + Arrays.toString(values));
            return true;
        }

        return false;
    }

    /**
     * 方法：左入队列
     * @param sharding 分片
     * @param key Key值
     * @param value 取值
     * @return 是否保存成功
     */
    @Override
    public boolean lpush(String sharding, String key, String value) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(key == null || key.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "key");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(value == null || value.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "value");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession(sharding) == null)
        {
            logger.error(Errors.ERROR_STORAGE_REDIS_SESSION_NULL);
            throw new RedisSessionException(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }

        if(getSession(sharding).lpush(key, value) > 0)
        {
            logger.debug(Hints.HINT_STORAGE_REDIS_SET_LIST_DATA_SUCCESS + key + ", " + value);
            return true;
        }

        return false;
    }

    /**
     * 方法：左入队列（批量）
     * @param sharding 分片
     * @param key Key值
     * @param values 多个取值
     * @return 是否保存成功
     */
    @Override
    public boolean lpush(String sharding, String key, String... values) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(key == null || key.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "key");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        for(String value: values)
        {
            if(value == null || value.equals(""))
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "values");
                throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
            }
        }

        if(getSession(sharding) == null)
        {
            logger.error(Errors.ERROR_STORAGE_REDIS_SESSION_NULL);
            throw new RedisSessionException(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }

        if(getSession(sharding).lpush(key, values) > 0)
        {
            logger.debug(Hints.HINT_STORAGE_REDIS_SET_LIST_DATA_SUCCESS + key + ", " + Arrays.toString(values));
            return true;
        }

        return false;
    }

    /**
     * 方法：右出队列
     * @param sharding 分片
     * @param key Key值
     * @return Key在Redis的当前值
     */
    @Override
    public String lpop(String sharding, String key) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(key == null || key.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "key");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession(sharding) == null)
        {
            logger.error(Errors.ERROR_STORAGE_REDIS_SESSION_NULL);
            throw new RedisSessionException(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }

        String value = getSession(sharding).lpop(key);

        logger.debug(Hints.HINT_STORAGE_REDIS_GET_LIST_DATA_SUCCESS + key + ", " + value);

        return value;
    }

    /**
     * 方法：左出队列
     * @param sharding 分片
     * @param key Key值
     * @return Key在Redis的当前值
     */
    @Override
    public String rpop(String sharding, String key) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(key == null || key.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "key");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession(sharding) == null)
        {
            logger.error(Errors.ERROR_STORAGE_REDIS_SESSION_NULL);
            throw new RedisSessionException(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }

        String value = getSession(sharding).rpop(key);

        logger.debug(Hints.HINT_STORAGE_REDIS_GET_LIST_DATA_SUCCESS + key + ", " + value);

        return value;
    }

    /**
     * 方法：队列长度
     * @param sharding 分片
     * @param key Key值
     * @return Key在Redis的当前值
     */
    @Override
    public long llen(String sharding, String key) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(key == null || key.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "key");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession(sharding) == null)
        {
            logger.error(Errors.ERROR_STORAGE_REDIS_SESSION_NULL);
            throw new RedisSessionException(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }

        long len = getSession(sharding).llen(key);

        logger.debug(key + Hints.HINT_STORAGE_REDIS_GET_LIST_DATA_LENGTH + len);

        return len;
    }

    /**
     * 方法：设置散列值
     * @param sharding 分片
     * @param key 键值
     * @param field 域名
     * @param value 取值
     * @return 是否设置成功
     */
    @Override
    public boolean hset(String sharding, String key, String field, String value) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(key == null || key.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "key");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(field == null || field.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "field");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(value == null || value.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "value");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession(sharding) == null)
        {
            logger.error(Errors.ERROR_STORAGE_REDIS_SESSION_NULL);
            throw new RedisSessionException(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }

        if(getSession(sharding).hset(key, field, value) > 0L)
        {
            logger.debug(Hints.HINT_STORAGE_REDIS_SET_HASH_DATA_SUCCESS + key + "," + field + "," + value);
            return true;
        }

        return false;
    }

    /**
     * 方法：设置散列值（批量）
     * @param sharding 分片
     * @param key 键值
     * @param hash filed和value组成的键值对
     * @return 是否设置成功
     */
    @Override
    public boolean hmset(String sharding, String key, Map<String, String> hash) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(key == null || key.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "key");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(hash == null || hash.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "hash");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession(sharding) == null)
        {
            logger.error(Errors.ERROR_STORAGE_REDIS_SESSION_NULL);
            throw new RedisSessionException(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }

        if(getSession(sharding).hmset(key, hash) != null)
        {
            logger.debug(Hints.HINT_STORAGE_REDIS_SET_HASH_DATA_SUCCESS + key + "," + hash);
            return true;
        }

        return false;
    }

    /**
     * 方法：读取散列值
     * @param sharding 分配
     * @param key 键值
     * @param field 域名
     * @return value值
     */
    @Override
    public String hget(String sharding, String key, String field) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(key == null || key.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "key");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(field == null || field.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "field");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession(sharding) == null)
        {
            logger.error(Errors.ERROR_STORAGE_REDIS_SESSION_NULL);
            throw new RedisSessionException(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }

        String value = getSession(sharding).hget(key, field);

        logger.debug(Hints.HINT_STORAGE_REDIS_GET_HASH_DATA_SUCCESS + key + ", " + field + ", " + value);

        return value;
    }

    /**
     * 方法：读取散列值（批量）
     * @param sharding 分片
     * @param key 键值
     * @param fields 域名组
     * @return value值组
     */
    @Override
    public List<String> hmget(String sharding, String key, String... fields) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(key == null || key.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "key");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        for(String field: fields)
        {
            if(field == null || field.equals(""))
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "field");
                throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
            }
        }

        if(getSession(sharding) == null)
        {
            logger.error(Errors.ERROR_STORAGE_REDIS_SESSION_NULL);
            throw new RedisSessionException(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }

        List<String> values = getSession(sharding).hmget(key, fields);

        logger.debug(Hints.HINT_STORAGE_REDIS_GET_HASH_DATA_SUCCESS + key + ", " + Arrays.toString(fields) + ", " + values);

        return values;
    }

    /**
     * 方法：判断散列值是否存在
     * @param sharding 分配
     * @param key 键值
     * @param field 域名
     * @return 是否存在
     */
    @Override
    public boolean hexists(String sharding, String key, String field) throws Exception
    {
        if(sharding == null || sharding.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sharding");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(key == null || key.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "key");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(field == null || field.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "field");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession(sharding) == null)
        {
            logger.error(Errors.ERROR_STORAGE_REDIS_SESSION_NULL);
            throw new RedisSessionException(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }

        boolean isExists = getSession(sharding).hexists(key, field);

        if(isExists)
            logger.debug(Hints.HINT_STORAGE_REDIS_HASH_DATA_EXISTS + key + ", " + field);
        else
            logger.debug(Hints.HINT_STORAGE_REDIS_HASH_DATA_NOTEXISTS + key + ", " + field);

        return isExists;
    }
}
