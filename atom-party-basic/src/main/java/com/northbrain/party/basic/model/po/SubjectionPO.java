package com.northbrain.party.basic.model.po;

import java.util.Date;

/**
 * 类名：隶属关系持久化对象类，该类记录了参与者隶属于组织的关系记录。
 * 引用方-->隶属关系-->组织及具体的组织机构
 * 用途：用于隶属关系数据库持久化对象增删改查。
 * @author Jiakun
 * @version 1.0
 */
public class SubjectionPO
{
    //隶属编号
    private Integer subjectionId;

    //参与者编号
    private Integer partyId;

    //组织机构编号
    private Integer organizationId;

    //具体的组织机构编号
    private Integer entityId;

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

    public Integer getSubjectionId() {
        return subjectionId;
    }

    public void setSubjectionId(Integer subjectionId) {
        this.subjectionId = subjectionId;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
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