package com.northbrain.common.security.service;

import com.northbrain.base.common.model.vo.ServiceVO;

/**
 * 类名：安全服务接口
 * 用途：封装登录、注册、权限、访问控制等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
public interface ISecurityService
{
    /**
     * 方法：获取特定的权限
     * @param privilegeId 权限编号
     * @return ServiceVO封装类
     */
    ServiceVO readPrivilege(Integer privilegeId);

    /**
     * 方法：获取特定的访问控制列表
     * @param roleId 角色编号
     * @return ServiceVO封装类
     */
    ServiceVO readAccessControlsByRole(Integer roleId);
}
