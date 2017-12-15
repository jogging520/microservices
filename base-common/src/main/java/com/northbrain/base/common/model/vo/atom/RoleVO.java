package com.northbrain.base.common.model.vo.atom;

import com.northbrain.base.common.model.vo.basic.BasicVO;

/**
 * 类名：角色值对象类
 * 用途：用于持久层以上的角色对象传递
 * @author Jiakun
 * @version 1.0
 */
public class RoleVO extends BasicVO
{
    //角色编号
    private Integer roleId;

    //角色名称
    private String name;

    //别名
    private String alias;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }
}
