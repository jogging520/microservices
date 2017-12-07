package com.northbrain.common.security.model.po;

import java.util.Date;

public class AccessControlPO {
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