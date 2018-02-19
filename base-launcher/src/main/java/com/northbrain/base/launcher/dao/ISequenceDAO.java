package com.northbrain.base.launcher.dao;

/**
 * 类名：序列号DAO接口
 * 用途：实现全局（分布式环境下）的唯一序列号
 * @author Jiakun
 * @version 1.0
 *
 */
public interface ISequenceDAO 
{
	/**
	 * 方法：获取下一个序列号
	 * @param path 路径
	 * @param sequenceName 序列名称
	 * @return 下一个序列号
	 */
	int getNextValue(String path, String sequenceName) throws Exception;
}
