package com.northbrain.base.common.model.bo;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ParametersStateException;
import com.northbrain.base.common.model.bo.BaseType.STATE;
import com.northbrain.base.common.util.CommonUtil;

/**
 * 类名：基础配置类Parameter
 * 用途：本地运行进程的控制器，远程（ZooKeeper）和本地（properties）的参数动态同步至控制器中。
 * 进程实时从该控制器读取相应的参数，实现配置参数的本地化，所有的参数均可达到实时和准实时控制。
 * @author Jiakun
 * @version 1.0
 */
public class Parameters
{
    private static Logger logger = Logger.getLogger(Parameters.class);

    private static STATE state = STATE.INVALID; 			                    //基础配置器的状态，初始化为失效状态，同步后再生效。
    private static List<Item> items = new CopyOnWriteArrayList<>();	            //控制器的参数列表

    /**
     * 获取基础配置器的状态
     * @return 基础配置器的当前状态
     */
    public static STATE getState()
    {
        return Parameters.state;
    }

    /**
     * 设置基础配置器的状态
     * @param state 状态
     */
    private static synchronized void setState(STATE state)
    {
        Parameters.state = state;
    }

    /**
     * 设置基础配置器为有效状态
     */
    public static synchronized void valid()
    {
    	setState(STATE.VALID);
    }

    /**
     * 设置基础配置器为无效状态
     */
    public static synchronized void invalid()
    {
    	setState(STATE.INVALID);
    }

    /**
     * 设置基础配置器为同步状态
     */
    public static synchronized void synching()
    {
    	setState(STATE.SYNCHING);
    }

    /**
     * 获取基础配置器的参数数量
     * @return 基础配置器参数列表的数量（长度）
     */
    private static int size()
    {
        return items.size();
    }

    /**
     * 往基础配置器增加参数内容
     * @param type 类型
     * @param name 名称
     * @param value 取值
     * @return 成功增加返回true，否则返回false
     * @throws Exception 异常
     */
    public static synchronized boolean add(String type, String name, String value) throws Exception
    {
        if(type == null || type.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "type");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(name == null || name.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "name");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(value == null || value.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "value");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        
        logger.debug(Hints.HINT_BUSINESS_COMMON_ADD_PARAMETER_ITEM + type + ", " + name);

        if(isExists(type, name))
            return update(type, name, value);

        Item item = new Item(type, name, value);
        return items.add(item);
    }

    /**
     * 根据类型和名称查找基础配置器中相应参数的当前取值
     * @param type 类型
     * @param name 名称
     * @return 参数当前值，如果没有，返回空
     * @throws Exception 异常
     */
    public static synchronized String get(String type, String name) throws Exception
    {
        if(size() == 0)
            return null;

        if(type == null || type.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "type");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(name == null || name.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "name");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        
        logger.debug(Hints.HINT_BUSINESS_COMMON_GET_PARAMETER_ITEM_VALUE + type + ", " + name);

        if(getState() != STATE.VALID)
            throw new ParametersStateException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_STATUS_EXCEPTION);

        for(Item item: items)
        {
            if(item.getType().equalsIgnoreCase(type) &&
                    item.getName().equalsIgnoreCase(name))
            {
                return item.getValue();
            }
        }

        return null;
    }

    /**
     * 根据类型和名称查找基础配置器中相应参数的当前取值
     * @param name 名称
     * @return 参数当前值，如果没有，返回空
     * @throws Exception 异常
     */
    public static synchronized String get(Names name) throws Exception
    {
        if(size() == 0)
            return null;

        if(name == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "name");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        
        logger.debug(Hints.HINT_BUSINESS_COMMON_GET_PARAMETER_ITEM_VALUE + name.getName());
        
        if(getState() != STATE.VALID)
        {
            CommonUtil.sleeping(Names.BUSINESS_COMMON_MANAGE_INTERVAL_MS);
            
            if(getState() != STATE.VALID)
            	throw new ParametersStateException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_STATUS_EXCEPTION);
        }
        
        for(Item item: items)
        {
            if(item.getType().equalsIgnoreCase(name.getType()) &&
                    item.getName().equalsIgnoreCase(name.getName()))
            {
                return item.getValue();
            }
        }

        return null;
    }

    /**
     * 根据类型和名称判断基础配置器中相应参数是否存在
     * @param type 类型
     * @param name 名称
     * @return 存在返回true，否则返回false
     * @throws Exception 异常
     */
    public static synchronized boolean isExists(String type, String name) throws Exception
    {
        if(type == null || type.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "type");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(name == null || name.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "name");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        
        logger.debug(Hints.HINT_BUSINESS_COMMON_CHECK_PARAMETER_IS_EXISTS + type + ", " + name);

        for(Item item: items)
        {
            if(item.getType().equalsIgnoreCase(type) &&
                    item.getName().equalsIgnoreCase(name))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * 根据类型和名称移除基础配置器中相应参数
     * @param type 类型
     * @param name 名称
     * @return 成功移除返回true，否则返回false
     * @throws Exception 异常
     */
    public static synchronized boolean remove(String type, String name) throws Exception
    {
        if(type == null || type.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "type");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(name == null || name.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "name");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_REMOVE_PARAMETER_ITEM + type + ", " + name);
        
        for(Item item: items)
        {
            if(item.getType().equalsIgnoreCase(type) &&
                    item.getName().equalsIgnoreCase(name))
            {
                return items.remove(item);
            }
        }

        return false;
    }

    /**
     * 根据类型批量移除基础配置器中相应参数
     * @param type 类型
     * @return 成功移除返回true，否则返回false
     * @throws Exception 异常
     */
    public static synchronized boolean remove(String type) throws Exception
    {
        if(type == null || type.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "type");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_REMOVE_PARAMETER_ITEM + type);        
        
        for(Item item: items)
        {
            if(item.getType().equalsIgnoreCase(type))
            {
                return items.remove(item);
            }
        }

        return false;
    }

    /**
     * 移除基础配置器中的所有参数，清空参数列表
     * @return 成功移除返回true，否则返回false
     * @throws Exception 异常
     */
    public static synchronized boolean removeAll() throws Exception
    {
    	logger.debug(Hints.HINT_BUSINESS_COMMON_REMOVE_PARAMETER_ITEM);
    	
    	items.clear();

        return true;
    }

    /**
     * 根据类型、名称和取值，更新基础配置器中对应的参数
     * 如果没有响应的参数，不增加。
     * @param type 类型
     * @param name 名称
     * @param value 取值
     * @return 成功更新返回true，否则返回false
     * @throws Exception 异常
     */
    public static synchronized boolean update(String type, String name, String value) throws Exception
    {
        if(type == null || type.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "type");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(name == null || name.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "name");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(value == null || value.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "value");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        
        logger.debug(Hints.HINT_BUSINESS_COMMON_UPDATE_PARAMETER_ITEM + type + ", " + name);

        for(Item item: items)
        {
            if(item.getType().equalsIgnoreCase(type) &&
                    item.getName().equalsIgnoreCase(name))
            {
                item.setValue(value);

                return true;
            }
        }

        return false;
    }

    /**
     * 获取当前基础配置器的所有参数清单
     * @return 当前基础配置器的所有参数清单字符串
     */
    public static synchronized String getAll()
    {
        StringBuilder result = new StringBuilder();

        result.append("Status");
        result.append(getState());

        for(Item item: items)
        {
            result.append("\nType:");
            result.append(item.getType());
            result.append("\tName:");
            result.append(item.getName());
            result.append("\tValue:");
            result.append(item.getValue());
        }

        return result.toString();
    }

    /**
     * 类名：基础配置器内部类Item
     * 用途：记录每一条参数
     * @author Jiakun
     * @version 1.0
     *
     */
    static class Item
    {
        private String type;	//类型
        private String name;	//名称
        private String value;	//取值

        Item(String type, String name, String value)
        {
            this.setType(type);
            this.setName(name);
            this.setValue(value);
        }

        String getType()
        {
            return type;
        }

        void setType(String type)
        {
            this.type = type;
        }

        String getName()
        {
            return name;
        }

        void setName(String name)
        {
            this.name = name;
        }

        String getValue()
        {
            return value;
        }

        void setValue(String value)
        {
            this.value = value;
        }
    }
}
