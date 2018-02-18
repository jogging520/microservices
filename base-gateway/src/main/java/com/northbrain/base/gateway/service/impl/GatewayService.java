package com.northbrain.base.gateway.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONException;
import com.netflix.client.ClientException;
import com.northbrain.base.common.exception.*;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.basic.ResponseVO;
import com.northbrain.base.common.model.vo.basic.ServiceVO;
import com.northbrain.base.common.model.vo.orch.OrchAccessControlVO;
import com.northbrain.base.common.util.StackTracerUtil;
import com.northbrain.base.gateway.domain.IGatewayDomain;
import com.northbrain.base.gateway.service.IGatewayService;

import feign.FeignException;

/**
 * 类名：网关服务接口的实现类
 * 用途：操作访问控制等相关操作。
 * @author Jiakun
 * @version 1.0
 */
public class GatewayService implements IGatewayService
{
    private static Logger logger = Logger.getLogger(GatewayService.class);
    private final IGatewayDomain gatewayDomain;

    @Autowired
    public GatewayService(IGatewayDomain gatewayDomain)
    {
        this.gatewayDomain = gatewayDomain;
    }

    /**
     * 方法：访问控制
     *
     * @param orchAccessControlVO 编排层访问控制值对象
     * @return 是否允许访问
     * @throws Exception 异常
     */
    @Override
    public Boolean readAccessControl(OrchAccessControlVO orchAccessControlVO)
    {
        try
        {
            if (orchAccessControlVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "orchAccessControlVO");
                return false;
            }

            if (gatewayDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "gatewayDomain");
                return false;
            }

            return gatewayDomain.readAccessControl(orchAccessControlVO);
        }
        catch (PropertyEnumerationException propertyEnumerationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(propertyEnumerationException));
        }
        catch (ClassCastException classCastException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(classCastException));
        }
        catch (IllegalStateException illegalStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalStateException));
        }
        catch (InstantiationException instantiationException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(instantiationException));
        }
        catch (JSONException jSONException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(jSONException));
        }
        catch (FeignException feignException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(feignException));
        }
        catch (ClientException clientException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(clientException));
        }
        catch (ArgumentInputException argumentInputException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(argumentInputException));
        }
        catch (NumberScopeException numberScopeException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(numberScopeException));
        }
        catch (ObjectNullException objectNullException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(objectNullException));
        }
        catch (Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
        }
        catch (Throwable throwable)
        {
            logger.error(StackTracerUtil.getExceptionInfo(throwable));
        }

        return false;
    }
}
