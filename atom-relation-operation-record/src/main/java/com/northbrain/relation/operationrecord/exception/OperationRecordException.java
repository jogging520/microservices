package com.northbrain.relation.operationrecord.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 操作记录插入或更新Exception
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
