package com.northbrain.foundation.authentication.dto.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.*;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.atom.*;
import com.northbrain.base.common.model.vo.orch.OrchLoginVO;
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
    public RegistryVO convertOrchRegistryVOToRegistryVO(OrchRegistryVO orchRegistryVO, int recordId, int registryId, int partyId)
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
     * @return 微服务层注册值对象
     * @throws Exception 异常
     */
    @Override
    public PartyVO convertOrchRegistryVOToPartyVO(OrchRegistryVO orchRegistryVO, int recordId, int registryId, int partyId)
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

    /**
     * 方法：将编排层OrchLoginVO对象转换成原子服务的LoginVO值对象
     *
     * @param orchLoginVO 编排层登录值对象
     * @param recordId 操作流水记录
     * @param loginId 登录编号
     * @param registryId 注册编码
     * @param partyId 参与者编码
     * @param roleId 角色编码
     * @return 微服务层登录值对象
     * @throws Exception 异常
     */
    @Override
    public LoginVO convertOrchLoginVOToLoginVO(OrchLoginVO orchLoginVO, int recordId, int loginId, int registryId,
                                               int partyId, int roleId) throws Exception
    {
        if(orchLoginVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "orchLoginVO");
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

        if(roleId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "roleId");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(loginId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "loginId");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_ORCH_VO_ATOM_VO_CONVERTION);

        LoginVO loginVO = new LoginVO();
        loginVO.setRecordId(recordId);
        loginVO.setLoginId(loginId);
        loginVO.setRegistryId(registryId);
        loginVO.setPartyId(partyId);
        loginVO.setRoleId(roleId);
        loginVO.setOrganizationId(orchLoginVO.getOrganizationId());
        loginVO.setStatus(BaseType.STATUS.INUSED.ordinal());

        // TODO 设置type、category和domain

        Date now = new Date();
        loginVO.setLoginTime(now);
        loginVO.setLogoutTime(now);
        loginVO.setDescription(orchLoginVO.getDescription());

        return loginVO;
    }

    /**
     * 方法：创建一条操作记录
     * @param operationRecordId 操作记录流水号
     * @param operationType 操作类型
     * @param operatorId 操作工号
     * @param domain 服务归属域
     * @param serviceName 编排服务名称
     * @param status 状态
     * @param description 描述（如A/B环境等）
     * @return 操作记录对象
     * @throws Exception 异常
     */
    @Override
    public OperationRecordVO createOperationRecord(int operationRecordId, BaseType.OPERATETYPE operationType, int operatorId,
                                                    BaseType.DOMAIN domain, String serviceName, BaseType.STATUS status,
                                                    String description) throws Exception
    {
        if(operationRecordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "operationRecordId:" + String.valueOf(operationRecordId));
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(operatorId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "operatorId:" + String.valueOf(operatorId));
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(serviceName == null || serviceName.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceName");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        OperationRecordVO operationRecordVO = new OperationRecordVO();
        operationRecordVO.setRecordId(operationRecordId);
        operationRecordVO.setOperateType(operationType.name());
        operationRecordVO.setOperatorId(operatorId);
        operationRecordVO.setDomain(domain.name());
        operationRecordVO.setServiceName(serviceName);
        operationRecordVO.setStatus(status.ordinal());
        operationRecordVO.setStartTime(new Date());
        operationRecordVO.setFinishTime(new Date());
        operationRecordVO.setDescription(description);

        return operationRecordVO;
    }

    /**
     * 方法：新增一条操作明细记录
     * @param operationRecordVO 操作记录值对象
     * @param rank 序号
     * @param operationType 操作类型
     * @param domain 域
     * @param serviceName 服务名称
     * @param status 状态
     * @return 操作明细对象
     * @throws Exception 异常
     */
    @Override
    public OperationRecordVO.OperationRecordDetailVO createOperationRecordDetail(OperationRecordVO operationRecordVO, int rank, BaseType.OPERATETYPE operationType,
                                                                                  BaseType.DOMAIN domain, String serviceName, BaseType.STATUS status) throws Exception
    {
        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(rank <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "rank:" + String.valueOf(rank));
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if(serviceName == null || serviceName.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "serviceName");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO = new OperationRecordVO.OperationRecordDetailVO();
        operationRecordDetailVO.setRecordDetailId((long) (operationRecordVO.getRecordId() * Constants.BUSINESS_COMMON_OPERATION_RECORD_DETAIL_ID_MULTIPLE + rank));
        operationRecordDetailVO.setRank(rank);
        operationRecordDetailVO.setOperateType(operationType.name());
        operationRecordDetailVO.setDomain(domain.name());
        operationRecordDetailVO.setServiceName(serviceName);
        operationRecordDetailVO.setStatus(status.ordinal());
        operationRecordDetailVO.setStartTime(new Date());
        operationRecordDetailVO.setFinishTime(new Date());

        if(operationRecordVO.addOperationRecordDetail(operationRecordDetailVO))
        {
            return operationRecordDetailVO;
        }
        else
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OPERATION_RECORD);
            throw new OperationRecordException(Errors.ERROR_BUSINESS_COMMON_OPERATION_RECORD_EXCEPTION);
        }
    }

    /**
     * 将登录参与者VO转换成令牌VO
     *
     * @param orchLoginVO 登录信息VO
     * @param partyId 参与者编码
     * @return 令牌VO
     * @throws Exception 异常
     */
    @Override
    public TokenVO convertOrchLoginVOToTokenVO(OrchLoginVO orchLoginVO, int partyId) throws Exception
    {
        if (orchLoginVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "orchLoginVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(partyId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "partyId:" + String.valueOf(partyId));
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        TokenVO tokenVO = new TokenVO();
        tokenVO.setPartyId(partyId);
        tokenVO.setOrganizationId(orchLoginVO.getOrganizationId());

        return tokenVO;
    }

    /**
     * 方法：将token值对象转换成流控控制值对象
     *
     * @param tokenVO 令牌值对象
     * @param flowControlType 流控类型
     * @param flow 流控值
     * @return 流量控制值对象
     * @throws Exception 异常
     */
    @Override
    public FlowControlVO convertTokenVOToFlowControlVO(TokenVO tokenVO, BaseType.FLOWCONTROLTYPE flowControlType, float flow)
            throws Exception
    {
        if (tokenVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "tokenVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (flowControlType == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "flowControlType");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(flow <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "partyId:" + String.valueOf(flow));
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        FlowControlVO flowControlVO = new FlowControlVO();
        flowControlVO.setPartyId(tokenVO.getPartyId());
        flowControlVO.setRoleId(tokenVO.getRoleId());
        flowControlVO.setOrganizationId(tokenVO.getOrganizationId());
        flowControlVO.setFlowControlType(flowControlType);
        flowControlVO.setFlow(flow);

        return flowControlVO;
    }
}
