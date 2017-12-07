package com.northbrain.party.role.domain;


import com.northbrain.base.common.model.vo.RoleVO;

import java.util.List;

/**
 * 类名：角色域接口
 * 用途：实现角色的增删改查等功能。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IRoleDomain
{
    /**
     * 方法：根据名称获取角色信息
     * @param name 名称
     * @return 角色值对象
     * @throws Exception 异常
     */
    List<RoleVO> readRolesByName(String name) throws Exception;
}
