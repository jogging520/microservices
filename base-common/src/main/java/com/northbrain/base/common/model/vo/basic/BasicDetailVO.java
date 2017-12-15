package com.northbrain.base.common.model.vo.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.northbrain.base.common.model.bo.Constants;

import java.util.Date;

/**
 * 类名：基础明细值对象类
 * 用途：实现通用熟悉，其他值对象类继承。
 * @author Jiakun
 * @version 1.0
 */
public class BasicDetailVO
{
    //属性
    private String attribute;

    //取值
    private String value;

    //状态
    private Integer status;

    //创建时间
    @JSONField(format= Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date createTime;

    //状态时间
    @JSONField(format= Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date statusTime;

    //描述
    private String description;

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute == null ? null : attribute.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
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
