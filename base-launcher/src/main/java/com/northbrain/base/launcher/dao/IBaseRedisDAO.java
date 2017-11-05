package com.northbrain.base.launcher.dao;

import java.util.List;
import java.util.Map;

/**
 * 类名：基础Redis的DAO接口
 * 用途：存取Redis数据的基础方法
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IBaseRedisDAO
{
    /**
     * 方法：右入队列
     * @param sharding 分片
     * @param key Key值
     * @param value 取值
     * @return 是否保存成功
     */
    public boolean rpush(String sharding, String key, String value) throws Exception;

    /**
     * 方法：右入队列（批量）
     * @param sharding 分片
     * @param key Key值
     * @param values 多个取值
     * @return 是否保存成功
     */
    public boolean rpush(String sharding, String key, String... values) throws Exception;

    /**
     * 方法：左入队列
     * @param sharding 分片
     * @param key Key值
     * @param value 取值
     * @return 是否保存成功
     */
    public boolean lpush(String sharding, String key, String value) throws Exception;

    /**
     * 方法：左入队列（批量）
     * @param sharding 分片
     * @param key Key值
     * @param values 多个取值
     * @return 是否保存成功
     */
    public boolean lpush(String sharding, String key, String... values) throws Exception;

    /**
     * 方法：右出队列
     * @param sharding 分片
     * @param key Key值
     * @return Key在Redis的当前值
     */
    public String lpop(String sharding, String key) throws Exception;

    /**
     * 方法：左出队列
     * @param sharding 分片
     * @param key Key值
     * @return Key在Redis的当前值
     */
    public String rpop(String sharding, String key) throws Exception;

    /**
     * 方法：队列长度
     * @param sharding 分片
     * @param key Key值
     * @return Key在Redis的当前值
     */
    public long llen(String sharding, String key) throws Exception;

    /**
     * 方法：设置散列值
     * @param sharding 分片
     * @param key 键值
     * @param field 域名
     * @param value 取值
     * @return 是否设置成功
     */
    public boolean hset(String sharding, String key, String field, String value) throws Exception;

    /**
     * 方法：设置散列值（批量）
     * @param sharding 分片
     * @param key 键值
     * @param hash filed和value组成的键值对
     * @return 是否设置成功
     */
    public boolean hmset(String sharding, String key, Map<String, String> hash) throws Exception;

    /**
     * 方法：读取散列值
     * @param sharding 分配
     * @param key 键值
     * @param field 域名
     * @return value值
     */
    public String hget(String sharding, String key, String field) throws Exception;

    /**
     * 方法：读取散列值（批量）
     * @param sharding 分片
     * @param key 键值
     * @param fields 域名组
     * @return value值组
     */
    public List<String> hmget(String sharding, String key, String... fields) throws Exception;

    /**
     * 方法：判断散列值是否存在
     * @param sharding 分配
     * @param key 键值
     * @param field 域名
     * @return 是否存在
     */
    public boolean hexists(String sharding, String key, String field) throws Exception;
}
