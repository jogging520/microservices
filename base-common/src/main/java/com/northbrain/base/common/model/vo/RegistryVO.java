package com.northbrain.base.common.model.vo;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.northbrain.base.common.model.bo.Constants;

public class RegistryVO
{
    //操作编号
    private Integer recordId;

    private Integer registryId;

    private Integer partyId;

    private String domain;

    private String category;

    private String type;

    private Integer status;

    @JSONField(format= Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date createTime;

    @JSONField(format=Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date statusTime;

    private String description;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getRegistryId() {
        return registryId;
    }

    public void setRegistryId(Integer registryId) {
        this.registryId = registryId;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
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

    public String toString()
    {
        return JSON.toJSONString(this);
    }
}
