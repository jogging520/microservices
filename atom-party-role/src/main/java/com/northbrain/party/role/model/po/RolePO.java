package com.northbrain.party.role.model.po;

import java.util.Date;

/**
 * 类名：角色持久化对象类。角色是对各类具象角色的抽象，引用方通过角色关联，而不是跟具体的某一类角色关联。
 * 引用方-->参与者--角色和具体角色实体
 * 用途：用于角色数据库持久化对象增删改查。
 * @author Jiakun
 * @version 1.0
 */
public class RolePO
{
    //角色编号
    private Integer roleId;

    //角色名称
    private String name;

    //别名
    private String alias;

    //归属域
    private String domain;

    //类别
    private String category;

    //类型
    private String type;

    //状态
    private Integer status;

    //创建时间
    private Date createTime;

    //状态时间
    private Date statusTime;

    //描述
    private String description;

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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}