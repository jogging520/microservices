package com.northbrain.foundation.authentication.domain.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.exception.OperationRecordException;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.atom.OperationRecordVO;
import com.northbrain.base.common.model.vo.atom.PartyVO;
import com.northbrain.base.common.model.vo.atom.RegistryVO;
import com.northbrain.base.common.model.vo.orch.OrchRegistryVO;
import com.northbrain.base.common.util.JsonTransformationUtil;
import com.northbrain.foundation.authentication.dao.*;
import com.northbrain.foundation.authentication.domain.IAuthenticationDomain;
import com.northbrain.foundation.authentication.dto.IAuthenticationDTO;

/**
 * 类名：鉴权DOMAIN接口的实现类
 * 用途：实现鉴权相关业务逻辑
 * @author Jiakun
 * @version 1.0
 */
@Component
public class AuthenticationDomain implements IAuthenticationDomain
{
    private static Logger logger = Logger.getLogger(AuthenticationDomain.class);
    private final IOperationRecordDAO operationRecordDAO;
    private final ISequenceDAO sequenceDAO;
    private final IPartyDAO partyDAO;
    private final ISecurityDAO securityDAO;
    private final IStrategyDAO strategyDAO;
    private final IAuthenticationDTO authenticationDTO;

    @Autowired
    public AuthenticationDomain(IOperationRecordDAO operationRecordDAO, ISequenceDAO sequenceDAO, IPartyDAO partyDAO,
                                ISecurityDAO securityDAO, IStrategyDAO strategyDAO, IAuthenticationDTO authenticationDTO)
    {
        this.operationRecordDAO = operationRecordDAO;
        this.sequenceDAO = sequenceDAO;
        this.partyDAO = partyDAO;
        this.securityDAO = securityDAO;
        this.strategyDAO = strategyDAO;
        this.authenticationDTO = authenticationDTO;
    }

    /**
     * 方法：注册
     *
     * @param orchRegistryVO 编排层注册值对象
     * @return 是否注册成功
     */
    @Override
    public boolean createRegistry(OrchRegistryVO orchRegistryVO) throws Exception
    {
        if (orchRegistryVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "orchRegistryVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if (authenticationDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "authenticationDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        int rank = 0;

        //1、获取全局唯一的序列号，作为操作记录、事务的流水号
        int operationRecordId = readAtomOperationRecordId();

        if(operationRecordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "operationRecordId:" + String.valueOf(operationRecordId));
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        //2、新生成一条操作记录，并作为分布式事务（最终一致性）的开始。状态为初始状态。
        OperationRecordVO operationRecordVO = createOperationRecord(operationRecordId, BaseType.OPERATETYPE.CREATE,
                Constants.BUSINESS_COMMON_OPERATOR_CODE, BaseType.DOMAIN.FOUNDATION, Constants.BUSINESS_FOUNDATION_AUTHENTICATION_ORCH_MICROSERVICE,
                BaseType.STATUS.INITIAL, "TEST");

        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        logger.info(operationRecordVO);

        //3、调用参与者原子服务检查是否已经存在相应的记录
        rank++;
        OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO = createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.READ, BaseType.DOMAIN.PARTY,
                Constants.BUSINESS_PARTY_BASIC_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        List<PartyVO> partyVOS = readAtomRolesByName(orchRegistryVO.getName());

        if (partyVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "partyVOS");
            operationRecordDetailVO.setStatus(BaseType.STATUS.FAILURE.ordinal());
            logger.info(operationRecordVO);
            createAtomOperationRecord(operationRecordVO);

            return false;
        }

        operationRecordDetailVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
        operationRecordDetailVO.setFinishTime(new Date());
        operationRecordVO.addOperationRecordDetail(operationRecordDetailVO);

        logger.info(operationRecordVO);

        //如果已经存在，那么直接返回。
        if(partyVOS.size() > 0)
        {
            operationRecordVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
            operationRecordVO.setFinishTime(new Date());
            logger.info(operationRecordVO);
            createAtomOperationRecord(operationRecordVO);

            return false;
        }

        //4.获取全局唯一的序列号，作为注册编号
        rank++;
        operationRecordDetailVO = createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.READ, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_COMMON_SEQUENCE_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        int registryId = readAtomRegistryId();

        if(registryId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "registryId:" + String.valueOf(registryId));
            operationRecordDetailVO.setStatus(BaseType.STATUS.FAILURE.ordinal());
            logger.info(operationRecordVO);
            createAtomOperationRecord(operationRecordVO);

            return false;
        }

        operationRecordDetailVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
        operationRecordDetailVO.setFinishTime(new Date());
        operationRecordVO.addOperationRecordDetail(operationRecordDetailVO);

        logger.info(operationRecordVO);

        //5.获取全局唯一的序列号，作为参与者编号
        rank++;
        operationRecordDetailVO = createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.READ, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_COMMON_SEQUENCE_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        int partyId = readAtomPartyId();

        if(partyId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "partyId:" + String.valueOf(partyId));
            operationRecordDetailVO.setStatus(BaseType.STATUS.FAILURE.ordinal());
            logger.info(operationRecordVO);
            createAtomOperationRecord(operationRecordVO);

            return false;
        }

        operationRecordDetailVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
        operationRecordDetailVO.setFinishTime(new Date());
        operationRecordVO.addOperationRecordDetail(operationRecordDetailVO);

        logger.info(operationRecordVO);

        //6.调用安全原子微服务进行注册
        rank++;
        operationRecordDetailVO = createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.CREATE, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_COMMON_SECURITY_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        RegistryVO registryVO = authenticationDTO.ConvertOrchRegistryVOToRegistryVO(orchRegistryVO, operationRecordId, registryId, partyId);

        if(registryVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "registryVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(!createAtomRegistry(registryVO))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_REGISTRY_EXCEPTION);
            operationRecordDetailVO.setStatus(BaseType.STATUS.FAILURE.ordinal());
            logger.info(operationRecordVO);
            createAtomOperationRecord(operationRecordVO);

            return false;
        }

        operationRecordDetailVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
        operationRecordDetailVO.setFinishTime(new Date());
        operationRecordVO.addOperationRecordDetail(operationRecordDetailVO);

        logger.info(operationRecordVO);

        //7.调用参与者基础原子微服务进行参与者登记
        rank++;
        operationRecordDetailVO = createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.CREATE, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_PARTY_BASIC_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        PartyVO partyVO = authenticationDTO.ConvertOrchRegistryVOToPartyVO(orchRegistryVO, operationRecordId, registryId, partyId);

        if(partyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "partyVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(!createAtomParty(partyVO))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_SECURITY_REGISTRY_EXCEPTION);
            operationRecordDetailVO.setStatus(BaseType.STATUS.FAILURE.ordinal());
            logger.info(operationRecordVO);
            createAtomOperationRecord(operationRecordVO);

            return false;
        }

        operationRecordDetailVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
        operationRecordDetailVO.setFinishTime(new Date());
        operationRecordVO.addOperationRecordDetail(operationRecordDetailVO);

        logger.info(operationRecordVO);


        //8.调用参与者相应原子微服务进行参与者细节登记

        //9.记录日志，并返回。
        operationRecordVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
        operationRecordVO.setFinishTime(new Date());
        logger.info(operationRecordVO);
        createAtomOperationRecord(operationRecordVO);

        return true;
    }

    /**
     * 方法：通过微服务读取全局操作记录序列号
     * @return 全局唯一操作记录序列号
     * @throws Exception 异常
     */
    private int readAtomOperationRecordId() throws Exception
    {
        if (sequenceDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "sequenceDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        Integer nextGlobalValue = JsonTransformationUtil.transformJSONStringIntoInteger(sequenceDAO.readAtomOperationRecordId());

        logger.info(Hints.HINT_BUSINESS_COMMON_SEQUENCE_NEXT_VALUE + String.valueOf(nextGlobalValue));

        return nextGlobalValue;
    }

    /**
     * 方法：通过微服务读取全局注册序列号
     * @return 全局唯一注册序列号
     * @throws Exception 异常
     */
    private int readAtomRegistryId() throws Exception
    {
        if (sequenceDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "sequenceDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        Integer nextGlobalValue = JsonTransformationUtil.transformJSONStringIntoInteger(sequenceDAO.readAtomRegistryId());

        logger.info(Hints.HINT_BUSINESS_COMMON_SEQUENCE_NEXT_VALUE + String.valueOf(nextGlobalValue));

        return nextGlobalValue;
    }

    /**
     * 方法：通过微服务读取全局参与者序列号
     * @return 全局唯一参与者序列号
     * @throws Exception 异常
     */
    private int readAtomPartyId() throws Exception
    {
        if (sequenceDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "sequenceDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        Integer nextGlobalValue = JsonTransformationUtil.transformJSONStringIntoInteger(sequenceDAO.readAtomPartyId());

        logger.info(Hints.HINT_BUSINESS_COMMON_SEQUENCE_NEXT_VALUE + String.valueOf(nextGlobalValue));

        return nextGlobalValue;
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
    private OperationRecordVO createOperationRecord(int operationRecordId, BaseType.OPERATETYPE operationType, int operatorId,
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
     * 方法：通过微服务新增一条操作记录，将操作记录持久化
     * @param operationRecordVO 操作记录对象
     * @throws Exception 异常
     */
    private void createAtomOperationRecord(OperationRecordVO operationRecordVO) throws Exception
    {
        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (operationRecordDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        boolean isCreated = JsonTransformationUtil.transformJSONStringIntoBoolean(operationRecordDAO.createAtomOperationRecord(operationRecordVO));

        if(!isCreated)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OPERATION_RECORD);
            throw new OperationRecordException(Errors.ERROR_BUSINESS_COMMON_OPERATION_RECORD_EXCEPTION);
        }
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
    private OperationRecordVO.OperationRecordDetailVO createOperationRecordDetail(OperationRecordVO operationRecordVO, int rank, BaseType.OPERATETYPE operationType,
                                                                                  BaseType.DOMAIN domain, String serviceName, BaseType.STATUS status) throws Exception
    {
        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
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

        if (operationRecordDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
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
     * 方法：通过参与者原子服务按名称读取注册列表
     * TODO 后期要改造成可以按照alias、email等多种方式校验
     * @param name 名称
     * @return 参与者列表
     * @throws Exception 异常
     */
    private List<PartyVO> readAtomRolesByName(String name) throws Exception
    {
        if (partyDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        JSONArray atomPartyVOS = JsonTransformationUtil.transformJSONStringIntoVOArray(this.partyDAO.readAtomRolesByName(name));

        if (atomPartyVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "atomPartyVOS");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        List<PartyVO> partyVOS = new ArrayList<>();

        for (Object atomPartyVOObject: atomPartyVOS)
        {
            PartyVO partyVO = (PartyVO) JsonTransformationUtil.transformPlainObjectIntoValueObject(atomPartyVOObject, PartyVO.class);

            if(partyVO == null)
                continue;

            partyVOS.add(partyVO);
        }

        return partyVOS;
    }

    /**
     * 方法：调用创建注册信息的原子服务进行注册
     * @param registryVO 注册信息值对象
     * @return 是否注册成功
     * @throws Exception 异常
     */
    private boolean createAtomRegistry(RegistryVO registryVO) throws Exception
    {
        if (registryVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "registryVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (securityDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JsonTransformationUtil.transformJSONStringIntoBoolean(this.securityDAO.createAtomRegistry(registryVO));
    }

    /**
     * 方法：调用创建参与者的原子服务进行注册
     * @param partyVO 参与者值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    private boolean createAtomParty(PartyVO partyVO) throws Exception
    {
        if (partyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "registryVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (partyDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JsonTransformationUtil.transformJSONStringIntoBoolean(this.partyDAO.createAtomParty(partyVO));
    }
}
