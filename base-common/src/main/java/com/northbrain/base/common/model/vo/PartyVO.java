package com.northbrain.base.common.model.vo;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.northbrain.base.common.model.bo.Constants;

public class PartyVO
{
    //操作编号
    private Integer recordId;

    //参与者编号
    private Integer partyId;

    //别名
    private String alias;

    //密码
    private String password;

    //具体实体编码
    private Integer entityId;

    //角色编码
    private Integer roleId;

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
    @JSONField(format= Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date statusTime;

    //描述
    private String description;

    private List<PartyDetailVO> partyDetailVOS;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

    public List<PartyDetailVO> getPartyDetailVOS() {
        return partyDetailVOS;
    }

    public void setPartyDetailVOS(List<PartyDetailVO> partyDetailVOS) {
        this.partyDetailVOS = partyDetailVOS;
    }

    public String toString()
    {
        return JSON.toJSONString(this);
    }


    public static class PartyDetailVO
    {
        //参与者明细编号
        private Integer partyDetailId;

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

        public Integer getPartyDetailId() {
            return partyDetailId;
        }

        public void setPartyDetailId(Integer partyDetailId) {
            this.partyDetailId = partyDetailId;
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
