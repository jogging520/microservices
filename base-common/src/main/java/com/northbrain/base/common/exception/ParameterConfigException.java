package com.northbrain.base.common.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 基础参数配置Exception
 * @author Jiakun
 * @version 1.0
 */
public class ParameterConfigException extends Exception
{
	private static final long serialVersionUID = 0L;
	
	public ParameterConfigException(Errors exception)
    {
        super(exception + "");
    }
}
