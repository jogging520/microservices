package com.northbrain.base.launcher.dao.impl;

import com.northbrain.base.launcher.dao.IPlaceHolderDAO;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Names;
import com.northbrain.base.launcher.model.po.PlaceHolderPO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 类名：占位符DAO接口
 * 用途：存取占位符，如数据库密码等。只读取，不写入。
 * 为避免敏感数据的本地存储，这些信息均在远端存储，实现统一管理。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class PlaceHolderDAO extends BaseZooKeeperDAO implements IPlaceHolderDAO
{
    private static Logger logger = Logger.getLogger(PlaceHolderDAO.class);

    /**
     * 方法：获取占位符信息
     * @param placeHolderNamespace 占位符命名空间
     * @return 占位符信息
     */
    @Override
    public PlaceHolderPO getPlaceholder(String placeHolderNamespace) throws Exception
    {
        if(placeHolderNamespace == null || placeHolderNamespace.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "placeHolderNamespace");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        PlaceHolderPO placeHolderPO = new PlaceHolderPO();

        placeHolderPO.setJdbcDriverClassName(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_DRIVER_CLASS_NAME.getName()));
        placeHolderPO.setJdbcUrl(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_URL.getName()));
        placeHolderPO.setJdbcUserName(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_USER_NAME.getName()));
        placeHolderPO.setJdbcPassword(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_PASSWORD.getName()));
        placeHolderPO.setJdbcDefaultAutoCommit(Constants.matchBoolean(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_DEFAULT_AUTO_COMMIT.getName())));
        placeHolderPO.setJdbcInitialSize(Integer.parseInt(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_INITIAL_SIZE.getName())));
        placeHolderPO.setJdbcMaxActive(Integer.parseInt(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_MAX_ACTIVE.getName())));
        placeHolderPO.setJdbcMaxIdle(Integer.parseInt(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_MAX_IDLE.getName())));
        placeHolderPO.setJdbcMinIdle(Integer.parseInt(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_MIN_IDLE.getName())));
        placeHolderPO.setJdbcMaxWait(Integer.parseInt(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_MAX_WAIT.getName())));
        placeHolderPO.setJdbcValidationQuery(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_VALIDATION_QUERY.getName()));
        placeHolderPO.setJdbcTestOnBorrow(Constants.matchBoolean(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_TEST_ON_BORROW.getName())));
        placeHolderPO.setJdbcTestOnReturn(Constants.matchBoolean(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_TEST_ON_RETURN.getName())));
        placeHolderPO.setJdbcTestWhileIdle(Constants.matchBoolean(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_TEST_WHILE_IDLE.getName())));
        placeHolderPO.setJdbcTimeBetweenEvictionRunsMillis(Integer.parseInt(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_TIME_BETWEEN_EVICTION_RUNS_MILLIS.getName())));
        placeHolderPO.setJdbcMinEvictableIdleTimeMillis(Integer.parseInt(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_MIN_EVICTABLE_IDLE_TIME_MILLIS.getName())));
        placeHolderPO.setJdbcRemoveAbandoned(Constants.matchBoolean(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_REMOVE_ABANDONED.getName())));
        placeHolderPO.setJdbcRemoveAbandonedTimeout(Integer.parseInt(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_REMOVE_ABANDONED_TIMEOUT.getName())));
        placeHolderPO.setJdbcValidationInterval(Long.parseLong(super.getData(placeHolderNamespace + Constants.STORAGE_ZOOKEEPER_STORAGE_NAMESPACE + Constants.BUSINESS_COMMON_NODE_SEPARATOR + Names.STORAGE_DATABASE_JDBC_VALIDATION_INTERVAL.getName())));

        return placeHolderPO;
    }
}
