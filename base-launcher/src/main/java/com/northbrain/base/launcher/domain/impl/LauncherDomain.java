package com.northbrain.base.launcher.domain.impl;

import com.northbrain.base.launcher.dao.IPlaceHolderDAO;
import com.northbrain.base.launcher.dao.impl.PlaceHolderDAO;
import com.northbrain.base.common.exception.ParametersStateException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.launcher.model.po.PlaceHolderPO;
import com.northbrain.base.launcher.dao.IParameterDAO;
import com.northbrain.base.launcher.dao.impl.ParameterDAO;
import com.northbrain.base.launcher.domain.ILauncherDomain;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.exception.ParameterConfigException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.util.StackTracerUtil;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.stereotype.Component;

/**
 * 类名：启动领域接口的实现类
 * 用途：启动占位符替换等工作，需要替换的是敏感信息如密码，以及经常变更的内容。同时启动监听ZooKeeper节点，以同步至Parameters。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class LauncherDomain implements ILauncherDomain, BeanDefinitionRegistryPostProcessor
{
    private static Logger logger = Logger.getLogger(LauncherDomain.class);

    /**
     * 方法：完成bean创建后对其部分占位符值进行替换，占位符的取值来自远端ZooKeeper。
     * 如果需要新增占位符，需同时修改Placeholder类的属性。
     * 初始化的参数是从properties文件读取出来的，由Launcher类封装。
     * @param beanFactory bean工厂
     * @exception BeansException 异常
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)	throws BeansException
    {
    	String storageZooKeeperConfigPath;

        BeanDefinition beanDefinition;
        MutablePropertyValues mutablePropertyValues;

        IPlaceHolderDAO placeHolderDAO;
        IParameterDAO parameterDAO;
        PlaceHolderPO placeHolderPO;

        try
        {
        	//第一步：在bean初始化前将数据库等远端信息增加值上下文配置中。
            storageZooKeeperConfigPath = Constants.BUSINESS_COMMON_NODE_SEPARATOR +
            		Constants.STORAGE_ZOOKEEPER_CONFIG_NAMESPACE +
            		Constants.BUSINESS_COMMON_NODE_SEPARATOR;

            beanDefinition = beanFactory.getBeanDefinition(Constants.SYSTEM_SPRING_DATASOURCE_BEAN_NAME);

            if(beanDefinition == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "beanDefinition");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            mutablePropertyValues = beanDefinition.getPropertyValues();

            if(mutablePropertyValues == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "mutablePropertyValues");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            logger.debug(Hints.HINT_BUSINESS_COMMON_DYNAMIC_ADD_REMOTE_PROPERTIES);

            placeHolderDAO = new PlaceHolderDAO();
            placeHolderPO = placeHolderDAO.getPlaceholder(storageZooKeeperConfigPath);

            if(placeHolderPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "placeHolderPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            //默认使用：org.apache.tomcat.jdbc.pool.DataSource
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_DRIVER_CLASS_NAME_PROPERTY_NAME, placeHolderPO.getJdbcDriverClassName());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_URL_PROPERTY_NAME, placeHolderPO.getJdbcUrl());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_USER_NAME_PROPERTY_NAME, placeHolderPO.getJdbcUserName());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_PASSWORD_PROPERTY_NAME, placeHolderPO.getJdbcPassword());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_DEFAULT_AUTO_COMMIT_PROPERTY_NAME, placeHolderPO.isJdbcDefaultAutoCommit());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_INITIAL_SIZE_PROPERTY_NAME, placeHolderPO.getJdbcInitialSize());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_MAX_ACTIVE_PROPERTY_NAME, placeHolderPO.getJdbcMaxActive());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_MAX_IDLE_PROPERTY_NAME, placeHolderPO.getJdbcMaxIdle());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_MIN_IDLE_PROPERTY_NAME, placeHolderPO.getJdbcMinIdle());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_MAX_WAIT_PROPERTY_NAME, placeHolderPO.getJdbcMaxWait());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_VALIDATION_QUERY_PROPERTY_NAME, placeHolderPO.getJdbcValidationQuery());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_TEST_ON_BORROW_PROPERTY_NAME, placeHolderPO.isJdbcTestOnBorrow());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_TEST_ON_RETURN_PROPERTY_NAME, placeHolderPO.isJdbcTestOnReturn());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_TEST_WHILE_IDLE_PROPERTY_NAME, placeHolderPO.isJdbcTestWhileIdle());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_TIME_BETWEEN_EVICTION_RUNS_MILLIS_PROPERTY_NAME, placeHolderPO.getJdbcTimeBetweenEvictionRunsMillis());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_MIN_EVICTABLE_IDLE_TIME_MILLIS_PROPERTY_NAME, placeHolderPO.getJdbcMinEvictableIdleTimeMillis());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_REMOVE_ABANDONED_PROPERTY_NAME, placeHolderPO.isJdbcRemoveAbandoned());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_REMOVE_ABANDONED_TIMEOUT_PROPERTY_NAME, placeHolderPO.getJdbcRemoveAbandonedTimeout());
            mutablePropertyValues.add(Constants.STORAGE_DATABASE_JDBC_VALIDATION_INTERVAL_PROPERTY_NAME, placeHolderPO.getJdbcValidationInterval());

    		//第二步：监听zookeeper配置子路径，任何事件发生将会同步本地。
    		parameterDAO = new ParameterDAO();

    		parameterDAO.sync(storageZooKeeperConfigPath + Constants.STORAGE_ZOOKEEPER_BUSINESS_NAMESPACE, true);
    		parameterDAO.sync(storageZooKeeperConfigPath + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE, true);
    		parameterDAO.sync(storageZooKeeperConfigPath + Constants.STORAGE_ZOOKEEPER_SYSTEM_NAMESPACE, true);
        }
        catch (ParametersStateException | ParameterConfigException | ArgumentInputException parametersStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parametersStateException));
            throw new RuntimeException(parametersStateException);
        }
        catch (IOException ioexception)
        {
        	logger.error(Errors.ERROR_SYSTEM_IO_EXCEPTION);
            throw new RuntimeException(ioexception);
        }
        catch (Exception exception)
        {
            logger.error(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
            throw new RuntimeException(exception);
        }
    }
    
    /**
     * 方法：在标准初始化之后，修改上下文中定义的bean注册信息。
     * Modify the application context's internal bean definition registry after its standard initialization.
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException 
    {
        logger.debug(Hints.HINT_SYSTEM_SPRING_BEAN_DEFINITION_REGISTRY);
        
        AnnotatedGenericBeanDefinition annotatedGenericBeanDefinition = new AnnotatedGenericBeanDefinition(org.apache.tomcat.jdbc.pool.DataSource.class);
        
        AnnotationConfigUtils.processCommonDefinitionAnnotations(annotatedGenericBeanDefinition);
        
        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(annotatedGenericBeanDefinition, Constants.SYSTEM_SPRING_DATASOURCE_BEAN_NAME);
        
        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, beanDefinitionRegistry);
    }
}
