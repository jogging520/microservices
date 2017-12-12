package com.northbrain.party.organization.exception;

import com.northbrain.base.common.model.bo.Errors;

/**
 * 组织机构插入或更新Exception
 * @author Jiakun
 * @version 1.0
 */
public class OrganizationException extends Exception
{
    private static final long serialVersionUID = 0L;

    public OrganizationException(Errors exception)
    {
        super(exception + "");
    }
}

