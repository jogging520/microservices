package com.northbrain.base.common.exception;

import com.northbrain.base.common.model.bo.Parameters;
import com.northbrain.base.common.model.bo.Errors;

/**
 * Parameters基础配置器的Exception
 * @author Jiakun
 * @version 1.0
 */
public class ParametersStateException extends Exception
{
    private static final long serialVersionUID = 0L;

    public ParametersStateException(Errors exception)
    {
        super(exception + ", " + Parameters.getState());
    }
}
