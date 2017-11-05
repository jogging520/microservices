package com.northbrain.base.launcher.dao.impl;

import com.northbrain.base.launcher.dao.ISequenceDAO;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 类名：序列号DAO接口的实现类
 * 用途：实现全局（分布式环境下）的唯一序列号
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class SequenceDAO extends BaseZooKeeperDAO implements ISequenceDAO
{
	private static Logger logger = Logger.getLogger(SequenceDAO.class);
	
	/**
	 * 方法：下一个序列号，默认情况下返回0，由调用者判断。
	 * @param path 路径
	 * @param sequenceName 序列名称
	 * @return 下一个序列号
	 */
	@Override
	public int getNextValue(String path, String sequenceName) throws Exception
	{
		if(path == null || path.equals(""))
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "path");
			throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
		}
		
		if(sequenceName == null || sequenceName.equals(""))
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "sequenceName");
			throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
		}
		
		int nextValue = super.setDataWithoutVersion(path + Constants.BUSINESS_COMMON_NODE_SEPARATOR + sequenceName, sequenceName);
		
		logger.debug(Hints.HINT_BUSINESS_COMMON_SEQUENCE_NEXT_VALUE + String.valueOf(nextValue));
		
		return nextValue;
	}

}
