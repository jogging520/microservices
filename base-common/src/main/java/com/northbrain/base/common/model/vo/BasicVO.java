package com.northbrain.base.common.model.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.northbrain.base.common.model.bo.Constants;

import java.util.Date;

/**
 * 类名：基础值对象类
 * 用途：实现通用熟悉，其他值对象类继承。
 * @author Jiakun
 * @version 1.0
 */
public class BasicVO
{
    //操作编号
    private Integer recordId;

    //归属域
    private String domain;

    //类别
    private String category;

    //类型
    private String type;

    //状态
    private Integer status;

    //创建时间
    @JSONField(format= Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date createTime;

    //状态时间
    @JSONField(format=Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date statusTime;

    //描述
    private String description;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
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
