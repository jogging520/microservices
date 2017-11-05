package com.northbrain.base.launcher.dao.impl;

import com.northbrain.base.launcher.dao.IZooKeeperCacheDAO;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Names;
import com.northbrain.base.common.model.bo.Parameters;
import com.northbrain.base.common.util.ThreadPoolUtil;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ParameterConfigException;
import com.northbrain.base.common.exception.ThreadPoolExecutorException;
import com.northbrain.base.common.exception.ZooKeeperSessionException;
import com.northbrain.base.common.model.bo.Hints;

import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ZooKeeperCacheDAO extends BaseZooKeeperDAO implements IZooKeeperCacheDAO
{
    private static Logger logger = Logger.getLogger(ZooKeeperCacheDAO.class);
    private PathChildrenCache pathChildrenCache;

    /**
     * 方法：缓存（注册监听）
     * @param path 路径
     * @param cacheData 是否缓存
     * @param listener 监听器
     * @throws Exception 异常
     */
    @Override
    public void cache(String path, boolean cacheData, PathChildrenCacheListener listener) throws Exception
    {
        if(path == null || path.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "path");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(listener == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "listener");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.info(Hints.HINT_STORAGE_ZOOKEEPER_ADD_CACHE + path);

        if(ThreadPoolUtil.getCacheExecutorService() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "CacheExecutorService");
            throw new ThreadPoolExecutorException(Errors.ERROR_SYSTEM_THREAD_POOL_EXECUTOR_EXCEPTION);
        }

        if(super.getSession() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "session");
            throw new ZooKeeperSessionException(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }
        
        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_SET_CACHE_LISTENER + path);

        pathChildrenCache = new PathChildrenCache(super.getSession(), path, cacheData, false, ThreadPoolUtil.getCacheExecutorService());

        pathChildrenCache.start(StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable()
                .addListener(listener);
    }

    /**
     * 方法：获取所有子节点列表
     * @return 子节点的键值对
     * @throws Exception 异常
     */
    @Override
    public Map<String, String> getChildrenData() throws Exception
    {
        Map<String, String> childrenData;

        String businessCommonCharset = Parameters.get(Names.BUSINESS_COMMON_CHARSET);

        if(businessCommonCharset == null || businessCommonCharset.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_CHARSET");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
        
        childrenData = new ConcurrentHashMap<>();

        for(ChildData childData: pathChildrenCache.getCurrentData())
        {
        	logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_GET_CACHE_CHILD_NODE_DATA + childData.getPath());
        	
        	childrenData.put(childData.getPath(), new String(childData.getData(), businessCommonCharset));
        }

        return childrenData;
    }

    /**
     * 方法：移除节点
     * @param path 路径
     * @return 是否成功
     * @throws Exception 异常
     */
    public boolean remove(String path) throws Exception
    {
        if(path == null || path.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "path");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_REMOVE_CACHE_LISTENER + path);
        
        return super.delete(path, true);
    }
}
