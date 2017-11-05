package com.northbrain.base.common.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 线程池执行器Exception
 * @author Jiakun
 * @version 1.0
 */
public class ThreadPoolExecutorException extends Exception
{
	private static final long serialVersionUID = 0L;
	
	public ThreadPoolExecutorException(Errors exception)
    {
        super(exception + "");
    }
}
