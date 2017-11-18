package com.northbrain.base.common.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 数值范围Exception
 * @author Jiakun
 * @version 1.0
 */
public class NumberScopeException extends Exception
{
	private static final long serialVersionUID = 0L;
	
	public NumberScopeException(Errors exception)
    {
        super(exception + "");
    }
}
