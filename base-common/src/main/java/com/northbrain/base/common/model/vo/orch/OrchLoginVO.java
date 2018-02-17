package com.northbrain.base.common.model.vo.orch;

/**
 * 类名：编排层登录值对象
 * 通过不同的ID类型，适配各类的登录方式。
 * @author Jiakun
 * @version 1.0
 */
public class OrchLoginVO
{
    //ID类型
    private String idType;

    //ID取值
    private String idValue;

    //密码（加密密文）
    private String password;

    //组织机构编码
    private int organizationId;

    //描述
    private String description;

    public String getIdType()
    {
        return idType;
    }

    public void setIdType(String idType)
    {
        this.idType = idType;
    }

    public String getIdValue()
    {
        return idValue;
    }

    public void setIdValue(String idValue)
    {
        this.idValue = idValue;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
