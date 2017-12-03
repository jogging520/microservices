package com.northbrain.base.common.util;

import java.util.Arrays;

import org.apache.log4j.*;
import org.springframework.core.io.support.ResourcePropertySource;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.exception.ParameterConfigException;
import com.northbrain.base.common.model.bo.*;

/**
 * 类名：启动属性类
 * 用途：从properties文件中读取zookeeper及业务初始化的属性配置
 * @author Jiakun
 * @version 1.0
 *
 */
public class LauncherUitl
{
	private static Logger logger = Logger.getLogger(LauncherUitl.class);
	
	private static ResourcePropertySource resourcePropertySource = null;
	
	/**
     * 方法：初始化启动的基础参数，包括ZooKeeper的配置、字符集等。
     * @throws Exception 异常
     */
    public static void initProperties(BaseType.SERVICETYPE serviceType) throws Exception
    {
        logger.debug(Hints.HINT_BUSINESS_COMMON_DYNAMIC_ADD_LOCAL_PARAMETERS);
        
        //默认从classpath:application.properties读取配置，可以通过--spring.config.location配置（相对路径classpath和绝对路径file）
        String systemSpringConfigLocation = Parameters.get(Names.SYSTEM_SPRING_CONFIG_LOCATION);
    	
		if(resourcePropertySource == null)
        {
        	if(systemSpringConfigLocation == null || systemSpringConfigLocation.equals(""))
        		resourcePropertySource = new ResourcePropertySource(Constants.SYSTEM_SPRING_RESOURCE_PROPERTY_NAME, Constants.SYSTEM_SPRING_RESOURCE_PROPERTY_LOCATION);
        	else
        		resourcePropertySource = new ResourcePropertySource(Constants.SYSTEM_SPRING_RESOURCE_PROPERTY_NAME, systemSpringConfigLocation);
        }

        //原子服务和进程都增加ZOOKEEPER、数据库等参数
        if(serviceType == BaseType.SERVICETYPE.ATOM || serviceType == BaseType.SERVICETYPE.PROC)
        {
			appendParameter(Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE, Names.STORAGE_ZOOKEEPER_SERVERS_ADDRESS.getName(), getProperty(resourcePropertySource, Names.STORAGE_ZOOKEEPER_SERVERS_ADDRESS));
			appendParameter(Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE, Names.STORAGE_ZOOKEEPER_BASE_SLEEP_TIME_MS.getName(), getProperty(resourcePropertySource, Names.STORAGE_ZOOKEEPER_BASE_SLEEP_TIME_MS));
			appendParameter(Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE, Names.STORAGE_ZOOKEEPER_CONNECT_MAX_RETRIES.getName(), getProperty(resourcePropertySource, Names.STORAGE_ZOOKEEPER_CONNECT_MAX_RETRIES));
			appendParameter(Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE, Names.STORAGE_ZOOKEEPER_SESSION_TIMEOUT_MS.getName(), getProperty(resourcePropertySource, Names.STORAGE_ZOOKEEPER_SESSION_TIMEOUT_MS));
			appendParameter(Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE, Names.STORAGE_ZOOKEEPER_CACHE_THREAD_POOL_NUMBER.getName(), getProperty(resourcePropertySource, Names.STORAGE_ZOOKEEPER_CACHE_THREAD_POOL_NUMBER));
		}

        appendParameter(Constants.STORAGE_ZOOKEEPER_BUSINESS_NAMESPACE, Names.BUSINESS_COMMON_MAX_RETRIES.getName(), getProperty(resourcePropertySource, Names.BUSINESS_COMMON_MAX_RETRIES));
		appendParameter(Constants.STORAGE_ZOOKEEPER_BUSINESS_NAMESPACE, Names.BUSINESS_COMMON_CHARSET.getName(), getProperty(resourcePropertySource, Names.BUSINESS_COMMON_CHARSET));
		appendParameter(Constants.STORAGE_ZOOKEEPER_BUSINESS_NAMESPACE, Names.BUSINESS_COMMON_MANAGE_INTERVAL_MS.getName(), getProperty(resourcePropertySource, Names.BUSINESS_COMMON_MANAGE_INTERVAL_MS));
		appendParameter(Constants.STORAGE_ZOOKEEPER_BUSINESS_NAMESPACE, Names.BUSINESS_COMMON_LOG_LEVEL.getName(), getProperty(resourcePropertySource, Names.BUSINESS_COMMON_LOG_LEVEL));
		appendParameter(Constants.STORAGE_ZOOKEEPER_BUSINESS_NAMESPACE, Names.BUSINESS_COMMON_LOG_DIRECTORY.getName(), getProperty(resourcePropertySource, Names.BUSINESS_COMMON_LOG_DIRECTORY));

		logger.debug(Parameters.getAll());
    }
    
    /**
     * 方法：解析命令行，从中读取项目名称和域名称
     * @param arguments 命令行参数数组
     * @throws Exception 异常
     */
    public static boolean parseCommandLine(String[] arguments) throws Exception
    {    	
    	if(arguments == null || arguments.length == 0)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "arguments");
			return false;
		}

    	String commandLine = Arrays.toString(arguments);
    	
    	if(!commandLine.contains(Constants.BUSINESS_COMMON_COMMAND_LINE_PROJECT_NAME))
    	{
    		logger.error(Errors.ERROR_BUSINESS_COMMON_COMMAND_LINE_MISSING);
    		return false;
    	}
    	
    	for(String argument: arguments)
    	{
    		logger.debug(Hints.HINT_BUSINESS_COMMON_COMMAND_LINE_ARGUMENT + argument);
    		
    		String[] elements = argument.split(Constants.BUSINESS_COMMON_COMMAND_LINE_ASSIGN_SYMBOL);
    		
    		if(elements.length != 2)
    			continue;    		
    		
    		if(argument.contains(Constants.BUSINESS_COMMON_COMMAND_LINE_PROJECT_NAME))
    			appendParameter(Constants.STORAGE_ZOOKEEPER_BUSINESS_NAMESPACE, Names.BUSINESS_COMMON_PROJECT_NAME.getName(), elements[1]);

    		if(argument.contains(Constants.BUSINESS_COMMON_COMMAND_LINE_SERVER_PORT))
    			appendParameter(Constants.STORAGE_ZOOKEEPER_BUSINESS_NAMESPACE, Names.BUSINESS_COMMON_SERVER_PORT.getName(), elements[1]);
    		
    		if(argument.contains(Constants.BUSINESS_COMMON_COMMAND_LINE_SPRING_CONFIG_LOCATION))
    			appendParameter(Constants.STORAGE_ZOOKEEPER_SYSTEM_NAMESPACE, Names.SYSTEM_SPRING_CONFIG_LOCATION.getName(), elements[1]);
    	}      	
    	
    	return true;
    }
    
    /**
     * 方法：设置系统的日志级别和日志文件（目录），默认为INFO级
     */
    public static void setLog() throws Exception
    {
    	String businessCommonLogLevel = Parameters.get(Names.BUSINESS_COMMON_LOG_LEVEL);
    	String businessCommonLogDirectory = Parameters.get(Names.BUSINESS_COMMON_LOG_DIRECTORY);
    	String businessCommonApplicationName = Parameters.get(Names.BUSINESS_COMMON_APPLICATION_NAME);
    	String businessCommonServerPort = Parameters.get(Names.BUSINESS_COMMON_SERVER_PORT);
    	
		if(businessCommonLogLevel == null || businessCommonLogLevel.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_LOG_LEVEL");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        } 	
		
		if(businessCommonLogDirectory == null || businessCommonLogDirectory.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_LOG_DIRECTORY");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        } 
    	
		if(businessCommonApplicationName == null || businessCommonApplicationName.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_APPLICATION_NAME");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        } 
		
		if(businessCommonServerPort == null || businessCommonServerPort.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG + "BUSINESS_COMMON_SERVER_PORT");
            throw new ParameterConfigException(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
		
		Logger rootLlogger = Logger.getRootLogger();
    	
    	// 设定是否继承父Logger，默认为true，继承root的输出。	
    	rootLlogger.setAdditivity(true);    	
		
		// 设定Logger的日志级别
		if(businessCommonLogLevel.equalsIgnoreCase(Constants.BUSINESS_COMMON_LOG_DEBUG_LEVEL))
			rootLlogger.setLevel(Level.DEBUG); 
		else
			rootLlogger.setLevel(Level.INFO); 
		
		DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender(); 
		// log的文字码 
		dailyRollingFileAppender.setEncoding(Constants.BUSINESS_COMMON_LOG_ENCODING);
		// true:在已存在log文件后面追加 false:新log覆盖以前的log
		dailyRollingFileAppender.setAppend(true);
		
		Appender appender = Logger.getRootLogger().getAppender(Constants.BUSINESS_COMMON_LOG_APPENDER_NAME);
		
		if(appender == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "appender");
			throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
		}
		
		Layout layout = appender.getLayout();
    	
		if(layout == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "layout");
			throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
		}
		
		dailyRollingFileAppender.setLayout(layout);
    	
		String file = businessCommonLogDirectory + 
				businessCommonApplicationName + 
				Constants.BUSINESS_COMMON_LOG_SEPARATOR +
				businessCommonServerPort +
				Constants.BUSINESS_COMMON_LOG_SEPARATOR +
				Constants.BUSINESS_COMMON_LOG_SUFFIX;
		
		// log输出路径（文件）：命令行目录+应用名称+端口号+后缀
		dailyRollingFileAppender.setFile(file);		
		// 设置日志的切断模式，每日一个日志文件
		dailyRollingFileAppender.setDatePattern(Constants.BUSINESS_COMMON_LOG_DATE_PATTERN);
		// 立即将日志输出到文件
		dailyRollingFileAppender.setImmediateFlush(true);
		// 适用当前配置  
		dailyRollingFileAppender.activateOptions(); 
		
    	// 清空Appender
    	rootLlogger.removeAllAppenders();     	
    	rootLlogger.addAppender(dailyRollingFileAppender);
    }    
    
    /**
     * 方法：获取环境变量，以及properties文件中的变量。
     * @param resourcePropertySource 属性文件对象
     * @param key 关键字
     * @return 属性内容
     * @throws ArgumentInputException 参数输入异常
     */
    private static String getProperty(ResourcePropertySource resourcePropertySource, Names key) throws ArgumentInputException
    {
    	if(resourcePropertySource == null)
    	{
    		logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "resourcePropertySource");
    		throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
    	}
    	
    	logger.debug(Hints.HINT_BUSINESS_COMMON_LOCAL_PARAMETER_VALUE + Constants.BUSINESS_COMMON_LAUNCHER_PREFIX + key.getName() + ", " +
    			resourcePropertySource.getProperty(Constants.BUSINESS_COMMON_LAUNCHER_PREFIX + key.getName()));
    	
    	return (String) resourcePropertySource.getProperty(Constants.BUSINESS_COMMON_LAUNCHER_PREFIX + key.getName());
    }
    
    /**
     * 方法：往基础参数管理器中增加参数。
     * @param type 类型
     * @param name 名称
     * @param value 值
     * @throws Exception 异常信息
     */
    public static void appendParameter(String type, String name, String value) throws Exception
    {
    	Parameters.synching();
		
		if(!Parameters.add(type, name, value))
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_UPDATE_PARAMETERS + "Type:" + type + ", Name:" + name);
			Parameters.invalid();
		}
		else 
		{
			logger.debug(Hints.HINT_BUSINESS_COMMON_UPDATE_PARAMETER_ITEM + "Type:" + type + ", Name:" + name);
			Parameters.valid();
		}
    }
}
