package com.northbrain.common.security.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 注册信息插入或更新Exception
 * @author Jiakun
 * @version 1.0
 */
public class RegistryException extends Exception
{
    private static final long serialVersionUID = 0L;

    public RegistryException(Errors exception)
    {
        super(exception + "");
    }
}

