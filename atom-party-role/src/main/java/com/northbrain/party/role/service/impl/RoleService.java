package com.northbrain.party.role.service.impl;

import com.northbrain.base.common.exception.*;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.RoleVO;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.base.common.util.StackTracerUtil;
import com.northbrain.party.role.domain.IRoleDomain;
import com.northbrain.party.role.service.IRoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 类名：角色服务接口的实现类
 * 用途：封装角色增删改查等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
@Service
public class RoleService implements IRoleService
{
    private static Logger logger = Logger.getLogger(RoleService.class);
    private final IRoleDomain roleDomain;

    @Autowired
    public RoleService(IRoleDomain roleDomain)
    {
        this.roleDomain = roleDomain;
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

            if(roleDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "roleDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            roleVOS = roleDomain.readRolesByName(name);
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
}
