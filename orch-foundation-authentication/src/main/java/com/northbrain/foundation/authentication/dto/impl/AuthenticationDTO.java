package com.northbrain.foundation.authentication.dto.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.PropertyEnumerationException;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.atom.PartyVO;
import com.northbrain.base.common.model.vo.atom.RegistryVO;
import com.northbrain.base.common.model.vo.orch.OrchRegistryVO;
import com.northbrain.foundation.authentication.dto.IAuthenticationDTO;

/**
 * 类名：鉴权数据转换对象接口的实现类
 * 用途：转换鉴权相关的数据，包括注册、登录、权限、参数等
 * @author Jiakun
 * @version 1.0
 */
@Component
public class AuthenticationDTO implements IAuthenticationDTO
{
    private static Logger logger = Logger.getLogger(AuthenticationDTO.class);
    /**
     * 方法：将OrchRegistryVO值对象转换成RegistryVO值对象
     *
     * @param orchRegistryVO 编排层注册值对象
     * @param recordId 操作流水记录
     * @param registryId 注册编码
     * @param partyId 参与者编码
     * @return 微服务层注册值对象
     * @throws Exception 异常
     */
    @Override
    public RegistryVO ConvertOrchRegistryVOToRegistryVO(OrchRegistryVO orchRegistryVO, int recordId, int registryId, int partyId)
            throws Exception
    {
        if(orchRegistryVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "orchRegistryVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(recordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "recordId");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(registryId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "registryId");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(partyId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "partyId");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_ORCH_VO_ATOM_VO_CONVERTION);

        RegistryVO registryVO = new RegistryVO();
        registryVO.setRecordId(recordId);
        registryVO.setRegistryId(registryId);
        registryVO.setPartyId(partyId);

        if(BaseType.REGISTRYDOMAIN.valueOf(orchRegistryVO.getDomain()).ordinal() < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_VALUE_OBJECT_PROPERTY_DOMAIN_ENUM);
            throw new PropertyEnumerationException(Errors.ERROR_BUSINESS_COMMON_VALUE_OBJECT_PROPERTY_ENUM_EXCEPTION);
        }

        registryVO.setDomain(orchRegistryVO.getDomain());

        if(BaseType.REGISTRYCATEGORY.valueOf(orchRegistryVO.getCategory()).ordinal() < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_VALUE_OBJECT_PROPERTY_CATEGORY_ENUM);
            throw new PropertyEnumerationException(Errors.ERROR_BUSINESS_COMMON_VALUE_OBJECT_PROPERTY_ENUM_EXCEPTION);
        }

        registryVO.setCategory(orchRegistryVO.getCategory());

        if(BaseType.REGISTRYTYPE.valueOf(orchRegistryVO.getType()).ordinal() < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_VALUE_OBJECT_PROPERTY_TYPE_ENUM);
            throw new PropertyEnumerationException(Errors.ERROR_BUSINESS_COMMON_VALUE_OBJECT_PROPERTY_ENUM_EXCEPTION);
        }

        registryVO.setType(orchRegistryVO.getType());
        registryVO.setStatus(BaseType.STATUS.INUSED.ordinal());
        Date now = new Date();
        registryVO.setCreateTime(now);
        registryVO.setStatusTime(now);
        registryVO.setDescription(orchRegistryVO.getDescription());

        return registryVO;
    }

    /**
     * 方法：将OrchRegistryVO值对象转换成PartyVO值对象
     *
     * @param orchRegistryVO 编排层注册值对象
     * @param recordId       操作流水记录
     * @param registryId     注册编码
     * @param partyId        参与者编码
     * @return 微服务层参与者值对象
     * @throws Exception 异常
     */
    @Override
    public PartyVO ConvertOrchRegistryVOToPartyVO(OrchRegistryVO orchRegistryVO, int recordId, int registryId, int partyId)
            throws Exception
    {
        if(orchRegistryVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "orchRegistryVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(recordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "recordId");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(registryId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "registryId");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(partyId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "partyId");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_ORCH_VO_ATOM_VO_CONVERTION);

        PartyVO partyVO = new PartyVO();
        partyVO.setRecordId(recordId);
        partyVO.setPartyId(partyId);
        partyVO.setAlias(orchRegistryVO.getAlias());
        partyVO.setPassword(orchRegistryVO.getPassword());

        if(BaseType.REGISTRYDOMAIN.valueOf(orchRegistryVO.getDomain()).ordinal() < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_VALUE_OBJECT_PROPERTY_DOMAIN_ENUM);
            throw new PropertyEnumerationException(Errors.ERROR_BUSINESS_COMMON_VALUE_OBJECT_PROPERTY_ENUM_EXCEPTION);
        }

        partyVO.setDomain(orchRegistryVO.getDomain());

        if(BaseType.REGISTRYCATEGORY.valueOf(orchRegistryVO.getCategory()).ordinal() < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_VALUE_OBJECT_PROPERTY_CATEGORY_ENUM);
            throw new PropertyEnumerationException(Errors.ERROR_BUSINESS_COMMON_VALUE_OBJECT_PROPERTY_ENUM_EXCEPTION);
        }

        partyVO.setCategory(orchRegistryVO.getCategory());

        if(BaseType.REGISTRYTYPE.valueOf(orchRegistryVO.getType()).ordinal() < 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_VALUE_OBJECT_PROPERTY_TYPE_ENUM);
            throw new PropertyEnumerationException(Errors.ERROR_BUSINESS_COMMON_VALUE_OBJECT_PROPERTY_ENUM_EXCEPTION);
        }

        partyVO.setType(orchRegistryVO.getType());
        partyVO.setStatus(BaseType.STATUS.INUSED.ordinal());
        Date now = new Date();
        partyVO.setCreateTime(now);
        partyVO.setStatusTime(now);
        partyVO.setDescription(orchRegistryVO.getDescription());
        //TODO 设置各类明细
        //TODO 根据类型设置roleid（这个应该在外面一层）

        return partyVO;
    }
}
