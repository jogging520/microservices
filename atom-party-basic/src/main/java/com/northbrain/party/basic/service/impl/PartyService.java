package com.northbrain.party.basic.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.northbrain.base.common.exception.*;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.*;
import com.northbrain.base.common.util.StackTracerUtil;
import com.northbrain.party.basic.domain.IPartyDomain;
import com.northbrain.party.basic.exception.OrganizationException;
import com.northbrain.party.basic.exception.PartyException;
import com.northbrain.party.basic.exception.RoleException;
import com.northbrain.party.basic.exception.SubjectionException;
import com.northbrain.party.basic.service.IPartyService;

/**
 * 类名：参与者服务接口的实现类
 * 用途：封装参与者增删改查等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
@Service
public class PartyService implements IPartyService
{
    private static Logger logger = Logger.getLogger(PartyService.class);

    private final IPartyDomain partyDomain;

    @Autowired
    public PartyService(IPartyDomain partyDomain)
    {
        this.partyDomain = partyDomain;
    }

    /**
     * 方法：根据名称获取权限清单
     *
     * @param name 角色名称
     * @return ServiceVO封装类
     */
    @Override
    public ServiceVO readRolesByName(String name)
    {
        ServiceVO serviceVO = new ServiceVO();
        List<RoleVO> roleVOS;

        try
        {
            if(name == null || name.equals(""))
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + name);
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return serviceVO;
            }

            if(partyDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            roleVOS = partyDomain.readRolesByName(name);
            serviceVO.setResponse(roleVOS);
            serviceVO.setResponseCodeAndDesc(Errors.SUCCESS_EXECUTE);
        }
        catch (NumberFormatException numberFormatException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberFormatException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_NUMBER_FORMAT_EXCEPTION);
        }
        catch (InterruptedException interruptedException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(interruptedException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_INTERRUPTED_EXCEPTION);
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(fileNotFoundException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FILE_NOT_FOUND_EXCEPTION);
        }
        catch (IOException iOException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(iOException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_IO_EXCEPTION);
        }
        catch (IllegalAccessException illegalAccessException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalAccessException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ACCESS_EXCEPTION);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalArgumentException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ARGUMENT_EXCEPTION);
        }
        catch (ArgumentInputException argumentInputException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(argumentInputException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        catch (CollectionEmptyException collectionEmptyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(collectionEmptyException));
            serviceVO.setResponseCodeAndDesc(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
        }
        catch (NumberScopeException numberScopeException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberScopeException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }
        catch (ObjectNullException objectNullException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(objectNullException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }
        catch (ParameterConfigException parameterConfigException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parameterConfigException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
        catch (ParametersStateException parametersStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parametersStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_STATUS_EXCEPTION);
        }
        catch (RedisSessionException redisSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(redisSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }
        catch (ThreadPoolExecutorException threadPoolExecutorException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(threadPoolExecutorException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_THREAD_POOL_EXECUTOR_EXCEPTION);
        }
        catch (ZooKeeperSessionException zooKeeperSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(zooKeeperSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }
        catch (BadSqlGrammarException badSqlGrammarException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(badSqlGrammarException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION);
        }
        catch (PartyException partyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(partyException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
        }
        catch (RoleException roleException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(roleException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
        }
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
        }
        catch (Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }
        catch (Throwable throwable)
        {
            logger.error(StackTracerUtil.getExceptionInfo(throwable));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }

        serviceVO.setResponseTime(new Date());

        return serviceVO;
    }

    /**
     * 方法：新建一条角色，根据RoleVO再转换成相应的PO
     *
     * @param roleVO 角色值对象
     * @return ServiceVO封装类
     */
    @Override
    @Transactional
    public ServiceVO createRole(RoleVO roleVO)
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(roleVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "roleVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return serviceVO;
            }

            if(partyDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(partyDomain.createRole(roleVO));
            serviceVO.setResponseCodeAndDesc(Errors.SUCCESS_EXECUTE);
        }
        catch (NumberFormatException numberFormatException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberFormatException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_NUMBER_FORMAT_EXCEPTION);
        }
        catch (InterruptedException interruptedException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(interruptedException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_INTERRUPTED_EXCEPTION);
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(fileNotFoundException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FILE_NOT_FOUND_EXCEPTION);
        }
        catch (IOException iOException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(iOException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_IO_EXCEPTION);
        }
        catch (IllegalAccessException illegalAccessException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalAccessException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ACCESS_EXCEPTION);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalArgumentException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ARGUMENT_EXCEPTION);
        }
        catch (ArgumentInputException argumentInputException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(argumentInputException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        catch (CollectionEmptyException collectionEmptyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(collectionEmptyException));
            serviceVO.setResponseCodeAndDesc(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
        }
        catch (NumberScopeException numberScopeException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberScopeException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }
        catch (ObjectNullException objectNullException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(objectNullException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }
        catch (ParameterConfigException parameterConfigException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parameterConfigException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
        catch (ParametersStateException parametersStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parametersStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_STATUS_EXCEPTION);
        }
        catch (RedisSessionException redisSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(redisSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }
        catch (ThreadPoolExecutorException threadPoolExecutorException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(threadPoolExecutorException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_THREAD_POOL_EXECUTOR_EXCEPTION);
        }
        catch (ZooKeeperSessionException zooKeeperSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(zooKeeperSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }
        catch (BadSqlGrammarException badSqlGrammarException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(badSqlGrammarException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION);
        }
        catch (PartyException partyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(partyException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
        }
        catch (RoleException roleException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(roleException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
        }
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
        }
        catch (Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }
        catch (Throwable throwable)
        {
            logger.error(StackTracerUtil.getExceptionInfo(throwable));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }

        serviceVO.setResponseTime(new Date());

        return serviceVO;
    }

    /**
     * 方法：更新一条角色，根据RoleVO再转换成相应的PO
     *
     * @param roleVO 角色值对象
     * @return ServiceVO封装类
     */
    @Override
    @Transactional
    public ServiceVO updateRole(RoleVO roleVO)
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(roleVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "roleVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return serviceVO;
            }

            if(partyDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(partyDomain.updateRole(roleVO));
            serviceVO.setResponseCodeAndDesc(Errors.SUCCESS_EXECUTE);
        }
        catch (NumberFormatException numberFormatException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberFormatException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_NUMBER_FORMAT_EXCEPTION);
        }
        catch (InterruptedException interruptedException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(interruptedException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_INTERRUPTED_EXCEPTION);
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(fileNotFoundException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FILE_NOT_FOUND_EXCEPTION);
        }
        catch (IOException iOException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(iOException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_IO_EXCEPTION);
        }
        catch (IllegalAccessException illegalAccessException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalAccessException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ACCESS_EXCEPTION);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalArgumentException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ARGUMENT_EXCEPTION);
        }
        catch (ArgumentInputException argumentInputException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(argumentInputException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        catch (CollectionEmptyException collectionEmptyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(collectionEmptyException));
            serviceVO.setResponseCodeAndDesc(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
        }
        catch (NumberScopeException numberScopeException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberScopeException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }
        catch (ObjectNullException objectNullException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(objectNullException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }
        catch (ParameterConfigException parameterConfigException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parameterConfigException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
        catch (ParametersStateException parametersStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parametersStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_STATUS_EXCEPTION);
        }
        catch (RedisSessionException redisSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(redisSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }
        catch (ThreadPoolExecutorException threadPoolExecutorException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(threadPoolExecutorException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_THREAD_POOL_EXECUTOR_EXCEPTION);
        }
        catch (ZooKeeperSessionException zooKeeperSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(zooKeeperSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }
        catch (BadSqlGrammarException badSqlGrammarException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(badSqlGrammarException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION);
        }
        catch (PartyException partyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(partyException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
        }
        catch (RoleException roleException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(roleException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
        }
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
        }
        catch (Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }
        catch (Throwable throwable)
        {
            logger.error(StackTracerUtil.getExceptionInfo(throwable));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }

        serviceVO.setResponseTime(new Date());

        return serviceVO;
    }

    /**
     * 方法：删除一条角色，根据RoleVO再转换成相应的PO
     *
     * @param roleVO 角色值对象
     * @return ServiceVO封装类
     */
    @Override
    @Transactional
    public ServiceVO deleteRole(RoleVO roleVO)
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(roleVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "roleVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return serviceVO;
            }

            if(partyDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(partyDomain.deleteRole(roleVO));
            serviceVO.setResponseCodeAndDesc(Errors.SUCCESS_EXECUTE);
        }
        catch (NumberFormatException numberFormatException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberFormatException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_NUMBER_FORMAT_EXCEPTION);
        }
        catch (InterruptedException interruptedException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(interruptedException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_INTERRUPTED_EXCEPTION);
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(fileNotFoundException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FILE_NOT_FOUND_EXCEPTION);
        }
        catch (IOException iOException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(iOException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_IO_EXCEPTION);
        }
        catch (IllegalAccessException illegalAccessException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalAccessException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ACCESS_EXCEPTION);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalArgumentException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ARGUMENT_EXCEPTION);
        }
        catch (ArgumentInputException argumentInputException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(argumentInputException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        catch (CollectionEmptyException collectionEmptyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(collectionEmptyException));
            serviceVO.setResponseCodeAndDesc(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
        }
        catch (NumberScopeException numberScopeException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberScopeException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }
        catch (ObjectNullException objectNullException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(objectNullException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }
        catch (ParameterConfigException parameterConfigException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parameterConfigException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
        catch (ParametersStateException parametersStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parametersStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_STATUS_EXCEPTION);
        }
        catch (RedisSessionException redisSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(redisSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }
        catch (ThreadPoolExecutorException threadPoolExecutorException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(threadPoolExecutorException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_THREAD_POOL_EXECUTOR_EXCEPTION);
        }
        catch (ZooKeeperSessionException zooKeeperSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(zooKeeperSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }
        catch (BadSqlGrammarException badSqlGrammarException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(badSqlGrammarException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION);
        }
        catch (PartyException partyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(partyException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
        }
        catch (RoleException roleException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(roleException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
        }
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
        }
        catch (Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }
        catch (Throwable throwable)
        {
            logger.error(StackTracerUtil.getExceptionInfo(throwable));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }

        serviceVO.setResponseTime(new Date());

        return serviceVO;
    }

    /**
     * 方法：新建一条参与者，根据PartyVO再转换成相应的PO
     *
     * @param partyVO 参与者值对象
     * @return ServiceVO封装类
     */
    @Override
    @Transactional
    public ServiceVO createParty(PartyVO partyVO)
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(partyVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "partyVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return serviceVO;
            }

            if(partyDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(partyDomain.createParty(partyVO));
            serviceVO.setResponseCodeAndDesc(Errors.SUCCESS_EXECUTE);
        }
        catch (NumberFormatException numberFormatException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberFormatException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_NUMBER_FORMAT_EXCEPTION);
        }
        catch (InterruptedException interruptedException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(interruptedException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_INTERRUPTED_EXCEPTION);
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(fileNotFoundException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FILE_NOT_FOUND_EXCEPTION);
        }
        catch (IOException iOException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(iOException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_IO_EXCEPTION);
        }
        catch (IllegalAccessException illegalAccessException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalAccessException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ACCESS_EXCEPTION);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalArgumentException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ARGUMENT_EXCEPTION);
        }
        catch (ArgumentInputException argumentInputException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(argumentInputException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        catch (CollectionEmptyException collectionEmptyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(collectionEmptyException));
            serviceVO.setResponseCodeAndDesc(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
        }
        catch (NumberScopeException numberScopeException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberScopeException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }
        catch (ObjectNullException objectNullException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(objectNullException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }
        catch (ParameterConfigException parameterConfigException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parameterConfigException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
        catch (ParametersStateException parametersStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parametersStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_STATUS_EXCEPTION);
        }
        catch (RedisSessionException redisSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(redisSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }
        catch (ThreadPoolExecutorException threadPoolExecutorException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(threadPoolExecutorException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_THREAD_POOL_EXECUTOR_EXCEPTION);
        }
        catch (ZooKeeperSessionException zooKeeperSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(zooKeeperSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }
        catch (BadSqlGrammarException badSqlGrammarException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(badSqlGrammarException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION);
        }
        catch (PartyException partyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(partyException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
        }
        catch (RoleException roleException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(roleException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
        }
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
        }
        catch (Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }
        catch (Throwable throwable)
        {
            logger.error(StackTracerUtil.getExceptionInfo(throwable));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }

        serviceVO.setResponseTime(new Date());

        return serviceVO;
    }

    /**
     * 方法：更新一条参与者，根据PartyVO再转换成相应的PO
     *
     * @param partyVO 参与者值对象
     * @return ServiceVO封装类
     */
    @Override
    @Transactional
    public ServiceVO updateParty(PartyVO partyVO)
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(partyVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "partyVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return serviceVO;
            }

            if(partyDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(partyDomain.updateParty(partyVO));
            serviceVO.setResponseCodeAndDesc(Errors.SUCCESS_EXECUTE);
        }
        catch (NumberFormatException numberFormatException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberFormatException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_NUMBER_FORMAT_EXCEPTION);
        }
        catch (InterruptedException interruptedException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(interruptedException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_INTERRUPTED_EXCEPTION);
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(fileNotFoundException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FILE_NOT_FOUND_EXCEPTION);
        }
        catch (IOException iOException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(iOException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_IO_EXCEPTION);
        }
        catch (IllegalAccessException illegalAccessException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalAccessException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ACCESS_EXCEPTION);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalArgumentException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ARGUMENT_EXCEPTION);
        }
        catch (ArgumentInputException argumentInputException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(argumentInputException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        catch (CollectionEmptyException collectionEmptyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(collectionEmptyException));
            serviceVO.setResponseCodeAndDesc(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
        }
        catch (NumberScopeException numberScopeException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberScopeException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }
        catch (ObjectNullException objectNullException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(objectNullException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }
        catch (ParameterConfigException parameterConfigException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parameterConfigException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
        catch (ParametersStateException parametersStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parametersStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_STATUS_EXCEPTION);
        }
        catch (RedisSessionException redisSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(redisSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }
        catch (ThreadPoolExecutorException threadPoolExecutorException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(threadPoolExecutorException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_THREAD_POOL_EXECUTOR_EXCEPTION);
        }
        catch (ZooKeeperSessionException zooKeeperSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(zooKeeperSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }
        catch (BadSqlGrammarException badSqlGrammarException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(badSqlGrammarException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION);
        }
        catch (PartyException partyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(partyException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
        }
        catch (RoleException roleException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(roleException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
        }
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
        }
        catch (Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }
        catch (Throwable throwable)
        {
            logger.error(StackTracerUtil.getExceptionInfo(throwable));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }

        serviceVO.setResponseTime(new Date());

        return serviceVO;
    }

    /**
     * 方法：删除一条参与者，根据PartyVO再转换成相应的PO
     *
     * @param partyVO 参与者值对象
     * @return ServiceVO封装类
     */
    @Override
    @Transactional
    public ServiceVO deleteParty(PartyVO partyVO)
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(partyVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "partyVO");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);

                return serviceVO;
            }

            if(partyDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(partyDomain.deleteParty(partyVO));
            serviceVO.setResponseCodeAndDesc(Errors.SUCCESS_EXECUTE);
        }
        catch (NumberFormatException numberFormatException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberFormatException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_NUMBER_FORMAT_EXCEPTION);
        }
        catch (InterruptedException interruptedException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(interruptedException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_INTERRUPTED_EXCEPTION);
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(fileNotFoundException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FILE_NOT_FOUND_EXCEPTION);
        }
        catch (IOException iOException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(iOException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_IO_EXCEPTION);
        }
        catch (IllegalAccessException illegalAccessException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalAccessException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ACCESS_EXCEPTION);
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalArgumentException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ARGUMENT_EXCEPTION);
        }
        catch (ArgumentInputException argumentInputException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(argumentInputException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
        catch (CollectionEmptyException collectionEmptyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(collectionEmptyException));
            serviceVO.setResponseCodeAndDesc(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
        }
        catch (NumberScopeException numberScopeException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberScopeException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }
        catch (ObjectNullException objectNullException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(objectNullException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }
        catch (ParameterConfigException parameterConfigException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parameterConfigException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
        catch (ParametersStateException parametersStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(parametersStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_STATUS_EXCEPTION);
        }
        catch (RedisSessionException redisSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(redisSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }
        catch (ThreadPoolExecutorException threadPoolExecutorException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(threadPoolExecutorException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_THREAD_POOL_EXECUTOR_EXCEPTION);
        }
        catch (ZooKeeperSessionException zooKeeperSessionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(zooKeeperSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }
        catch (BadSqlGrammarException badSqlGrammarException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(badSqlGrammarException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION);
        }
        catch (PartyException partyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(partyException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
        }
        catch (RoleException roleException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(roleException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
        }
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
        }
        catch (Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }
        catch (Throwable throwable)
        {
            logger.error(StackTracerUtil.getExceptionInfo(throwable));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }

        serviceVO.setResponseTime(new Date());

        return serviceVO;
    }

    /**
     * 方法：创建一条组织机构
     *
     * @param organizationVO 组织机构值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    @Override
    @Transactional
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

            if(partyDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(partyDomain.createOrganization(organizationVO));
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
        catch (BadSqlGrammarException badSqlGrammarException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(badSqlGrammarException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (PartyException partyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(partyException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
        }
        catch (RoleException roleException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(roleException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
        }
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
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
    @Transactional
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

            if(partyDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(partyDomain.updateOrganization(organizationVO));
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
        catch (BadSqlGrammarException badSqlGrammarException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(badSqlGrammarException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (PartyException partyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(partyException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
        }
        catch (RoleException roleException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(roleException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
        }
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
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
    @Transactional
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

            if(partyDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(partyDomain.deleteOrganization(organizationVO));
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
        catch (BadSqlGrammarException badSqlGrammarException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(badSqlGrammarException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (PartyException partyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(partyException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
        }
        catch (RoleException roleException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(roleException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
        }
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
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
    @Transactional
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

            if(partyDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(partyDomain.createSubjection(subjectionVO));
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
        catch (BadSqlGrammarException badSqlGrammarException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(badSqlGrammarException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (PartyException partyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(partyException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
        }
        catch (RoleException roleException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(roleException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
        }
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
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
    @Transactional
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

            if(partyDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(partyDomain.updateSubjection(subjectionVO));
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
        catch (BadSqlGrammarException badSqlGrammarException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(badSqlGrammarException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (PartyException partyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(partyException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
        }
        catch (RoleException roleException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(roleException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
        }
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
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
    @Transactional
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

            if(partyDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(partyDomain.deleteSubjection(subjectionVO));
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
        catch (BadSqlGrammarException badSqlGrammarException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(badSqlGrammarException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_BAD_SQL_GRAMMAR_EXCEPTION);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (PartyException partyException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(partyException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
        }
        catch (RoleException roleException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(roleException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ROLE_EXCEPTION);
        }
        catch (OrganizationException organizationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(organizationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_ORGANIZATION_EXCEPTION);
        }
        catch (SubjectionException subjectionException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(subjectionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_PARTY_SUBJECTION_EXCEPTION);
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
