package com.northbrain.base.launcher.dao;

/**
 * 类名：参数访问DAO接口
 * 用途：根据给了属性读写ZooKeeper参数
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IParameterDAO 
{
	/**
	 * 方法：主动同步（远端主动同步到本地）
	 * @param path 路径
	 * @param cacheData 是否缓存数据
	 * @throws Exception
	 */
	public void sync(String path, boolean cacheData) throws Exception;
}
