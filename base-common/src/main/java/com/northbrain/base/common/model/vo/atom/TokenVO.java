package com.northbrain.base.common.model.vo.atom;

/**
 * 类名：令牌值对象类
 * 用途：用于传输令牌信息
 * @author Jiakun
 * @version 1.0
 */
public class TokenVO
{
    //参与者编码
    private int partyId;

    //角色编码
    private int roleId;

    //组织机构编码
    private int organizationId;

    public int getPartyId()
    {
        return partyId;
    }

    public void setPartyId(int partyId)
    {
        this.partyId = partyId;
    }

    public int getRoleId()
    {
        return roleId;
    }

    public void setRoleId(int roleId)
    {
        this.roleId = roleId;
    }
    public int getOrganizationId()
    {
        return organizationId;
    }

    public void setOrganizationId(int organizationId)
    {
        this.organizationId = organizationId;
    }

}
