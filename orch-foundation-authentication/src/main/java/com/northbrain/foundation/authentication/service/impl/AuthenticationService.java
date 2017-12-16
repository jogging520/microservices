package com.northbrain.foundation.authentication.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONException;
import com.netflix.client.ClientException;
import com.northbrain.base.common.exception.PropertyEnumerationException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.basic.ServiceVO;
import com.northbrain.base.common.model.vo.orch.OrchRegistryVO;
import com.northbrain.base.common.util.StackTracerUtil;
import com.northbrain.foundation.authentication.domain.IAuthenticationDomain;
import com.northbrain.foundation.authentication.service.IAuthenticationService;

import feign.FeignException;

/**
 * 类名：鉴权服务接口的实现类
 * 用途：操作鉴权、权限、注册、登录等相关操作。
 * @author Jiakun
 * @version 1.0
 */
@Service
public class AuthenticationService implements IAuthenticationService
{
    private static Logger logger = Logger.getLogger(AuthenticationService.class);
    private final IAuthenticationDomain authenticationDomain;

    @Autowired
    public AuthenticationService(IAuthenticationDomain authenticationDomain)
    {
        this.authenticationDomain = authenticationDomain;
    }

    /**
     * 方法：注册
     *
     * @param orchRegistryVO 编排层注册值对象
     * @return 是否注册成功的ServiceVO封装对象
     */
    @Override
    public ServiceVO createRegistry(OrchRegistryVO orchRegistryVO)
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if (authenticationDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "authenticationDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(authenticationDomain.createRegistry(orchRegistryVO));
            serviceVO.setResponseCodeAndDesc(Errors.SUCCESS_EXECUTE);
        }
        catch (PropertyEnumerationException propertyEnumerationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(propertyEnumerationException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_VALUE_OBJECT_PROPERTY_ENUM_EXCEPTION);
        }
        catch (ClassCastException classCastException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(classCastException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_CLASS_CAST_EXCEPTION);
        }
        catch(IllegalStateException illegalStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_STATE_EXCEPTION);
        }
        catch (JSONException jSONException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(jSONException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_JSON_EXCEPTION);
        }
        catch (FeignException feignException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(feignException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FEIGN_EXCEPTION);
        }
        catch (ClientException clientException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(clientException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_CLIENT_EXCEPTION);
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
