package com.northbrain.common.security.domain.impl;

import com.northbrain.base.common.exception.CollectionEmptyException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.AccessControlVO;
import com.northbrain.base.common.model.vo.PrivilegeVO;
import com.northbrain.common.security.dao.AccessControlHisPOMapper;
import com.northbrain.common.security.dao.AccessControlPOMapper;
import com.northbrain.common.security.dao.PrivilegeHisPOMapper;
import com.northbrain.common.security.dao.PrivilegePOMapper;
import com.northbrain.common.security.domain.ISecurityDomain;
import com.northbrain.common.security.dto.ISecurityDTO;
import com.northbrain.common.security.model.po.AccessControlPO;
import com.northbrain.common.security.model.po.PrivilegePO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：安全域接口的实现类
 * 用途：实现权限、访问控制、登录、注册等功能。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class SecurityDomain implements ISecurityDomain
{
    private static Logger logger = Logger.getLogger(SecurityDomain.class);

    private final PrivilegePOMapper privilegePOMapper;
    private final PrivilegeHisPOMapper privilegeHisPOMapper;
    private final AccessControlPOMapper accessControlPOMapper;
    private final AccessControlHisPOMapper accessControlHisPOMapper;
    private final ISecurityDTO securityDTO;

    @Autowired
    public SecurityDomain(PrivilegePOMapper privilegePOMapper, PrivilegeHisPOMapper privilegeHisPOMapper,
                          AccessControlPOMapper accessControlPOMapper, AccessControlHisPOMapper accessControlHisPOMapper,
                          ISecurityDTO securityDTO)
    {
        this.privilegePOMapper = privilegePOMapper;
        this.privilegeHisPOMapper = privilegeHisPOMapper;
        this.accessControlPOMapper = accessControlPOMapper;
        this.accessControlHisPOMapper = accessControlHisPOMapper;
        this.securityDTO = securityDTO;
    }

    /**
     * 方法：获取特定的权限
     * @param privilegeId 权限编号
     * @return 权限值对象
     * @throws Exception 异常
     */
    @Override
    public PrivilegeVO readPrivilege(Integer privilegeId) throws Exception
    {
        if(privilegeId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "privilegeId:" + privilegeId);
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(privilegePOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "privilegePOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(securityDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        PrivilegePO privilegePO = privilegePOMapper.selectByPrimaryKey(privilegeId);

        if(privilegePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "privilegePO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return securityDTO.convertToPrivilegeVO(privilegePO);
    }

    /**
     * 方法：获取特定的访问控制
     * @param roleId 角色编号
     * @return 访问控制值对象列表
     * @throws Exception 异常
     */
    @Override
    public List<AccessControlVO> readAccessControlsByRole(Integer roleId) throws Exception
    {
        if(roleId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "roleId:" + roleId);
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(accessControlPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "accessControlPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(securityDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        List<AccessControlPO> accessControlPOS = accessControlPOMapper.selectByRole(roleId);

        if(accessControlPOS == null || accessControlPOS.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "accessControlPOs");
            throw new CollectionEmptyException(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
        }

        List<AccessControlVO> accessControlVOS = new ArrayList<>();
        AccessControlVO accessControlVO;

        for(AccessControlPO accessControlPO: accessControlPOS)
        {
            accessControlVO = securityDTO.convertToAccessControlVO(accessControlPO);

            if(accessControlVO == null)
                continue;

            accessControlVOS.add(accessControlVO);
        }

        return accessControlVOS;
    }
}
