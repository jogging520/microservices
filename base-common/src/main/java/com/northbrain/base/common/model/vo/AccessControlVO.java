package com.northbrain.base.common.model.vo;

import java.util.Date;

/**
 * 类名：访问控制值对象类
 * 用途：用于持久层以上的访问控制对象传递
 * @author Jiakun
 * @version 1.0
 */
public class AccessControlVO
{
    private Integer accessControlId;

    private Integer roleId;

    private Integer privilegeId;

    private Integer status;

    private Date createTime;

    private Date statusTime;

    public Integer getAccessControlId() {
        return accessControlId;
    }

    public void setAccessControlId(Integer accessControlId) {
        this.accessControlId = accessControlId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }
}
