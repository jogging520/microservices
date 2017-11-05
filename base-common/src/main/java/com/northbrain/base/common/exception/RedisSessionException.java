package com.northbrain.base.common.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * Redis会话异常Exception
 * @author Jiakun
 * @version 1.0
 */
public class RedisSessionException extends Exception
{
	private static final long serialVersionUID = 0L;

    public RedisSessionException(Errors exception)
    {
        super(exception + "");
    }
}
