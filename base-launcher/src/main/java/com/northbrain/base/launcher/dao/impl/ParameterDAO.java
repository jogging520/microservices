package com.northbrain.base.launcher.dao.impl;

import com.northbrain.base.common.exception.CollectionEmptyException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.exception.ParameterConfigException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Names;
import com.northbrain.base.common.model.bo.Parameters;
import com.northbrain.base.launcher.model.po.ParameterPO;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.springframework.stereotype.Component;

import com.northbrain.base.launcher.dao.IParameterDAO;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.Hints;

/**
 * 类名：参数访问DAO接口的实现类
 * 用途：根据给了属性读写ZooKeeper参数，变更的数据同步至Parameters中。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class ParameterDAO extends ZooKeeperCacheDAO implements IParameterDAO, PathChildrenCacheListener
{
	private static Logger logger = Logger.getLogger(ParameterDAO.class);
	
	/**
	 * 方法：监听到子节点事件
	 * @param curatorFramework curator框架
	 * @param pathChildrenCacheEvent 变更事件
	 * @exception Exception 异常
	 */
	@Override
	public void childEvent(CuratorFramework curatorFramework, 
			PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception 
	{
		if(curatorFramework == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "curatorFramework");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
		}	
		
		if(pathChildrenCacheEvent == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "pathChildrenCacheEvent");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
		}
		
		logger.info(Hints.HINT_STORAGE_ZOOKEEPER_RECV_CHILDEVENT);
		
		switch(pathChildrenCacheEvent.getType())
		{
		case CHILD_ADDED:
			logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_ADD_CHILD_NODE);
			updateChildHandler(getParameterFromEvent(pathChildrenCacheEvent));
			break;
		case CHILD_UPDATED:
			logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_UPDATE_CHILD_NODE);
			updateChildHandler(getParameterFromEvent(pathChildrenCacheEvent));
			break;
		case CHILD_REMOVED:
			logger.debug(Hints.HINT_STORAGE_ZOOKEEPER_REMOVE_CHILD_NODE);
			removeChildHandler(getParameterFromEvent(pathChildrenCacheEvent));
			break;
		default:
			break;
		}
	}
	
	/**
	 * 方法：更新子节点的处理方法
	 * @param parameterPO 参数对象
	 * @throws Exception 异常
	 */
	private synchronized void updateChildHandler(ParameterPO parameterPO) throws Exception
	{
		if(parameterPO == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "pathChildrenCacheEvent");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
		}	
		
		String[] nameSpaces = parameterPO.getNameSpace().split(Constants.BUSINESS_COMMON_NODE_SEPARATOR);
		
		if(nameSpaces.length <= 1)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "nameSpaces");
			throw new CollectionEmptyException(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
		}
		
		String type = nameSpaces[nameSpaces.length-1];		
		
		Parameters.synching();
		
		if(!Parameters.add(type, parameterPO.getNodeName(), parameterPO.getNodeValue()))
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_UPDATE_PARAMETERS + "Type:" + type + ", Name:" + parameterPO.getNodeName());
			Parameters.invalid();
		}
		else 
		{
			logger.debug(Hints.HINT_BUSINESS_COMMON_UPDATE_PARAMETER_ITEM + "Type:" + type + ", Name:" + parameterPO.getNodeName());
			Parameters.valid();
		}
		
		logger.debug(Parameters.getAll());
	}
	
	/**
	 * 方法：移除子节点的处理方法
	 * @param parameterPO 参数对象
	 * @throws Exception 异常
	 */
	private synchronized void removeChildHandler(ParameterPO parameterPO) throws Exception
	{
		if(parameterPO == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "pathChildrenCacheEvent");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
		}	
		
		String[] nameSpaces = parameterPO.getNameSpace().split(Constants.BUSINESS_COMMON_NODE_SEPARATOR);		
		
		if(nameSpaces.length <= 1)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "directoryItem");
			throw new CollectionEmptyException(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
		}
		
		String type = nameSpaces[nameSpaces.length-1];
				
		Parameters.synching();
		
		if(Parameters.isExists(type, parameterPO.getNodeName()))
		{
			if(!Parameters.remove(type, parameterPO.getNodeName()))
			{
				logger.error(Errors.ERROR_BUSINESS_COMMON_REMOVE_PARAMETERS + "Type:" + type + ", Name:" + parameterPO.getNodeName());
				Parameters.invalid();
			}
			else
			{
				logger.debug(Hints.HINT_BUSINESS_COMMON_REMOVE_PARAMETER_ITEM + "Type:" + type + ", Name:" + parameterPO.getNodeName());
				Parameters.valid();
			}
		}
		
		logger.debug(Parameters.getAll());
	}

	/**
	 * 方法：主动同步（远端主动同步到本地）
	 * @param path 路径
	 * @param cacheData 是否缓存数据
	 * @throws Exception 异常
	 */
	@Override
	public void sync(String path, boolean cacheData) throws Exception 
	{
		if(path == null || path.equals(""))
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "path");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
		}
		
		PathChildrenCacheListener pathChildrenCacheListener = new ParameterDAO();
		
		super.cache(path, cacheData, pathChildrenCacheListener);		
	}

	/**
	 * 方法：根据子节点事件获取参数
	 * @param pathChildrenCacheEvent 变更事件
	 * @return ParameterPO 参数持久化对象
	 * @exception Exception 异常
	 */
	private ParameterPO getParameterFromEvent(PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception
	{
		if(pathChildrenCacheEvent == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "pathChildrenCacheEvent");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
		}	
		
		String charset = Parameters.get(Names.BUSINESS_COMMON_CHARSET);
			
		if(charset == null || charset.equals(""))
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_CHARSET");
			throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
		}
		
		if(pathChildrenCacheEvent.getData() == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "pathChildrenCacheEvent.getData()");
			throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
		}
		
		if(pathChildrenCacheEvent.getData().getPath() == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "pathChildrenCacheEvent.getData().getPath()");
			throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
		}	
		
		if(pathChildrenCacheEvent.getData().getData() == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "pathChildrenCacheEvent.getData().getData()");
			throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
		}
		
		if(pathChildrenCacheEvent.getData().getStat() == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "pathChildrenCacheEvent.getData().getStat()");
			throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
		}
		
		String nameSpace = pathChildrenCacheEvent.getData().getPath();
		String nodeValue = new String(pathChildrenCacheEvent.getData().getData(), charset);
		int dataVersion = pathChildrenCacheEvent.getData().getStat().getVersion();
		
		if(dataVersion < 0)
		{
			logger.error(Errors.ERROR_STORAGE_ZOOKEEPER_INCORRECT_DATAVERSION + String.valueOf(dataVersion));
			throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
		}
		
		String[] directoryItem = nameSpace.split(Constants.BUSINESS_COMMON_NODE_SEPARATOR);		
		
		if(directoryItem.length <= 1)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "directoryItem");
			throw new CollectionEmptyException(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
		}
		
		String nodeName = directoryItem[directoryItem.length-1];		
		nameSpace = nameSpace.replace(Constants.BUSINESS_COMMON_NODE_SEPARATOR + nodeName, "");

		return new ParameterPO(nameSpace, CreateMode.PERSISTENT, nodeName, nodeValue, dataVersion);
	}
}
