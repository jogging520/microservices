package com.northbrain.common.security.dto.impl;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.AccessControlVO;
import com.northbrain.base.common.model.vo.LoginVO;
import com.northbrain.base.common.model.vo.PrivilegeVO;
import com.northbrain.base.common.model.vo.RegistryVO;
import com.northbrain.common.security.dto.ISecurityDTO;
import com.northbrain.common.security.model.po.*;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 类名：安全传输对象接口的实现类
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class SecurityDTO implements ISecurityDTO
{
    private static Logger logger = Logger.getLogger(SecurityDTO.class);
    /**
     * 方法：将权限PO转换成VO
     *
     * @param privilegePO 权限持久化对象
     * @return 权限值对象
     */
    @Override
    public PrivilegeVO convertToPrivilegeVO(PrivilegePO privilegePO) throws Exception
    {
        if(privilegePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "privilegePO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        PrivilegeVO privilegeVO = new PrivilegeVO();

        privilegeVO.setRecordId(Constants.BUSINESS_COMMON_OPERATION_RECORD_DEFAULT_ID);
        privilegeVO.setPrivilegeId(privilegePO.getPrivilegeId());
        privilegeVO.setName(privilegePO.getName());
        privilegeVO.setDomain(privilegePO.getDomain());
        privilegeVO.setCategory(privilegePO.getCategory());
        privilegeVO.setType(privilegePO.getType());
        privilegeVO.setStatus(privilegePO.getStatus());
        privilegeVO.setCreateTime(privilegePO.getCreateTime());
        privilegeVO.setStatusTime(privilegePO.getStatusTime());
        privilegeVO.setDescription(privilegePO.getDescription());

        return privilegeVO;
    }

    /**
     * 方法：将权限VO转换成PO
     *
     * @param privilegeVO 权限值对象
     * @return 权限持久化对象
     */
    @Override
    public PrivilegePO convertToPrivilegePO(PrivilegeVO privilegeVO) throws Exception
    {
        if(privilegeVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "privilegeVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        PrivilegePO privilegePO = new PrivilegePO();

        privilegePO.setPrivilegeId(privilegeVO.getPrivilegeId());
        privilegePO.setName(privilegeVO.getName());
        privilegePO.setDomain(privilegeVO.getDomain());
        privilegePO.setCategory(privilegeVO.getCategory());
        privilegePO.setType(privilegeVO.getType());
        privilegePO.setStatus(privilegeVO.getStatus());
        privilegePO.setCreateTime(privilegeVO.getCreateTime());
        privilegePO.setStatusTime(privilegeVO.getStatusTime());
        privilegePO.setDescription(privilegeVO.getDescription());

        return privilegePO;
    }

    /**
     * 方法：将访问控制PO转换成VO
     *
     * @param accessControlPO 访问控制持久化对象
     * @return 访问控制值对象
     */
    @Override
    public AccessControlVO convertToAccessControlVO(AccessControlPO accessControlPO) throws Exception
    {
        if(accessControlPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "accessControlPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        AccessControlVO accessControlVO = new AccessControlVO();

        accessControlVO.setRecordId(Constants.BUSINESS_COMMON_OPERATION_RECORD_DEFAULT_ID);
        accessControlVO.setAccessControlId(accessControlPO.getAccessControlId());
        accessControlVO.setRoleId(accessControlPO.getRoleId());
        accessControlVO.setOrganizationId(accessControlPO.getOrganizationId());
        accessControlVO.setPrivilegeId(accessControlPO.getPrivilegeId());
        accessControlVO.setStatus(accessControlPO.getStatus());
        accessControlVO.setCreateTime(accessControlPO.getCreateTime());
        accessControlVO.setStatusTime(accessControlPO.getStatusTime());

        return accessControlVO;
    }

    /**
     * 方法：将权限控制VO转换成PO
     *
     * @param accessControlVO 权限控制值对象
     * @return 权限控制持久化对象
     */
    @Override
    public AccessControlPO convertToAccessControlPO(AccessControlVO accessControlVO) throws Exception
    {
        if(accessControlVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "accessControlVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        AccessControlPO accessControlPO = new AccessControlPO();

        accessControlPO.setAccessControlId(accessControlVO.getAccessControlId());
        accessControlPO.setRoleId(accessControlVO.getRoleId());
        accessControlPO.setOrganizationId(accessControlVO.getOrganizationId());
        accessControlPO.setPrivilegeId(accessControlVO.getPrivilegeId());
        accessControlPO.setStatus(accessControlVO.getStatus());
        accessControlPO.setCreateTime(accessControlVO.getCreateTime());
        accessControlPO.setStatusTime(accessControlVO.getStatusTime());

        return accessControlPO;
    }

    /**
     * 方法：将登录信息PO转换成VO
     *
     * @param loginPO 登录信息持久化对象
     * @return 登录信息值对象
     */
    @Override
    public LoginVO convertToLoginVO(LoginPO loginPO) throws Exception
    {
        if(loginPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "loginPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        LoginVO loginVO = new LoginVO();

        loginVO.setRecordId(Constants.BUSINESS_COMMON_OPERATION_RECORD_DEFAULT_ID);
        loginVO.setLoginId(loginPO.getLoginId());
        loginVO.setRegistryId(loginPO.getRegistryId());
        loginVO.setPartyId(loginPO.getPartyId());
        loginVO.setRoleId(loginPO.getRoleId());
        loginVO.setOrganizationId(loginPO.getOrganizationId());
        loginVO.setDomain(loginPO.getDomain());
        loginVO.setCategory(loginPO.getCategory());
        loginVO.setType(loginPO.getType());
        loginVO.setStatus(loginPO.getStatus());
        loginVO.setLoginTime(loginPO.getLoginTime());
        loginVO.setLogoutTime(loginPO.getLogoutTime());
        loginVO.setDescription(loginPO.getDescription());

        return loginVO;
    }

    /**
     * 方法：将登录信息VO转换成PO
     *
     * @param loginVO 登录信息值对象
     * @return 登录信息持久化对象
     */
    @Override
    public LoginPO convertToLoginPO(LoginVO loginVO) throws Exception
    {
        if(loginVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "loginVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        LoginPO loginPO = new LoginPO();

        loginPO.setLoginId(loginVO.getLoginId());
        loginPO.setRegistryId(loginVO.getRegistryId());
        loginPO.setPartyId(loginVO.getPartyId());
        loginPO.setRoleId(loginVO.getRoleId());
        loginPO.setOrganizationId(loginVO.getOrganizationId());
        loginPO.setDomain(loginVO.getDomain());
        loginPO.setCategory(loginVO.getCategory());
        loginPO.setType(loginVO.getType());
        loginPO.setStatus(loginVO.getStatus());
        loginPO.setLoginTime(loginVO.getLoginTime());
        loginPO.setLogoutTime(loginVO.getLogoutTime());
        loginPO.setDescription(loginVO.getDescription());

        return loginPO;
    }

    /**
     * 方法：将登录信息PO转换成历史PO
     *
     * @param recordId    操作记录编号
     * @param operateType 操作类型
     * @param loginPO     登录信息持久化对象
     * @return 登录信息历史持久化对象
     */
    @Override
    public LoginHisPO convertToLoginHisPO(Integer recordId, String operateType, LoginPO loginPO) throws Exception
    {
        if(recordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "recordId:" + recordId);
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(operateType == null || operateType.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operateType");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(loginPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "loginPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        LoginHisPO loginHisPO = new LoginHisPO();

        loginHisPO.setRecordId(recordId);
        loginHisPO.setOperateType(operateType);
        loginHisPO.setLoginId(loginPO.getLoginId());
        loginHisPO.setRegistryId(loginPO.getRegistryId());
        loginHisPO.setPartyId(loginPO.getPartyId());
        loginHisPO.setRoleId(loginPO.getRoleId());
        loginHisPO.setOrganizationId(loginPO.getOrganizationId());
        loginHisPO.setDomain(loginPO.getDomain());
        loginHisPO.setCategory(loginPO.getCategory());
        loginHisPO.setType(loginPO.getType());
        loginHisPO.setStatus(loginPO.getStatus());
        loginHisPO.setLoginTime(loginPO.getLoginTime());
        loginHisPO.setLogoutTime(loginPO.getLogoutTime());
        loginHisPO.setDescription(loginPO.getDescription());

        return loginHisPO;
    }

    /**
     * 方法：将注册信息PO转换成VO
     *
     * @param registryPO 注册信息持久化对象
     * @return 注册信息值对象
     */
    @Override
    public RegistryVO convertToRegistryVO(RegistryPO registryPO) throws Exception
    {
        if(registryPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "registryPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        RegistryVO registryVO = new RegistryVO();

        registryVO.setRegistryId(registryPO.getRegistryId());
        registryVO.setPartyId(registryPO.getPartyId());
        registryVO.setDomain(registryPO.getDomain());
        registryVO.setCategory(registryPO.getCategory());
        registryVO.setType(registryPO.getType());
        registryVO.setStatus(registryPO.getStatus());
        registryVO.setCreateTime(registryPO.getCreateTime());
        registryVO.setStatusTime(registryPO.getStatusTime());
        registryVO.setDescription(registryPO.getDescription());

        return registryVO;
    }

    /**
     * 方法：将注册信息VO转换成PO
     *
     * @param registryVO 注册信息值对象
     * @return 注册信息持久化对象
     */
    @Override
    public RegistryPO convertToRegistryPO(RegistryVO registryVO) throws Exception
    {
        if(registryVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "registryVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        RegistryPO registryPO = new RegistryPO();

        registryPO.setRegistryId(registryVO.getRegistryId());
        registryPO.setPartyId(registryVO.getPartyId());
        registryPO.setDomain(registryVO.getDomain());
        registryPO.setCategory(registryVO.getCategory());
        registryPO.setType(registryVO.getType());
        registryPO.setStatus(registryVO.getStatus());
        registryPO.setCreateTime(registryVO.getCreateTime());
        registryPO.setStatusTime(registryVO.getStatusTime());
        registryPO.setDescription(registryVO.getDescription());

        return registryPO;
    }

    /**
     * 方法：将注册信息PO转换成历史PO
     *
     * @param recordId    操作记录编号
     * @param operateType 操作类型
     * @param registryPO  注册信息持久化对象
     * @return 注册信息历史持久化对象
     */
    @Override
    public RegistryHisPO convertToRegistryHisPO(Integer recordId, String operateType, RegistryPO registryPO) throws Exception
    {
        if(recordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "recordId:" + recordId);
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(operateType == null || operateType.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operateType");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(registryPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "registryPO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        RegistryHisPO registryHisPO = new RegistryHisPO();

        registryHisPO.setRecordId(recordId);
        registryHisPO.setOperateType(operateType);
        registryHisPO.setRegistryId(registryPO.getRegistryId());
        registryHisPO.setPartyId(registryPO.getPartyId());
        registryHisPO.setDomain(registryPO.getDomain());
        registryHisPO.setCategory(registryPO.getCategory());
        registryHisPO.setType(registryPO.getType());
        registryHisPO.setStatus(registryPO.getStatus());
        registryHisPO.setCreateTime(registryPO.getCreateTime());
        registryHisPO.setStatusTime(registryPO.getStatusTime());
        registryHisPO.setDescription(registryPO.getDescription());

        return registryHisPO;
    }
}
