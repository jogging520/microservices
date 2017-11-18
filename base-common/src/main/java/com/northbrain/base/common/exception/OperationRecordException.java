package com.northbrain.base.common.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 操作记录Exception
 * @author Jiakun
 * @version 1.0
 */
public class OperationRecordException extends Exception
{
    private static final long serialVersionUID = 0L;

    public OperationRecordException(Errors exception)
    {
        super(exception + "");
    }
}
