package com.northbrain.base.launcher.dao;


import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

import java.util.Map;

/**
 * 类名：缓存DAO接口
 * 用途：注册监听ZooKeeper节点信息，并同步至本地。
 * @author Jiakun
 * @version 1.0
 */
public interface IZooKeeperCacheDAO
{
    /**
     * 方法：缓存（注册监听）
     * @param path 路径
     * @param cacheData 是否缓存
     * @param listener 监听器
     * @throws Exception
     */
    public void cache(String path, boolean cacheData, PathChildrenCacheListener listener)
            throws Exception;

    /**
     * 方法：获取所有子节点列表
     * @return
     * @throws Exception
     */
    public Map<String, String> getChildrenData() throws Exception;

    /**
     * 方法：移除节点
     * @param path 路径
     * @return 是否成功
     * @throws Exception
     */
    public boolean remove(String path) throws Exception;
}
