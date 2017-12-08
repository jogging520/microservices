package com.northbrain.common.security.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 登录信息插入或更新Exception
 * @author Jiakun
 * @version 1.0
 */
public class LoginException extends Exception
{
    private static final long serialVersionUID = 0L;

    public LoginException(Errors exception)
    {
        super(exception + "");
    }
}

