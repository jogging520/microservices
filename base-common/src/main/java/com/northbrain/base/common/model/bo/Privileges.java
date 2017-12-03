package com.northbrain.base.common.model.bo;

/**
 * 类名：权限枚举类
 * 用途：定义各类权限
 */
public enum Privileges
{
    PRIVILEGE_SYSTEM_                                               ("10000001", ""),


    PRIVILEGE_BUSINESS_COMMON_                                      ("20000001", ""),


    private String code;
    private String desc;

    Privileges(String code, String desc)
    {
        this.setCode(code);
        this.setDesc(desc);
    }

    public String getCode()
    {
        return this.code;
    }

    private void setCode(String code)
    {
        this.code = code;
    }

    public String getDesc()
    {
        return this.desc;
    }

    private void setDesc(String desc)
    {
        this.desc = desc;
    }

    @Override
    public String toString()
    {
        return "[" + this.getCode() + ":" + this.getDesc() + "]";
    }
}
