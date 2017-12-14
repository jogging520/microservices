package com.northbrain.resource.basic.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 资源插入或更新Exception
 * @author Jiakun
 * @version 1.0
 */
public class ResourceException extends Exception
{
    private static final long serialVersionUID = 0L;

    public ResourceException(Errors exception)
    {
        super(exception + "");
    }
}
