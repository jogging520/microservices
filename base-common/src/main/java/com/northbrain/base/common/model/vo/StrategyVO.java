package com.northbrain.base.common.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.northbrain.base.common.model.bo.Constants;

/**
 * 类名：策略值对象类
 * 用途：实现domain层以上策略对象的传递
 * @author Jiakun
 * @version 1.0
 */
public class StrategyVO
{
    //操作编号
    private Integer recordId;

    //策略编号
    private Integer strategyId;

    //策略名称
    private String name;

    //策略归属域
    private String domain;

    //策略类别
    private String category;

    //策略类型
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

    private List<StrategyDetailVO> strategyDetailVOS;

    public StrategyVO()
    {

    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public List<StrategyDetailVO> getStrategyDetailVOS() {
        return strategyDetailVOS;
    }

    public void setStrategyDetailVOS(List<StrategyDetailVO> strategyDetailVOS) {
        this.strategyDetailVOS = strategyDetailVOS;
    }

    public boolean addStrategyDetail(StrategyDetailVO newStrategyDetailVO)
    {
        if(newStrategyDetailVO == null)
            return false;

        if(this.strategyDetailVOS == null)
            this.strategyDetailVOS = new ArrayList<>();

        boolean isContain = false;

        for(StrategyDetailVO strategyDetailVO: this.strategyDetailVOS)
            if(Objects.equals(strategyDetailVO.getStrategyDetailId(), newStrategyDetailVO.getStrategyDetailId()))
                isContain = true;

        if(!isContain)
            this.strategyDetailVOS.add(newStrategyDetailVO);

        return true;
    }

    public String toString()
    {
        return JSON.toJSONString(this);
    }

    //策略明细值对象类
    public static class StrategyDetailVO
    {
        //策略明细编号
        private Integer strategyDetailId;

        //属性
        private String attribute;

        //取值
        private String value;

        //状态
        private Integer status;

        //创建时间
        @JSONField(format=Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
        private Date createTime;

        //状态时间
        @JSONField(format=Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
        private Date statusTime;

        //描述
        private String description;

        public Integer getStrategyDetailId() {
            return strategyDetailId;
        }

        public void setStrategyDetailId(Integer strategyDetailId) {
            this.strategyDetailId = strategyDetailId;
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
}
