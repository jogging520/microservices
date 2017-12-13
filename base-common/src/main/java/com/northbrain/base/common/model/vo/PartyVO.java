package com.northbrain.base.common.model.vo;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.northbrain.base.common.model.bo.Constants;

public class PartyVO extends BasicVO
{
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

    private List<PartyDetailVO> partyDetailVOS;

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

    public List<PartyDetailVO> getPartyDetailVOS() {
        return partyDetailVOS;
    }

    public void setPartyDetailVOS(List<PartyDetailVO> partyDetailVOS) {
        this.partyDetailVOS = partyDetailVOS;
    }

    public static class PartyDetailVO extends BasicDetailVO
    {
        //参与者明细编号
        private Integer partyDetailId;

        public Integer getPartyDetailId() {
            return partyDetailId;
        }

        public void setPartyDetailId(Integer partyDetailId) {
            this.partyDetailId = partyDetailId;
        }
    }
}
