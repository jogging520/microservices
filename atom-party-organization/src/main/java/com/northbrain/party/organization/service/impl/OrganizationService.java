package com.northbrain.party.organization.service.impl;

import com.northbrain.base.common.exception.*;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.OrganizationVO;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.base.common.model.vo.SubjectionVO;
import com.northbrain.base.common.util.StackTracerUtil;
import com.northbrain.party.organization.domain.IOrganizationDomain;
import com.northbrain.party.organization.exception.OrganizationException;
import com.northbrain.party.organization.exception.SubjectionException;
import com.northbrain.party.organization.service.IOrganizationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * 类名：组织机构和隶属关系服务接口的实现类
 * 用途：封装组织机构等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
@Service
public class OrganizationService implements IOrganizationService
{
    private static Logger logger = Logger.getLogger(OrganizationService.class);

    private final IOrganizationDomain organizationDomain;

    @Autowired
    public OrganizationService(IOrganizationDomain organizationDomain)
    {
        this.organizationDomain = organizationDomain;
    }

    /**
     * 方法：创建一条组织机构
     *
     * @param organizationVO 组织机构值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    @Override
    public ServiceVO createOrganization(OrganizationVO organizationVO)
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(organizationVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "organizationVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return serviceVO;
            }

            if(organizationDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(organizationDomain.createOrganization(organizationVO));
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
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
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

    /**
     * 方法：更新一条组织机构
     *
     * @param organizationVO 组织机构值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    @Override
    public ServiceVO updateOrganization(OrganizationVO organizationVO)
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(organizationVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "organizationVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return serviceVO;
            }

            if(organizationDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(organizationDomain.updateOrganization(organizationVO));
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
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
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

    /**
     * 方法：删除一条组织机构
     *
     * @param organizationVO 组织机构值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    @Override
    public ServiceVO deleteOrganization(OrganizationVO organizationVO)
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(organizationVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "organizationVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return serviceVO;
            }

            if(organizationDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(organizationDomain.deleteOrganization(organizationVO));
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
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
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

    /**
     * 方法：创建一条隶属关系
     *
     * @param subjectionVO 隶属关系值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    @Override
    public ServiceVO createSubjection(SubjectionVO subjectionVO)
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(subjectionVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "subjectionVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return serviceVO;
            }

            if(organizationDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(organizationDomain.createSubjection(subjectionVO));
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
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
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

    /**
     * 方法：更新一条隶属关系
     *
     * @param subjectionVO 隶属关系值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    @Override
    public ServiceVO updateSubjection(SubjectionVO subjectionVO)
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(subjectionVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "subjectionVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return serviceVO;
            }

            if(organizationDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(organizationDomain.updateSubjection(subjectionVO));
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
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
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

    /**
     * 方法：删除一条隶属关系
     *
     * @param subjectionVO 隶属关系值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    @Override
    public ServiceVO deleteSubjection(SubjectionVO subjectionVO)
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(subjectionVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "subjectionVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return serviceVO;
            }

            if(organizationDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "organizationDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(organizationDomain.deleteSubjection(subjectionVO));
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
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
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
