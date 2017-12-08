package com.northbrain.common.security.dto;

import com.northbrain.base.common.model.vo.AccessControlVO;
import com.northbrain.base.common.model.vo.LoginVO;
import com.northbrain.base.common.model.vo.PrivilegeVO;
import com.northbrain.base.common.model.vo.RegistryVO;
import com.northbrain.common.security.model.po.AccessControlPO;
import com.northbrain.common.security.model.po.LoginPO;
import com.northbrain.common.security.model.po.PrivilegePO;
import com.northbrain.common.security.model.po.RegistryPO;

/**
 * 类名：安全传输对象接口
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
public interface ISecurityDTO
{
    /**
     * 方法：将权限PO转换成VO
     * @param privilegePO 权限持久化对象
     * @return 权限值对象
     */
    PrivilegeVO convertToPrivilegeVO(PrivilegePO privilegePO) throws Exception;

    /**
     * 方法：将权限VO转换成PO
     * @param privilegeVO 权限值对象
     * @return 权限持久化对象
     */
    PrivilegePO convertToPrivilegePO(PrivilegeVO privilegeVO) throws Exception;

    /**
     * 方法：将访问控制PO转换成VO
     * @param accessControlPO 访问控制持久化对象
     * @return 访问控制值对象
     */
    AccessControlVO convertToAccessControlVO(AccessControlPO accessControlPO) throws Exception;

    /**
     * 方法：将访问控制VO转换成PO
     * @param accessControlVO 访问控制值对象
     * @return 访问控制持久化对象
     */
    AccessControlPO convertToAccessControlPO(AccessControlVO accessControlVO) throws Exception;

    /**
     * 方法：将登录信息PO转换成VO
     * @param loginPO 登录信息持久化对象
     * @return 登录信息值对象
     */
    LoginVO convertToLoginVO(LoginPO loginPO) throws Exception;

    /**
     * 方法：将登录信息VO转换成PO
     * @param loginVO 登录信息值对象
     * @return 登录信息持久化对象
     */
    LoginPO convertToLoginPO(LoginVO loginVO) throws Exception;

    /**
     * 方法：将注册信息PO转换成VO
     * @param registryPO 注册信息持久化对象
     * @return 注册信息值对象
     */
    RegistryVO convertToRegistryVO(RegistryPO registryPO) throws Exception;

    /**
     * 方法：将注册信息VO转换成PO
     * @param registryVO 注册信息值对象
     * @return 注册信息持久化对象
     */
    RegistryPO convertToRegistryPO(RegistryVO registryVO) throws Exception;
}

