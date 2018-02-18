package com.northbrain.base.common.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 访问控制Exception
 * @author Jiakun
 * @version 1.0
 */
public class AccessControlException extends Exception
{
    private static final long serialVersionUID = 0L;

    public AccessControlException(Errors exception)
    {
        super(exception + "");
    }
}
