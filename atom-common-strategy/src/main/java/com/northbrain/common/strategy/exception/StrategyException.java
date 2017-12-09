package com.northbrain.common.strategy.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 策略插入或更新Exception
 * @author Jiakun
 * @version 1.0
 */
public class StrategyException extends Exception
{
    private static final long serialVersionUID = 0L;

    public StrategyException(Errors exception)
    {
        super(exception + "");
    }
}
