package com.northbrain.base.common.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 入参Exception
 * @author Jiakun
 * @version 1.0
 */
public class ArgumentInputException extends Exception
{
	private static final long serialVersionUID = 0L;

    public ArgumentInputException(Errors exception)
    {
        super(exception + "");
    }
}
