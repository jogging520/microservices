package com.northbrain.party.basic.model.po;

import java.util.Date;

/**
 * 类名：组织机构历史数据持久化对象类，该类定义了组织机构的层级关系、名称、类型等。
 * 用途：用于组织机构历史数据数据库持久化对象增删改查。
 * @author Jiakun
 * @version 1.0
 */
public class OrganizationHisPO
{
    //操作流水号
    private Integer recordId;

    //操作类型
    private String operateType;

    //组织机构编号
    private Integer organizationId;

    //名称
    private String name;

    //组织机构的编码（按一定的规则）
    private String code;

    //归属域
    private String domain;

    //类别
    private String category;

    //类型
    private String type;

    //父组织机构的编号（用于递归）
    private Integer parentOrganizationId;

    //状态
    private Integer status;

    //创建时间
    private Date createTime;

    //状态时间
    private Date statusTime;

    //描述
    private String description;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public Integer getParentOrganizationId() {
        return parentOrganizationId;
    }

    public void setParentOrganizationId(Integer parentOrganizationId) {
        this.parentOrganizationId = parentOrganizationId;
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