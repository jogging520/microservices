package com.northbrain.base.common.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * Token校验Exception
 * @author Jiakun
 * @version 1.0
 */
public class TokenVerificationException extends Exception
{
    private static final long serialVersionUID = 0L;

    public TokenVerificationException(Errors exception)
    {
        super(exception + "");
    }
}
