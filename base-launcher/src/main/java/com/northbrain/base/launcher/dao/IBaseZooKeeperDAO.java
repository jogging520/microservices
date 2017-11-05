package com.northbrain.base.launcher.dao;

import com.northbrain.base.common.exception.ParametersStateException;
import com.northbrain.base.launcher.model.po.ZooKeeperNodePO;

import org.apache.curator.framework.CuratorFramework;

import java.util.List;

/**
 * 类名：基础ZooKeeper的DAO接口
 * 用途：访问ZooKeeper节点
 * @author Jiakun
 * @version 1.0
 */
public interface IBaseZooKeeperDAO
{
    /**
     * 方法：获取ZooKeeper会话
     * @return ZooKeeper会话
     */
    public CuratorFramework getSession() throws Exception;

    /**
     * 方法：关闭ZooKeeper会话
     */
    public void closeSession();

    /**
     * 方法：创建ZooKeeper节点
     * @param ZooKeeperNodePO ZooKeeper节点对象
     * @return 是否成功
     * @throws Exception
     */
    public <Z extends ZooKeeperNodePO> boolean create(Z ZooKeeperNodePO) throws Exception;

    /**
     * 方法：删除ZooKeeper节点
     * @param ZooKeeperNodePO ZooKeeper节点对象
     * @param recursive 是否递归（子节点）
     * @return 是否成功
     * @throws Exception
     */
    public <Z extends ZooKeeperNodePO> boolean delete(Z ZooKeeperNodePO, boolean recursive) throws Exception;

    /**
     * 方法：删除ZooKeeper节点
     * @param path 路径
     * @param recursive 是否递归（子节点）
     * @return 是否成功
     * @throws Exception
     */
    public boolean delete(String path, boolean recursive) throws Exception;

    /**
     * 方法：获取ZooKeeper的节点取值（数据）
     * @param ZooKeeperNodePO ZooKeeper对象
     * @return 节点取值（数据）
     * @throws Exception
     */
    public <Z extends ZooKeeperNodePO> String getData(Z ZooKeeperNodePO) throws Exception;

    /**
     * 方法：获取ZooKeeper的节点取值（数据）
     * @param path 路径
     * @return 节点取值（数据）
     * @throws Exception
     */
    public String getData(String path) throws ParametersStateException, Exception;

    /**
     * 方法：判断节点是否存在
     * @param ZooKeeperNodePO ZooKeeper节点对象
     * @return 是否存在
     * @throws Exception
     */
    public <Z extends ZooKeeperNodePO> boolean isExists(Z ZooKeeperNodePO) throws Exception;

    /**
     * 方法：判断节点是否存在
     * @param path 路径
     * @return 是否存在
     * @throws Exception
     */
    public boolean isExists(String path) throws Exception;

    /**
     * 方法：获取ZooKeeper节点的所有子节点列表
     * @param parentPath 父节点路径
     * @return 子节点列表
     * @throws Exception
     */
    public List<String> getChildren(String parentPath) throws Exception;

    /**
     * 方法：更新节点数据取值（不判断数据版本号）
     * @param ZooKeeperNodePO ZooKeeper节点对象
     * @return 是否更新成功
     * @throws Exception
     */
    public <Z extends ZooKeeperNodePO> boolean setDataWithoutVersion(Z ZooKeeperNodePO) throws Exception;

    /**
     * 方法：更新节点数据取值
     * @param ZooKeeperNodePO ZooKeeper节点对象
     * @return 是否更新成功
     * @throws Exception
     */
    public <Z extends ZooKeeperNodePO> boolean setDataWithVersion(Z ZooKeeperNodePO) throws Exception;

    /**
     * 方法：更新节点数据取值（不判断数据版本号）
     * @param path 路径
     * @param value 取值
     * @return 是否更新成功
     * @throws Exception
     */
    public int setDataWithoutVersion(String path, String value) throws Exception;
}