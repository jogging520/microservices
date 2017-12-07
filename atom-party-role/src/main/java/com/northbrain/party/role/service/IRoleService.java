package com.northbrain.party.role.service;

import com.northbrain.base.common.model.vo.ServiceVO;

/**
 * 类名：角色服务接口
 * 用途：封装角色增删改查等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
public interface IRoleService
{
    /**
     * 方法：根据名称获取权限清单
     * @param name 角色名称
     * @return ServiceVO封装类
     */
    ServiceVO readRolesByName(String name);
}
