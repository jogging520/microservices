package com.northbrain.general.gateway.domain.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.AccessControlException;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.basic.ResponseVO;
import com.northbrain.base.common.model.vo.orch.OrchAccessControlVO;
import com.northbrain.base.common.util.JsonTransformationUtil;
import com.northbrain.general.gateway.dao.IAuthenticationDAO;
import com.northbrain.general.gateway.domain.IGatewayDomain;

/**
 * 类名：网关域接口的实现类
 * 用途：实现网管相关业务逻辑
 * @author Jiakun
 * @version 1.0
 */
@Component
public class GatewayDomain implements IGatewayDomain
{
    private static Logger logger = Logger.getLogger(GatewayDomain.class);
    private final IAuthenticationDAO authenticationDAO;

    @Autowired
    public GatewayDomain(IAuthenticationDAO authenticationDAO)
    {
        this.authenticationDAO = authenticationDAO;
    }

    /**
     * 方法：访问控制
     * @param orchAccessControlVO 编排层访问控制值对象
     * @return 是否允许访问
     * @throws Exception 异常
     */
    @Override
    public boolean readAccessControl(OrchAccessControlVO orchAccessControlVO) throws Exception
    {
        if (orchAccessControlVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "orchAccessControlVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        ResponseVO<Boolean> loginResponseVO = readOrchAccessControl(orchAccessControlVO);

        if (loginResponseVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "loginResponseVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(!loginResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_ACCESS_CONTROL_EXCEPTION);
            throw new AccessControlException(Errors.ERROR_BUSINESS_COMMON_SECURITY_ACCESS_CONTROL_EXCEPTION);
        }

        return loginResponseVO.getResponse();
    }

    /**
     * 方法：调用编排层服务进行访问控制鉴权
     * @param orchAccessControlVO 编排层访问控制值对象
     * @return 登录信息
     * @throws Exception 异常
     */
    private ResponseVO<Boolean> readOrchAccessControl(OrchAccessControlVO orchAccessControlVO) throws Exception
    {
        if (orchAccessControlVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "orchAccessControlVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (authenticationDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "authenticationDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JsonTransformationUtil.transformJSONString(this.authenticationDAO.readOrchAccessControl(orchAccessControlVO));
    }
}