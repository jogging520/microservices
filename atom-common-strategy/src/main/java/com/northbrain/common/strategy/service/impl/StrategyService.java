package com.northbrain.common.strategy.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.northbrain.base.common.exception.*;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.base.common.model.vo.StrategyVO;
import com.northbrain.base.common.util.StackTracerUtil;
import com.northbrain.common.strategy.domain.IStrategyDomain;
import com.northbrain.common.strategy.exception.StrategyException;
import com.northbrain.common.strategy.service.IStrategyService;

/**
 * 类名：策略服务接口的实现类
 * 用途：封装策略等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
@Service
public class StrategyService implements IStrategyService
{
    private static Logger logger = Logger.getLogger(StrategyService.class);
    private final IStrategyDomain strategyDomain;

    @Autowired
    public StrategyService(IStrategyDomain strategyDomain)
    {
        this.strategyDomain = strategyDomain;
    }

    /**
     * 方法：创建一条策略
     *
     * @param strategyVO 策略值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    @Override
    @Transactional
    public ServiceVO createStrategy(StrategyVO strategyVO)
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(strategyVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "strategyVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return serviceVO;
            }

            if(strategyDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(strategyDomain.createStrategy(strategyVO));
            serviceVO.setResponseCodeAndDesc(Errors.SUCCESS_EXECUTE);
        }
        catch (NumberFormatException numberFormatException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberFormatException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_NUMBER_FORMAT_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (InterruptedException interruptedException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(interruptedException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_INTERRUPTED_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(fileNotFoundException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FILE_NOT_FOUND_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (IOException iOException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(iOException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_IO_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (IllegalAccessException illegalAccessException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalAccessException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ACCESS_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalArgumentException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ARGUMENT_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (ArgumentInputException argumentInputException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(argumentInputException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (CollectionEmptyException collectionEmptyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(collectionEmptyException));
            serviceVO.setResponseCodeAndDesc(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (NumberScopeException numberScopeException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberScopeException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (ObjectNullException objectNullException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(objectNullException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (ParameterConfigException parameterConfigException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parameterConfigException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (ParametersStateException parametersStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parametersStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_STATUS_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (RedisSessionException redisSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(redisSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (ThreadPoolExecutorException threadPoolExecutorException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(threadPoolExecutorException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_THREAD_POOL_EXECUTOR_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (ZooKeeperSessionException zooKeeperSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(zooKeeperSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (OperationRecordException operationRecordInsertException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(operationRecordInsertException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_RELATION_OPERATION_RECORD_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (BadSqlGrammarException badSqlGrammarException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(badSqlGrammarException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (StrategyException strategyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(strategyException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_SECURITY_STRATEGY_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (Throwable throwable)
        {
            logger.error(StackTracerUtil.getExceptionInfo(throwable));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        serviceVO.setResponseTime(new Date());

        return serviceVO;
    }
}
