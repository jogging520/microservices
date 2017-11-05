package com.northbrain.base.common.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 集合为空Exception
 * @author Jiakun
 * @version 1.0
 */
public class CollectionEmptyException extends Exception
{
	private static final long serialVersionUID = 0L;
	
	public CollectionEmptyException(Errors exception)
    {
        super(exception + ", ");
    }
}
