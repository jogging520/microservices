package com.northbrain.party.basic.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 参与者插入或更新Exception
 * @author Jiakun
 * @version 1.0
 */
public class PartyException extends Exception
{
    private static final long serialVersionUID = 0L;

    public PartyException(Errors exception)
    {
        super(exception + "");
    }
}