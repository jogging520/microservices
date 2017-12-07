package com.northbrain.common.security.dto;

import com.northbrain.base.common.model.vo.AccessControlVO;
import com.northbrain.base.common.model.vo.PrivilegeVO;
import com.northbrain.common.security.model.po.AccessControlPO;
import com.northbrain.common.security.model.po.PrivilegePO;

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
     * 方法：将权限控制VO转换成PO
     * @param accessControlVO 权限控制值对象
     * @return 权限控制持久化对象
     */
    AccessControlPO convertToAccessControlPO(AccessControlVO accessControlVO) throws Exception;
}

