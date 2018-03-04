package com.northbrain.general.gateway.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.orch.OrchAccessControlVO;
import com.northbrain.base.common.util.StackTracerUtil;
import com.northbrain.general.gateway.domain.IGatewayDomain;
import com.northbrain.general.gateway.service.IGatewayService;

/**
 * 类名：网关服务接口的实现类
 * 用途：操作访问控制等相关操作。
 * @author Jiakun
 * @version 1.0
 */
@Service
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
