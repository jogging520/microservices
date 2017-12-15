package com.northbrain.common.security.domain.impl;

import com.northbrain.base.common.exception.CollectionEmptyException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.atom.AccessControlVO;
import com.northbrain.base.common.model.vo.atom.LoginVO;
import com.northbrain.base.common.model.vo.atom.PrivilegeVO;
import com.northbrain.base.common.model.vo.atom.RegistryVO;
import com.northbrain.common.security.dao.*;
import com.northbrain.common.security.domain.ISecurityDomain;
import com.northbrain.common.security.dto.ISecurityDTO;
import com.northbrain.common.security.exception.LoginException;
import com.northbrain.common.security.exception.RegistryException;
import com.northbrain.common.security.model.po.*;

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
    private final LoginPOMapper loginPOMapper;
    private final LoginHisPOMapper loginHisPOMapper;
    private final RegistryPOMapper registryPOMapper;
    private final RegistryHisPOMapper registryHisPOMapper;
    private final ISecurityDTO securityDTO;

    @Autowired
    public SecurityDomain(PrivilegePOMapper privilegePOMapper, PrivilegeHisPOMapper privilegeHisPOMapper,
                          AccessControlPOMapper accessControlPOMapper, AccessControlHisPOMapper accessControlHisPOMapper,
                          LoginPOMapper loginPOMapper, LoginHisPOMapper loginHisPOMapper,
                          RegistryPOMapper registryPOMapper, RegistryHisPOMapper registryHisPOMapper, ISecurityDTO securityDTO)
    {
        this.privilegePOMapper = privilegePOMapper;
        this.privilegeHisPOMapper = privilegeHisPOMapper;
        this.accessControlPOMapper = accessControlPOMapper;
        this.accessControlHisPOMapper = accessControlHisPOMapper;
        this.loginPOMapper = loginPOMapper;
        this.loginHisPOMapper = loginHisPOMapper;
        this.registryPOMapper = registryPOMapper;
        this.registryHisPOMapper = registryHisPOMapper;
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

    /**
     * 方法：获取登录信息
     *
     * @param partyId 参与者编号
     * @return 登录信息的值对象列表
     * @throws Exception 异常
     */
    @Override
    public List<LoginVO> readLoginsByParty(Integer partyId) throws Exception
    {
        if(partyId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "partyId:" + partyId);
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(loginPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "loginPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(securityDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        List<LoginPO> loginPOS = loginPOMapper.selectByParty(partyId);

        if(loginPOS == null || loginPOS.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "loginPOS");
            throw new CollectionEmptyException(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
        }

        List<LoginVO> loginVOS = new ArrayList<>();
        LoginVO loginVO;

        for(LoginPO loginPO: loginPOS)
        {
            loginVO = securityDTO.convertToLoginVO(loginPO);

            if(loginVO == null)
                continue;

            loginVOS.add(loginVO);
        }

        return loginVOS;
    }

    /**
     * 方法：新增一条注册信息（注册）
     *
     * @param registryVO 注册信息值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    @Override
    public boolean createRegistry(RegistryVO registryVO) throws Exception
    {
        if(registryVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "registryVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(registryPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "registryPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(registryHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "registryHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(securityDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(registryVO);

        RegistryPO registryPO = securityDTO.convertToRegistryPO(registryVO);

        if(registryPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "registryPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(registryPOMapper.selectByPrimaryKey(registryPO.getRegistryId()) == null)
        {
            if(registryPOMapper.insert(registryPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_REGISTRY_INSERT + String.valueOf(registryPO.getRegistryId()));
                throw new RegistryException(Errors.ERROR_BUSINESS_COMMON_SECURITY_REGISTRY_EXCEPTION);
            }

            RegistryHisPO registryHisPO = securityDTO.convertToRegistryHisPO(registryVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), registryPO);

            if(registryHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "registryHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(registryHisPOMapper.insert(registryHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_REGISTRY_INSERT + String.valueOf(registryHisPO.getRegistryId()));
                throw new RegistryException(Errors.ERROR_BUSINESS_COMMON_SECURITY_REGISTRY_EXCEPTION);
            }

            return true;
        }

        return false;
    }

    /**
     * 方法：创建一条登录信息（登录）
     *
     * @param loginVO 登录信息值对象
     * @return 是否创建成功
     * @throws Exception 异常
     */
    @Override
    public boolean createLogin(LoginVO loginVO) throws Exception
    {
        if(loginVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "loginVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(loginPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "loginPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(loginHisPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "loginHisPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(securityDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(loginVO);

        LoginPO loginPO = securityDTO.convertToLoginPO(loginVO);

        if(loginPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "loginPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(loginPOMapper.selectByPrimaryKey(loginPO.getLoginId()) == null)
        {
            if(loginPOMapper.insert(loginPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_LOGIN_INSERT + String.valueOf(loginPO.getLoginId()));
                throw new LoginException(Errors.ERROR_BUSINESS_COMMON_SECURITY_LOGIN_EXCEPTION);
            }

            LoginHisPO loginHisPO = securityDTO.convertToLoginHisPO(loginVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), loginPO);

            if(loginHisPO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "loginHisPO");
                throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            }

            if(loginHisPOMapper.insert(loginHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_LOGIN_INSERT + String.valueOf(loginHisPO.getRegistryId()));
                throw new LoginException(Errors.ERROR_BUSINESS_COMMON_SECURITY_LOGIN_EXCEPTION);
            }

            return true;
        }

        return false;
    }

    /**
     * 方法：更新一条登录信息（登出）
     *
     * @param loginVO 登录信息值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public boolean updateLogin(LoginVO loginVO) throws Exception
    {
        if(loginVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "loginVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(loginPOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "loginPOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(securityDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.debug(loginVO);

        LoginPO loginPO = securityDTO.convertToLoginPO(loginVO);

        if(loginPO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "loginPO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(loginPOMapper.selectByPrimaryKey(loginPO.getLoginId()) != null)
        {
            if(loginPOMapper.updateByPrimaryKey(loginPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_LOGIN_UPDATE + String.valueOf(loginPO.getLoginId()));
                throw new LoginException(Errors.ERROR_BUSINESS_COMMON_SECURITY_LOGIN_EXCEPTION);
            }

            LoginHisPO loginHisPO = securityDTO.convertToLoginHisPO(loginVO.getRecordId(), BaseType.OPERATETYPE.CREATE.name(), loginPO);

            if(loginHisPOMapper.insert(loginHisPO) == 0)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_LOGIN_INSERT + String.valueOf(loginHisPO.getRegistryId()));
                throw new LoginException(Errors.ERROR_BUSINESS_COMMON_SECURITY_LOGIN_EXCEPTION);
            }

            return true;
        }

        return false;
    }
}
