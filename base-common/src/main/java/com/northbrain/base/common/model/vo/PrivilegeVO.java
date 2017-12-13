package com.northbrain.base.common.model.vo;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.northbrain.base.common.model.bo.Constants;

/**
 * 类名：权限值对象类
 * 用途：用于持久层以上的权限对象传递
 * @author Jiakun
 * @version 1.0
 */
public class PrivilegeVO extends BasicVO
{
    private Integer privilegeId;

    private String name;

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
