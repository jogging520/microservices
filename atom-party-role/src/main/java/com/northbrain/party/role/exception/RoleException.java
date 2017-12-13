package com.northbrain.party.role.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 角色插入或更新Exception
 * @author Jiakun
 * @version 1.0
 */
public class RoleException extends Exception
{
    private static final long serialVersionUID = 0L;

    public RoleException(Errors exception)
    {
        super(exception + "");
    }
}

