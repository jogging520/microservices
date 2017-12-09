package com.northbrain.common.strategy.model.po;

import java.util.Date;

/**
 * 类名：策略明细持久化类
 * 用途：实现策略明细持久化对象增删改查的数据库同步
 * @author Jiakun
 * @version 1.0
 */
public class StrategyDetailPO
{
    //策略明细编号
    private Integer strategyDetailId;

    //策略编号
    private Integer strategyId;

    //属性
    private String attribute;

    //取值
    private String value;

    //状态
    private Integer status;

    //创建时间
    private Date createTime;

    //状态时间
    private Date statusTime;

    //描述
    private String description;

    public Integer getStrategyDetailId() {
        return strategyDetailId;
    }

    public void setStrategyDetailId(Integer strategyDetailId) {
        this.strategyDetailId = strategyDetailId;
    }

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

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
}