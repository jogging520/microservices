package com.northbrain.base.common.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 调用原子服务返回Exception
 * @author Jiakun
 * @version 1.0
 */
public class AtomicServiceResponseException extends Exception
{
    private static final long serialVersionUID = 0L;

    public AtomicServiceResponseException(Errors exception)
    {
        super(exception + "");
    }
}
