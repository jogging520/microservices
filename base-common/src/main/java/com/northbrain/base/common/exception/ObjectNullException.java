package com.northbrain.base.common.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 空对象Exception
 * @author Jiakun
 * @version 1.0
 */
public class ObjectNullException extends Exception
{
	private static final long serialVersionUID = 0L;
	
	public ObjectNullException(Errors exception)
    {
        super(exception + "");
    }
}
