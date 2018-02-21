package com.northbrain.base.common.model.vo.atom;

import com.northbrain.base.common.model.bo.BaseType;

/**
 * 类名：流控控制值对象类
 * 用途：用于流量管控的增删改查
 * @author Jiakun
 * @version 1.0
 */
public class FlowControlVO
{
    //参与者编码
    private int partyId;

    //角色编码
    private int roleId;

    //组织机构编码
    private int organizationId;

    // TODO 流控类型（按固定值、按比例、按时间段），后续优化。
    private BaseType.FLOWCONTROLTYPE flowControlType;

    //流控值
    private float flow;

    public int getPartyId() {
        return partyId;
    }

    public void setPartyId(int partyId) {
        this.partyId = partyId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public BaseType.FLOWCONTROLTYPE getFlowControlType() {
        return flowControlType;
    }

    public void setFlowControlType(BaseType.FLOWCONTROLTYPE flowControlType) {
        this.flowControlType = flowControlType;
    }

    public float getFlow() {
        return flow;
    }

    public void setFlow(float flow) {
        this.flow = flow;
    }
}
