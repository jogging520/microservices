package com.northbrain.common.security.dto.impl;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.AccessControlVO;
import com.northbrain.base.common.model.vo.PrivilegeVO;
import com.northbrain.common.security.dto.ISecurityDTO;
import com.northbrain.common.security.model.po.AccessControlPO;
import com.northbrain.common.security.model.po.PrivilegePO;
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

        privilegeVO.setPrivilegeId(privilegePO.getPrivilegeId());
        privilegeVO.setName(privilegePO.getName());
        privilegeVO.setDomain(privilegePO.getDomain());
        privilegeVO.setCategory(privilegePO.getCategory());
        privilegeVO.setType(privilegePO.getType());
        privilegeVO.setStatus(privilegePO.getStatus());
        privilegeVO.setCreateTime(privilegePO.getCreateTime());
        privilegeVO.setStatusTime(privilegePO.getStatusTime());
        privilegeVO.setDesciption(privilegePO.getDesciption());

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
        privilegePO.setDesciption(privilegeVO.getDesciption());

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

        accessControlVO.setAccessControlId(accessControlPO.getAccessControlId());
        accessControlVO.setRoleId(accessControlPO.getRoleId());
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
        accessControlPO.setPrivilegeId(accessControlVO.getPrivilegeId());
        accessControlPO.setStatus(accessControlVO.getStatus());
        accessControlPO.setCreateTime(accessControlVO.getCreateTime());
        accessControlPO.setStatusTime(accessControlVO.getStatusTime());

        return accessControlPO;
    }
}
