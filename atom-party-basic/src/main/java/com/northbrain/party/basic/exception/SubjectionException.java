package com.northbrain.party.basic.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 隶属关系插入或更新Exception
 * @author Jiakun
 * @version 1.0
 */
public class SubjectionException extends Exception
{
    private static final long serialVersionUID = 0L;

    public SubjectionException(Errors exception)
    {
        super(exception + "");
    }
}

