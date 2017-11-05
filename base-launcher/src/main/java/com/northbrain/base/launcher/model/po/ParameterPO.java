package com.northbrain.base.launcher.model.po;

import org.apache.zookeeper.CreateMode;

/**
 * 类名：参数类
 * 用途：本地与ZooKeeper进行参数同步、更新的基础类
 * @author Administrator
 *
 */
public class ParameterPO extends ZooKeeperNodePO
{
	private static final long serialVersionUID = 1L;

	/**
	 * 方法名：参数类Parameter的构造方法
	 * @param nameSpace 命名空间
	 * @param type 类型
	 * @param nodeName 节点名称
	 * @param nodeValue 节点取值
	 * @param dataVersion 节点数据版本号
	 */
	public ParameterPO(String nameSpace, CreateMode type, String nodeName, 
			String nodeValue, int dataVersion) 
	{
		super(nameSpace, type, nodeName, nodeValue, dataVersion);
	}
}
