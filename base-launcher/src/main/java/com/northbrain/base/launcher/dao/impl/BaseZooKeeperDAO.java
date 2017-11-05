package com.northbrain.base.launcher.dao.impl;

import com.northbrain.base.launcher.dao.IBaseZooKeeperDAO;
import com.northbrain.base.common.exception.ParameterConfigException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.bo.Names;
import com.northbrain.base.common.model.bo.Parameters;
import com.northbrain.base.launcher.model.po.ZooKeeperNodePO;
import com.northbrain.base.launcher.session.ZooKeeperSessionFactory;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ZooKeeperSessionException;

import org.apache.curator.framework.CuratorFramework;
import org.apache.log4j.Logger;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 类名：基础ZooKeeper的DAO类，IBaseZooKeeperDAO的实现类
 * 用途：访问ZooKeeper节点
 * @author Jiakun
 * @version 1.0
 */
@Component
public class BaseZooKeeperDAO implements IBaseZooKeeperDAO
{
    private static Logger logger = Logger.getLogger(BaseZooKeeperDAO.class);

    /**
     * 方法：获取ZooKeeper会话
     * @return ZooKeeper会话
     */
    @Override
    public CuratorFramework getSession() throws Exception
    {
        return ZooKeeperSessionFactory.getSession();
    }

    /**
     * 方法：关闭ZooKeeper会话
     */
    @Override
    public void closeSession()
    {
        ZooKeeperSessionFactory.closeSession();
    }

    /**
     * 方法：创建ZooKeeper节点
     * @param zooKeeperNode ZooKeeper节点对象
     * @return 是否成功
     * @throws Exception 异常
     */
    @Override
    public <Z extends ZooKeeperNodePO>boolean create(Z zooKeeperNode) throws Exception
    {
        if(zooKeeperNode == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "zooKeeperNode");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "session");
            throw new ZooKeeperSessionException(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }

        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_CREATE_NODE + zooKeeperNode.getPath());
        getSession().create()
                .creatingParentsIfNeeded()
                .withMode(zooKeeperNode.getType())
                .forPath(zooKeeperNode.getPath(), zooKeeperNode.getByteNodeValue());

        return true;
    }

    /**
     * 方法：获取ZooKeeper节点的所有子节点列表（第一层）
     * @param parentPath 父节点路径
     * @return 第一层子节点列表
     * @throws Exception 异常
     */
    @Override
    public List<String> getChildren(String parentPath) throws Exception
    {
        List<String> children;

        if(parentPath == null || parentPath.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "parentPath");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "session");
            throw new ZooKeeperSessionException(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }

        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_GET_CHILD_NODE + parentPath);
        
        children = getSession().getChildren()
                .forPath(parentPath);

        return children;
    }

    /**
     * 方法：删除ZooKeeper节点
     * @param zooKeeperNode ZooKeeper节点对象
     * @param recursive 是否递归（子节点）
     * @return 是否成功
     * @throws Exception 异常
     */
    @Override
    public <Z extends ZooKeeperNodePO> boolean delete(Z zooKeeperNode, boolean recursive) throws Exception
    {
        if(zooKeeperNode == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "zooKeeperNode");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "session");
            throw new ZooKeeperSessionException(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }
        
        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_DELETE_NODE + zooKeeperNode.getPath());
        
        Stat stat = new Stat();
        getSession().getData()
                .storingStatIn(stat)
                .forPath(zooKeeperNode.getPath());

        if(!recursive)
            getSession().delete()
                    .guaranteed()
                    .forPath(zooKeeperNode.getPath());
        else
            getSession().delete()
                    .guaranteed()
                    .deletingChildrenIfNeeded()
                    .forPath(zooKeeperNode.getPath());

        return true;
    }

    /**
     * 方法：删除ZooKeeper节点
     * @param path ZooKeeper节点路径
     * @param recursive 是否递归（子节点）
     * @return 是否成功
     * @throws Exception 异常
     */
    @Override
    public boolean delete(String path, boolean recursive) throws Exception
    {
        if(path == null || path.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "path");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "session");
            throw new ZooKeeperSessionException(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }

        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_DELETE_NODE + path);
        
        Stat stat = new Stat();
        getSession().getData()
                .storingStatIn(stat)
                .forPath(path);

        if(!recursive)
            getSession().delete()
                    .guaranteed()
                    .forPath(path);
        else
            getSession().delete()
                    .guaranteed()
                    .deletingChildrenIfNeeded()
                    .forPath(path);

        logger.info(Hints.HINT_STORAGE_ZOOKEEPER_DELETE_NODE + path);

        return true;
    }

    /**
     * 方法：获取ZooKeeper的节点取值（数据）
     * @param zooKeeperNode ZooKeeper对象
     * @return 节点取值（数据）
     * @throws Exception 异常
     */
    @Override
    public <Z extends ZooKeeperNodePO> String getData(Z zooKeeperNode) throws Exception
    {
        String data;

        if(zooKeeperNode == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "zooKeeperNode");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "session");
            throw new ZooKeeperSessionException(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }

        String charset = Parameters.get(Names.BUSINESS_COMMON_CHARSET);

        if(charset == null || charset.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_CHARSET");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_GET_NODE_DATA + zooKeeperNode.getPath());
        
        if(!isExists(zooKeeperNode)) return null;

        Stat stat = new Stat();
        data = new String(getSession().getData()
                .storingStatIn(stat)
                .forPath(zooKeeperNode.getPath()), charset);

        return data;
    }

    /**
     * 方法：获取ZooKeeper的节点取值（数据）
     * @param path 路径
     * @return 节点取值（数据）
     * @throws Exception 异常
     */
    @Override
    public String getData(String path) throws Exception
    {
        String result;

        if(path == null || path.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "path");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "session");
            throw new ZooKeeperSessionException(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }

        String charset = Parameters.get(Names.BUSINESS_COMMON_CHARSET);

        if(charset == null || charset.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_CHARSET");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_GET_NODE_DATA + path);
        
        if(!isExists(path))
            return null;

        Stat stat = new Stat();
        result = new String(getSession().getData()
                .storingStatIn(stat)
                .forPath(path), charset);

        return result;
    }

    /**
     * 方法：判断节点是否存在
     * 这些把异常都抛出去，单纯true和false不能判断是否真正存在。
     * @param zooKeeperNode ZooKeeper节点对象
     * @return 是否存在
     * @throws Exception 异常
     */
    @Override
    public <Z extends ZooKeeperNodePO> boolean isExists(Z zooKeeperNode) throws Exception
    {
        if(zooKeeperNode == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "zooKeeperNode");
        	throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        
        if(getSession() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "session");
        	throw new ZooKeeperSessionException(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }

        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_CHECK_IS_EXISTS + zooKeeperNode.getPath());
        
        Stat stat = getSession().checkExists()
                    .forPath(zooKeeperNode.getPath());

        return stat != null;
    }

    /**
     * 方法：判断节点是否存在
     * 这些把异常都抛出去，单纯true和false不能判断是否真正存在。
     * @param path 路径
     * @return 是否存在
     * @throws Exception 异常
     */
    @Override
    public boolean isExists(String path) throws Exception
    {
        if(path == null || path.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "path");
        	throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "session");
        	throw new ZooKeeperSessionException(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }
        
        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_CHECK_IS_EXISTS + path);
        
        Stat stat = getSession().checkExists()
                    .forPath(path);

        return stat != null;
    }

    /**
     * 方法：更新节点数据取值
     * @param zooKeeperNode ZooKeeper节点对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public <Z extends ZooKeeperNodePO> boolean setDataWithVersion(Z zooKeeperNode) throws Exception
    {
        if(zooKeeperNode == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "zooKeeperNode");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "session");
            throw new ZooKeeperSessionException(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }

        String charset = Parameters.get(Names.BUSINESS_COMMON_CHARSET);

        if(charset == null || charset.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_CHARSET");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
        
        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_UPDATE_NODE_DATA + zooKeeperNode.getPath());

        getSession().setData()
                .withVersion(zooKeeperNode.getDataVersion())
                .forPath(zooKeeperNode.getPath(), zooKeeperNode.getNodeValue().getBytes(charset));

        return true;
    }

    /**
     * 方法：更新节点数据取值（不判断数据版本号）
     * @param zooKeeperNode ZooKeeper节点对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public <Z extends ZooKeeperNodePO> boolean setDataWithoutVersion(Z zooKeeperNode) throws Exception
    {
        if(zooKeeperNode == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "zooKeeperNode");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "session");
            throw new ZooKeeperSessionException(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }

        String charset = Parameters.get(Names.BUSINESS_COMMON_CHARSET);

        if(charset == null || charset.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_CHARSET");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
        
        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_UPDATE_NODE_DATA + zooKeeperNode.getPath());

        getSession().setData()
                .forPath(zooKeeperNode.getPath(), zooKeeperNode.getNodeValue().getBytes(charset));

        return true;
    }

    /**
     * 方法：更新节点数据取值（不判断数据版本号）
     * @param path 路径
     * @param value 取值
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public int setDataWithoutVersion(String path, String value) throws Exception
    {
        int sequence;
        Stat stat;

        if(path == null || path.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "path");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(value == null || value.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "value");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(getSession() == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "session");
            throw new ZooKeeperSessionException(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }

        String charset = Parameters.get(Names.BUSINESS_COMMON_CHARSET);

        if(charset == null || charset.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_CHARSET");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }

        logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_UPDATE_NODE_DATA + path);
        
        stat = getSession().setData()
                .forPath(path, value.getBytes(charset));

        sequence = stat.getVersion();

        return sequence;
    }
}
