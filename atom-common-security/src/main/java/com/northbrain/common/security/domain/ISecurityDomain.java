package com.northbrain.common.security.domain;

import com.northbrain.base.common.model.vo.AccessControlVO;
import com.northbrain.base.common.model.vo.PrivilegeVO;

import java.util.List;

/**
 * 类名：安全域接口
 * 用途：实现权限、访问控制、登录、注册等功能。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface ISecurityDomain
{
    /**
     * 方法：获取特定的权限
     * @param privilegeId 权限编号
     * @return 权限值对象
     * @throws Exception 异常
     */
    PrivilegeVO readPrivilege(Integer privilegeId) throws Exception;

    /**
     * 方法：获取特定的访问控制
     * @param roleId 角色编号
     * @return 访问控制值对象列表
     * @throws Exception 异常
     */
    List<AccessControlVO> readAccessControlsByRole(Integer roleId) throws Exception;
}
