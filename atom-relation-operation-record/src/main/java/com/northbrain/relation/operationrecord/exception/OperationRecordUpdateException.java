package com.northbrain.relation.operationrecord.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 操作记录更新Exception
 * @author Jiakun
 * @version 1.0
 */
public class OperationRecordUpdateException extends Exception
{
    private static final long serialVersionUID = 0L;

    public OperationRecordUpdateException(Errors exception)
    {
        super(exception + "");
    }
}
