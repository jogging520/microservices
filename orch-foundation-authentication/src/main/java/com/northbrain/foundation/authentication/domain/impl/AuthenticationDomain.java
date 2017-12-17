package com.northbrain.foundation.authentication.domain.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.exception.OperationRecordException;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.atom.OperationRecordVO;
import com.northbrain.base.common.model.vo.atom.PartyVO;
import com.northbrain.base.common.model.vo.atom.RegistryVO;
import com.northbrain.base.common.model.vo.basic.ResponseVO;
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
     * TODO 代码有点长，后续要优化。
     * @param orchRegistryVO 编排层注册值对象
     * @return 是否注册成功
     */
    @Override
    public ResponseVO<Boolean> createRegistry(OrchRegistryVO orchRegistryVO) throws Exception
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
        ResponseVO<Boolean> responseVO = new ResponseVO<>();
        responseVO.setResponse(false);

        //1、获取全局唯一的序列号，作为操作记录、事务的流水号
        ResponseVO<Integer> operationRecordIdResponseVO = readAtomOperationRecordId();

        if(operationRecordIdResponseVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordIdResponseVO");
            responseVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            return responseVO;
        }

        if(!operationRecordIdResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE);
            responseVO.setResponseCodeAndDesc(operationRecordIdResponseVO.getResponseCode(), operationRecordIdResponseVO.getResponseDesc());
            return responseVO;
        }

        int operationRecordId = operationRecordIdResponseVO.getResponse();

        if(operationRecordId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "operationRecordId:" + String.valueOf(operationRecordId));
            responseVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE);
            return responseVO;
        }

        //2、新生成一条操作记录，并作为分布式事务（最终一致性）的开始。状态为初始状态。
        OperationRecordVO operationRecordVO = authenticationDTO.createOperationRecord(operationRecordId, BaseType.OPERATETYPE.CREATE,
                Constants.BUSINESS_COMMON_OPERATOR_CODE, BaseType.DOMAIN.FOUNDATION, Constants.BUSINESS_FOUNDATION_AUTHENTICATION_ORCH_MICROSERVICE,
                BaseType.STATUS.INITIAL, "TEST");

        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordVO");
            responseVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            return responseVO;
        }

        logger.info(operationRecordVO);

        //3、调用参与者原子服务检查是否已经存在相应的记录
        rank++;
        OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO =
                authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.READ, BaseType.DOMAIN.PARTY,
                Constants.BUSINESS_PARTY_BASIC_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordDetailVO");
            responseVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            return responseVO;
        }

        ResponseVO<List<PartyVO>> roleResponseVO = readAtomRolesByName(orchRegistryVO.getName());

        if (roleResponseVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "roleResponseVO");
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, responseVO);
        }

        if(!roleResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE);
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    roleResponseVO.getResponseCode(), roleResponseVO.getResponseDesc(), responseVO);
        }

        List<PartyVO> partyVOS = roleResponseVO.getResponse();

        if (partyVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyVOS");
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, responseVO);
        }

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);
        logger.info(operationRecordVO);

        //如果已经存在，那么直接返回。
        if(partyVOS.size() > 0)
        {
            logger.error(Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION);
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.SUCCESS,
                    Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION, responseVO);
        }

        //4.获取全局唯一的序列号，作为注册编号
        rank++;
        operationRecordDetailVO = authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.READ, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_COMMON_SEQUENCE_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordDetailVO");
            responseVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            return responseVO;
        }

        ResponseVO<Integer> registryIdResponseVO = readAtomRegistryId();

        if (registryIdResponseVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "registryIdResponseVO");
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, responseVO);
        }

        if(!registryIdResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE);
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    registryIdResponseVO.getResponseCode(), registryIdResponseVO.getResponseDesc(), responseVO);
        }

        int registryId = registryIdResponseVO.getResponse();

        if(registryId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "registryId" + String.valueOf(registryId));
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.SUCCESS,
                    Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION, responseVO);
        }

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);
        logger.info(operationRecordVO);

        //5.获取全局唯一的序列号，作为参与者编号
        rank++;
        operationRecordDetailVO = authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.READ, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_COMMON_SEQUENCE_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordDetailVO");
            responseVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            return responseVO;
        }

        ResponseVO<Integer> partyIdResponseVO = readAtomPartyId();

        if (partyIdResponseVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyIdResponseVO");
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, responseVO);
        }

        if(!partyIdResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE);
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    partyIdResponseVO.getResponseCode(), partyIdResponseVO.getResponseDesc(), responseVO);
        }

        int partyId = partyIdResponseVO.getResponse();

        if(partyId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "partyId" + String.valueOf(partyId));
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.SUCCESS,
                    Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION, responseVO);
        }

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);
        logger.info(operationRecordVO);

        //6.调用安全原子微服务进行注册
        rank++;
        operationRecordDetailVO = authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.CREATE, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_COMMON_SECURITY_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordDetailVO");
            responseVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            return responseVO;
        }

        RegistryVO registryVO = authenticationDTO.ConvertOrchRegistryVOToRegistryVO(orchRegistryVO, operationRecordId, registryId, partyId);

        if(registryVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "registryVO");
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, responseVO);
        }

        ResponseVO<Boolean> registryResponseVO = createAtomRegistry(registryVO);

        if (registryResponseVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "registryResponseVO");
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, responseVO);
        }

        if(!registryResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE);
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    registryResponseVO.getResponseCode(), registryResponseVO.getResponseDesc(), responseVO);
        }

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);
        logger.info(operationRecordVO);

        //7.调用参与者基础原子微服务进行参与者登记
        rank++;
        operationRecordDetailVO = authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.CREATE, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_PARTY_BASIC_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordDetailVO");
            responseVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
            return responseVO;
        }

        PartyVO partyVO = authenticationDTO.ConvertOrchRegistryVOToPartyVO(orchRegistryVO, operationRecordId, registryId, partyId);

        if (partyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyVO");
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, responseVO);
        }

        ResponseVO<Boolean> partyResponseVO = createAtomParty(partyVO);

        if (partyResponseVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyResponseVO");
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, responseVO);
        }

        if(!partyResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE);
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    partyResponseVO.getResponseCode(), partyResponseVO.getResponseDesc(), responseVO);
        }

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);
        logger.info(operationRecordVO);


        //8.调用参与者相应原子微服务进行参与者细节登记

        //9.记录日志，并返回。
        operationRecordVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
        operationRecordVO.setFinishTime(new Date());
        logger.info(operationRecordVO);
        createAtomOperationRecord(operationRecordVO);
        responseVO.setResponse(true);

        return responseVO;
    }

    /**
     * 方法：通过微服务读取全局操作记录序列号
     * @return 全局唯一操作记录序列号的ResponseVO封装类对象
     * @throws Exception 异常
     */
    private ResponseVO<Integer> readAtomOperationRecordId() throws Exception
    {
        if (sequenceDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "sequenceDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JsonTransformationUtil.transformJSONStringIntoInteger(sequenceDAO.readAtomOperationRecordId());
    }

    /**
     * 方法：通过微服务读取全局注册序列号
     * @return 全局唯一注册序列号的ResponseVO封装类对象
     * @throws Exception 异常
     */
    private ResponseVO<Integer> readAtomRegistryId() throws Exception
    {
        if (sequenceDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "sequenceDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JsonTransformationUtil.transformJSONStringIntoInteger(sequenceDAO.readAtomRegistryId());
    }

    /**
     * 方法：通过微服务读取全局参与者序列号
     * @return 全局唯一参与者序列号的ResponseVO封装类对象
     * @throws Exception 异常
     */
    private ResponseVO<Integer> readAtomPartyId() throws Exception
    {
        if (sequenceDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "sequenceDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JsonTransformationUtil.transformJSONStringIntoInteger(sequenceDAO.readAtomPartyId());
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

        ResponseVO<Boolean> responseVO = JsonTransformationUtil.transformJSONStringIntoBoolean(operationRecordDAO.createAtomOperationRecord(operationRecordVO));

        if (responseVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "responseVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(!responseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE);
            throw new OperationRecordException(Errors.ERROR_BUSINESS_COMMON_OPERATION_RECORD_EXCEPTION);
        }
    }

    /**
     * 方法：通过参与者原子服务按名称读取注册列表
     * TODO 后期要改造成可以按照alias、email等多种方式校验
     * @param name 名称
     * @return 参与者列表的ResponseVO封装对象
     * @throws Exception 异常
     */
    private ResponseVO<List<PartyVO>> readAtomRolesByName(String name) throws Exception
    {
        if (partyDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        ResponseVO<List<PartyVO>> responseVO = new ResponseVO<>();
        responseVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);

        ResponseVO<JSONArray> partyResponseVO = JsonTransformationUtil.transformJSONStringIntoVOArray(this.partyDAO.readAtomRolesByName(name));
        responseVO.setResponseCodeAndDesc(partyResponseVO.getResponseCode(), partyResponseVO.getResponseDesc());
        JSONArray atomPartyVOS = partyResponseVO.getResponse();

        if (atomPartyVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "atomPartyVOS");
            return responseVO;
        }

        List<PartyVO> partyVOS = new ArrayList<>();

        for (Object atomPartyVOObject: atomPartyVOS)
        {
            PartyVO partyVO = JsonTransformationUtil.transformPlainObjectIntoValueObject(atomPartyVOObject, PartyVO.class);

            if(partyVO == null)
                continue;

            partyVOS.add(partyVO);
        }

        responseVO.setResponse(partyVOS);
        return responseVO;
    }

    /**
     * 方法：调用创建注册信息的原子服务进行注册
     * @param registryVO 注册信息值对象
     * @return 是否注册成功的ResponseVO封装对象
     * @throws Exception 异常
     */
    private ResponseVO<Boolean> createAtomRegistry(RegistryVO registryVO) throws Exception
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
     * @return 是否新增成功的ResponseVO封装对象
     * @throws Exception 异常
     */
    private ResponseVO<Boolean> createAtomParty(PartyVO partyVO) throws Exception
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

    /**
     * 方法：提交操作明细记录（按应答码和应答描述设置相应明细并提交）
     * @param operationRecordVO 操作记录值对象
     * @param operationRecordDetailVO 操作记录明细值对象
     * @param status 状态
     * @param responseCode 响应码
     * @param responseDesc 响应描述
     * @param responseVO 原始响应体
     * @return 响应体
     * @throws Exception 异常
     */
    private ResponseVO<Boolean> createOperationRecordDetail(OperationRecordVO operationRecordVO,
                                                            OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO,
                                                            BaseType.STATUS status,
                                                            String responseCode,
                                                            String responseDesc,
                                                            ResponseVO<Boolean> responseVO)
            throws Exception
    {
        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (operationRecordDetailVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordDetailVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (status == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "status");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (responseCode == null || responseCode.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "responseCode");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (responseDesc == null || responseDesc.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "responseDesc");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (responseVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "responseVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        operationRecordDetailVO.setStatus(status.ordinal());
        operationRecordDetailVO.setFinishTime(new Date());
        operationRecordDetailVO.setDescription(responseCode+responseDesc);
        logger.info(operationRecordVO);
        operationRecordVO.addOperationRecordDetail(operationRecordDetailVO);
        createAtomOperationRecord(operationRecordVO);

        responseVO.setResponseCodeAndDesc(responseCode, responseDesc);
        return responseVO;
    }

    /**
     * 方法：提交操作明细记录（按错误体设置相应明细并提交）
     * @param operationRecordVO 操作记录值对象
     * @param operationRecordDetailVO 操作记录明细值对象
     * @param status 状态
     * @param errors 错误体
     * @param responseVO 原始响应体
     * @return 响应体
     * @throws Exception 异常
     */
    private ResponseVO<Boolean> createOperationRecordDetail(OperationRecordVO operationRecordVO,
                                                            OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO,
                                                            BaseType.STATUS status,
                                                            Errors errors,
                                                            ResponseVO<Boolean> responseVO)
            throws Exception
    {
        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (operationRecordDetailVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordDetailVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (status == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "status");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (errors == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "errors");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (responseVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "responseVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        operationRecordDetailVO.setStatus(status.ordinal());
        operationRecordDetailVO.setFinishTime(new Date());
        operationRecordDetailVO.setDescription(errors);
        logger.info(operationRecordVO);
        operationRecordVO.addOperationRecordDetail(operationRecordDetailVO);
        createAtomOperationRecord(operationRecordVO);

        responseVO.setResponseCodeAndDesc(errors);
        return responseVO;
    }

    /**
     * 方法：提交操作明细记录（增加明显并返回）
     * @param operationRecordVO 操作记录值对象
     * @param operationRecordDetailVO 操作记录明细值对象
     * @param status 状态
     * @param errors 错误体
     * @return 添加明细后的操作记录
     * @throws Exception 异常
     */
    private OperationRecordVO createOperationRecordDetail(OperationRecordVO operationRecordVO,
                                                          OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO,
                                                          BaseType.STATUS status,
                                                          Errors errors)
            throws Exception
    {
        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (operationRecordDetailVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordDetailVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (status == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "status");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (errors == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "errors");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        operationRecordDetailVO.setStatus(status.ordinal());
        operationRecordDetailVO.setFinishTime(new Date());
        operationRecordDetailVO.setDescription(errors);
        operationRecordVO.addOperationRecordDetail(operationRecordDetailVO);

        return operationRecordVO;
    }
}
