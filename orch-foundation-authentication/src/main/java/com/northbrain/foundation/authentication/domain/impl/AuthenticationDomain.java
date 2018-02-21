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
import com.northbrain.base.common.model.vo.atom.*;
import com.northbrain.base.common.model.vo.basic.ResponseVO;
import com.northbrain.base.common.model.vo.orch.OrchAccessControlVO;
import com.northbrain.base.common.model.vo.orch.OrchLoginVO;
import com.northbrain.base.common.model.vo.orch.OrchRegistryVO;
import com.northbrain.base.common.model.vo.orch.OrchStrategyVO;
import com.northbrain.base.common.util.JsonTransformationUtil;
import com.northbrain.foundation.authentication.dao.*;
import com.northbrain.foundation.authentication.domain.IAuthenticationDomain;
import com.northbrain.foundation.authentication.dto.IAuthenticationDTO;

/**
 * 类名：鉴权DOMAIN接口的实现类
 * 用途：实现鉴权相关业务逻辑，包括注册、登录、鉴权（单纯鉴权和带token鉴权）等。
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
    private final IFlowControlDAO flowControlDAO;
    private final IAuthenticationDTO authenticationDTO;

    @Autowired
    public AuthenticationDomain(IOperationRecordDAO operationRecordDAO, ISequenceDAO sequenceDAO, IPartyDAO partyDAO,
                                ISecurityDAO securityDAO, IStrategyDAO strategyDAO, IFlowControlDAO flowControlDAO,
                                IAuthenticationDTO authenticationDTO)
    {
        this.operationRecordDAO = operationRecordDAO;
        this.sequenceDAO = sequenceDAO;
        this.partyDAO = partyDAO;
        this.securityDAO = securityDAO;
        this.strategyDAO = strategyDAO;
        this.flowControlDAO = flowControlDAO;
        this.authenticationDTO = authenticationDTO;
    }

    /**
     * 方法：注册
     * 1、获取全局唯一的序列号，作为操作记录、事务的流水号。
     * 2、新生成一条操作记录，并作为分布式事务（最终一致性）的开始，状态为初始状态。
     * 3、调用参与者原子服务检查是否已经存在相应的参与者记录。
     * 4、获取全局唯一的序列号，作为注册编号。
     * 5、获取全局唯一的序列号，作为参与者编号。
     * 6、调用安全原子微服务进行注册。
     * 7、调用参与者基础原子微服务进行参与者登记。
     * 8、记录日志，并返回。
     * @param orchRegistryVO 编排层注册值对象
     * @return 是否注册成功
     */
    @Override
    public ResponseVO<Boolean> createRegistry(OrchRegistryVO orchRegistryVO) throws Exception
    {
        if (orchRegistryVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "orchRegistryVO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
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
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordIdResponseVO");

        if(!operationRecordIdResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE,
                    operationRecordIdResponseVO.getResponseCode(), operationRecordIdResponseVO.getResponseDesc(), "readAtomOperationRecordId");

        int operationRecordId = operationRecordIdResponseVO.getResponse();

        if(operationRecordId <= 0)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE,
                    Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION, "operationRecordId:" + String.valueOf(operationRecordId));

        //2、新生成一条操作记录，并作为分布式事务（最终一致性）的开始。状态为初始状态。
        OperationRecordVO operationRecordVO = authenticationDTO.createOperationRecord(operationRecordId, BaseType.OPERATETYPE.CREATE,
                Constants.BUSINESS_COMMON_OPERATOR_CODE, BaseType.DOMAIN.FOUNDATION, Constants.BUSINESS_FOUNDATION_AUTHENTICATION_ORCH_MICROSERVICE,
                BaseType.STATUS.INITIAL, orchRegistryVO.getDescription());

        if (operationRecordVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordVO");

        logger.info(operationRecordVO);

        //3、调用参与者原子服务检查是否已经存在相应的记录
        rank++;
        OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO =
                authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.READ, BaseType.DOMAIN.PARTY,
                Constants.BUSINESS_PARTY_BASIC_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        ResponseVO<List<PartyVO>> partyResponseVOS = readAtomPartyByProperties(BaseType.IDTYPE.NAME.name(), orchRegistryVO.getName());

        if (partyResponseVOS == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyResponseVOS", responseVO);

        if(!partyResponseVOS.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    partyResponseVOS.getResponseCode(), partyResponseVOS.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "readAtomPartyByProperties", responseVO);

        List<PartyVO> partyVOS = partyResponseVOS.getResponse();

        if (partyVOS == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyVOS", responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);

        //如果已经存在，那么直接返回。
        if(partyVOS.size() > 0)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.SUCCESS,
                    Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION,
                    Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION + "partyVOS", responseVO);

        //5.获取全局唯一的序列号，作为注册编号
        rank++;
        operationRecordDetailVO = authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.READ, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_COMMON_SEQUENCE_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        ResponseVO<Integer> registryIdResponseVO = readAtomRegistryId();

        if (registryIdResponseVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "registryIdResponseVO", responseVO);

        if(!registryIdResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    registryIdResponseVO.getResponseCode(), registryIdResponseVO.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "readAtomRegistryId", responseVO);

        int registryId = registryIdResponseVO.getResponse();

        if(registryId <= 0)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.SUCCESS,
                    Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "registryId" + String.valueOf(registryId), responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);

        //6.获取全局唯一的序列号，作为参与者编号
        rank++;
        operationRecordDetailVO = authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.READ, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_COMMON_SEQUENCE_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        ResponseVO<Integer> partyIdResponseVO = readAtomPartyId();

        if (partyIdResponseVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyIdResponseVO", responseVO);

        if(!partyIdResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    partyIdResponseVO.getResponseCode(), partyIdResponseVO.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "readAtomPartyId", responseVO);

        int partyId = partyIdResponseVO.getResponse();

        if(partyId <= 0)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.SUCCESS,
                    Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "partyId" + String.valueOf(partyId), responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);

        //7.调用安全原子微服务进行注册
        rank++;
        operationRecordDetailVO = authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.CREATE, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_COMMON_SECURITY_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        RegistryVO registryVO = authenticationDTO.convertOrchRegistryVOToRegistryVO(orchRegistryVO, operationRecordId, registryId, partyId);

        if(registryVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "registryVO", responseVO);

        ResponseVO<Boolean> registryResponseVO = createAtomRegistry(registryVO);

        if (registryResponseVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "registryResponseVO", responseVO);

        if(!registryResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()) ||
                !registryResponseVO.getResponse())
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    registryResponseVO.getResponseCode(), registryResponseVO.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "createAtomRegistry", responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);

        //8.调用参与者基础原子微服务进行参与者登记
        rank++;
        operationRecordDetailVO = authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.CREATE, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_PARTY_BASIC_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        PartyVO partyVO = authenticationDTO.convertOrchRegistryVOToPartyVO(orchRegistryVO, operationRecordId, registryId, partyId);

        if (partyVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyVO", responseVO);

        ResponseVO<Boolean> partyResponseVO = createAtomParty(partyVO);

        if (partyResponseVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyResponseVO", responseVO);

        if(!partyResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()) ||
                !partyResponseVO.getResponse())
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    partyResponseVO.getResponseCode(), partyResponseVO.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "createAtomParty", responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);

        //9.记录日志，并返回。
        operationRecordVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
        operationRecordVO.setFinishTime(new Date());
        logger.info(operationRecordVO);
        createAtomOperationRecord(operationRecordVO);
        responseVO.setResponse(true);

        return responseVO;
    }

    /**
     * 方法：登入
     * 1、获取全局唯一的序列号，作为操作记录、事务的流水号
     * 2、新生成一条操作记录，并作为分布式事务（最终一致性）的开始。状态为初始状态。
     * 3、调用参与者原子服务检查是否已经存在相应的记录
     * 4、调用安全原子服务检查是否已经存在相应的注册记录
     * 5、获取全局唯一的序列号，作为登录编号
     * 6、调用安全微服务进行登录操作
     * 7、调用安全微服务，获取token
     * 8、记录日志，并返回。
     * @param orchLoginVO   登录值对象
     * @return 如果登入成功，那么返回token，否则为空
     * @throws Exception 异常
     */
    @Override
    public ResponseVO<String> createLogin(OrchLoginVO orchLoginVO) throws Exception
    {
        if (orchLoginVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "orchLoginVO");
            throw new ArgumentInputException(Errors.ERROR_SYSTEM_INSTANTIATION_EXCEPTION);
        }

        if (authenticationDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "authenticationDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        int rank = 0;
        ResponseVO<String> responseVO = new ResponseVO<>();
        responseVO.setResponse(null);

        //1、获取全局唯一的序列号，作为操作记录、事务的流水号
        ResponseVO<Integer> operationRecordIdResponseVO = readAtomOperationRecordId();

        if(operationRecordIdResponseVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordIdResponseVO");

        if(!operationRecordIdResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE,
                    operationRecordIdResponseVO.getResponseCode(), operationRecordIdResponseVO.getResponseDesc(), "readAtomOperationRecordId");

        int operationRecordId = operationRecordIdResponseVO.getResponse();

        if(operationRecordId <= 0)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE,
                    Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION, "operationRecordId:" + String.valueOf(operationRecordId));

        //2、新生成一条操作记录，并作为分布式事务（最终一致性）的开始。状态为初始状态。
        OperationRecordVO operationRecordVO = authenticationDTO.createOperationRecord(operationRecordId, BaseType.OPERATETYPE.CREATE,
                Constants.BUSINESS_COMMON_OPERATOR_CODE, BaseType.DOMAIN.FOUNDATION, Constants.BUSINESS_FOUNDATION_AUTHENTICATION_ORCH_MICROSERVICE,
                BaseType.STATUS.INITIAL, orchLoginVO.getDescription());

        if (operationRecordVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordVO");

        logger.info(operationRecordVO);

        //3、调用参与者原子服务检查是否已经存在相应的记录
        rank++;
        OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO =
                authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                        BaseType.OPERATETYPE.READ, BaseType.DOMAIN.PARTY,
                        Constants.BUSINESS_PARTY_BASIC_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        ResponseVO<List<PartyVO>> partyResponseVOS = readAtomPartyByProperties(orchLoginVO.getIdType(), orchLoginVO.getIdValue());

        if (partyResponseVOS == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyResponseVOS", responseVO);

        if(!partyResponseVOS.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    partyResponseVOS.getResponseCode(), partyResponseVOS.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "readAtomPartyByProperties", responseVO);

        List<PartyVO> partyVOS = partyResponseVOS.getResponse();

        if (partyVOS == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyVOS", responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);
        logger.info(operationRecordVO);

        //如果不存在，那么需要注册。
        if(partyVOS.size() != 1)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.SUCCESS,
                    Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION,
                    Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION + "partyVOS", responseVO);

        //4、调用安全原子服务检查是否已经存在相应的注册记录
        rank++;
        operationRecordDetailVO =
                authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                        BaseType.OPERATETYPE.READ, BaseType.DOMAIN.SECURITY,
                        Constants.BUSINESS_COMMON_SECURITY_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        ResponseVO<List<RegistryVO>> registerResponseVOS = readAtomRegisterByParty(partyVOS);

        if (registerResponseVOS == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "registerResponseVOS", responseVO);

        if(!registerResponseVOS.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    registerResponseVOS.getResponseCode(), registerResponseVOS.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "readAtomRegisterByParty", responseVO);

        List<RegistryVO> registryVOS = registerResponseVOS.getResponse();

        if (registryVOS == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "registryVOS", responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);

        //如果不存在，说明没有注册，那么直接返回。
        if(registryVOS.size() == 0)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.SUCCESS,
                    Errors.ERROR_BUSINESS_COMMON_SECURITY_REGISTRY_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_SECURITY_REGISTRY_EXCEPTION + "registryVOS", responseVO);

        //5、获取全局唯一的序列号，作为登录编号
        rank++;
        operationRecordDetailVO = authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.READ, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_COMMON_SEQUENCE_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        ResponseVO<Integer> loginIdResponseVO = readAtomLoginId();

        if (loginIdResponseVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "loginIdResponseVO", responseVO);

        if(!loginIdResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    loginIdResponseVO.getResponseCode(), loginIdResponseVO.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "readAtomLoginId", responseVO);

        int loginId = loginIdResponseVO.getResponse();

        if(loginId <= 0)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.SUCCESS,
                    Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "loginId" + String.valueOf(loginId), responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);

        //6、调用安全微服务进行登录操作
        rank++;
        operationRecordDetailVO = authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.CREATE, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_COMMON_SECURITY_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        LoginVO loginVO = authenticationDTO.convertOrchLoginVOToLoginVO(orchLoginVO, operationRecordId, loginId,
                registryVOS.get(0).getRegistryId(), partyVOS.get(0).getPartyId(), partyVOS.get(0).getRoleId());

        if(loginVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "loginVO", responseVO);

        ResponseVO<Boolean> loginResponseVO = createAtomLogin(loginVO);

        if (loginResponseVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "loginResponseVO", responseVO);

        if(!loginResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()) ||
                !loginResponseVO.getResponse())
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    loginResponseVO.getResponseCode(), loginResponseVO.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "createAtomLogin", responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);

        //7、调用安全微服务，获取token
        rank++;
        operationRecordDetailVO = authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                BaseType.OPERATETYPE.CREATE, BaseType.DOMAIN.COMMON,
                Constants.BUSINESS_COMMON_SECURITY_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        TokenVO tokenVO = authenticationDTO.convertOrchLoginVOToTokenVO(orchLoginVO, partyVOS.get(0).getPartyId());

        if(tokenVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "tokenVO", responseVO);

        ResponseVO<String> tokenResponseVO = createAtomToken(tokenVO);

        if (tokenResponseVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "tokenResponseVO", responseVO);

        if(!tokenResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    tokenResponseVO.getResponseCode(), tokenResponseVO.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "createAtomToken", responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);

        //8.记录日志，并返回。
        operationRecordVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
        operationRecordVO.setFinishTime(new Date());
        logger.info(operationRecordVO);
        createAtomOperationRecord(operationRecordVO);
        responseVO.setResponse(tokenResponseVO.getResponse());

        return responseVO;
    }

    /**
     * 方法：访问控制
     * 1、获取全局唯一的序列号，作为操作记录、事务的流水号
     * 2、新生成一条操作记录，并作为分布式事务（最终一致性）的开始。状态为初始状态。
     * 3、获取token的值
     * 4、校验token是否处于登录状态
     * 5、获取服务对应的权限id
     * 6、根据token中的partyId、roleId、organizationId校验该服务是否有权限被访问
     * 7、校验是否已经达到流控的门限
     * 8、记录日志，并返回。
     * @param orchAccessControlVO 编排层访问控制值对象
     * @return 是否运行访问控制
     */
    @Override
    public ResponseVO<Boolean> readAccessControl(OrchAccessControlVO orchAccessControlVO) throws Exception
    {
        if (orchAccessControlVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "orchAccessControlVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (authenticationDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "authenticationDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        int rank = 0;
        ResponseVO<Boolean> responseVO = new ResponseVO<>();
        responseVO.setResponse(null);

        //1、获取全局唯一的序列号，作为操作记录、事务的流水号
        ResponseVO<Integer> operationRecordIdResponseVO = readAtomOperationRecordId();

        if(operationRecordIdResponseVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordIdResponseVO");

        if(!operationRecordIdResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE,
                    operationRecordIdResponseVO.getResponseCode(), operationRecordIdResponseVO.getResponseDesc(), "readAtomOperationRecordId");

        int operationRecordId = operationRecordIdResponseVO.getResponse();

        if(operationRecordId <= 0)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE,
                    Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION, "operationRecordId:" + String.valueOf(operationRecordId));

        //2、新生成一条操作记录，并作为分布式事务（最终一致性）的开始。状态为初始状态。
        OperationRecordVO operationRecordVO = authenticationDTO.createOperationRecord(operationRecordId, BaseType.OPERATETYPE.CREATE,
                Constants.BUSINESS_COMMON_OPERATOR_CODE, BaseType.DOMAIN.FOUNDATION, Constants.BUSINESS_FOUNDATION_AUTHENTICATION_ORCH_MICROSERVICE,
                BaseType.STATUS.INITIAL, orchAccessControlVO.getDescription());

        if (operationRecordVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordVO");

        logger.info(operationRecordVO);

        //3、获取token的值
        rank++;
        OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO =
                authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                        BaseType.OPERATETYPE.READ, BaseType.DOMAIN.PARTY,
                        Constants.BUSINESS_COMMON_SECURITY_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        ResponseVO<TokenVO> tokenResponseVO = readAtomToken(orchAccessControlVO.getJsonWebToken());

        if (tokenResponseVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "tokenResponseVO", responseVO);

        if(!tokenResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    tokenResponseVO.getResponseCode(), tokenResponseVO.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "readAtomToken", responseVO);

        TokenVO tokenVO = tokenResponseVO.getResponse();

        if (tokenVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "tokenVO", responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);
        logger.info(operationRecordVO);

        //4、校验token是否处于登录状态
        rank++;
        operationRecordDetailVO =
                authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                        BaseType.OPERATETYPE.READ, BaseType.DOMAIN.PARTY,
                        Constants.BUSINESS_COMMON_SECURITY_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        ResponseVO<LoginVO> loginResponseVO = readAtomLoginByToken(tokenVO);

        if (loginResponseVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "loginResponseVO", responseVO);

        if(!loginResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    loginResponseVO.getResponseCode(), loginResponseVO.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "readAtomLoginByToken", responseVO);

        LoginVO loginVO = loginResponseVO.getResponse();

        if (loginVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_SECURITY_LOGIN_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_SECURITY_LOGIN_EXCEPTION + "loginVO", responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);

        //5、获取服务对应的权限id
        rank++;
        operationRecordDetailVO =
                authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                        BaseType.OPERATETYPE.READ, BaseType.DOMAIN.PARTY,
                        Constants.BUSINESS_COMMON_SECURITY_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        //权限名为restful的rui的前缀
        ResponseVO<List<PrivilegeVO>> privilegeResponseVO = readAtomPrivilegeByName(
                BaseType.PRIVILEGEDOMAIN.ORCHSERVICE.name(), orchAccessControlVO.getHttpMethod(), orchAccessControlVO.getUri());

        if (privilegeResponseVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "privilegeResponseVO", responseVO);

        if(!privilegeResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    privilegeResponseVO.getResponseCode(), privilegeResponseVO.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "readAtomPrivilegeByName", responseVO);

        List<PrivilegeVO> privilegeVOS = privilegeResponseVO.getResponse();

        if (privilegeVOS == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "privilegeVOS", responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);

        //没有找到该权限定义
        if(privilegeVOS.size() != 1)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.SUCCESS,
                    Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION,
                    Errors.ERROR_BUSINESS_PARTY_BASIC_EXCEPTION + "privilegeVOS", responseVO);

        //6、根据token中的partyId、roleId、organizationId校验该服务是否有权限被访问
        rank++;
        operationRecordDetailVO =
                authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                        BaseType.OPERATETYPE.READ, BaseType.DOMAIN.PARTY,
                        Constants.BUSINESS_COMMON_SECURITY_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        ResponseVO<List<AccessControlVO>> accessControlResponseVO = readAtomAccessControlsByRole(tokenVO.getRoleId(),
                tokenVO.getOrganizationId(), BaseType.PRIVILEGEDOMAIN.ORCHSERVICE.name(), privilegeVOS.get(0).getPrivilegeId());

        if (accessControlResponseVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "accessControlResponseVO", responseVO);

        if(!accessControlResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    accessControlResponseVO.getResponseCode(), accessControlResponseVO.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "readAtomAccessControlsByRole", responseVO);

        List<AccessControlVO> accessControlVOS = accessControlResponseVO.getResponse();

        if (accessControlVOS == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "accessControlVOS", responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);

        //没有访问控制定义，不允许访问
        if(accessControlVOS.size() != 1)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.SUCCESS,
                    Errors.ERROR_BUSINESS_COMMON_SECURITY_ACCESS_CONTROL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_SECURITY_ACCESS_CONTROL_EXCEPTION + "accessControlVOS", responseVO);

        //7、校验是否已经达到流控的门限
        rank++;
        operationRecordDetailVO =
                authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                        BaseType.OPERATETYPE.READ, BaseType.DOMAIN.PARTY,
                        Constants.BUSINESS_COMMON_FLOW_CONTROL_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        // TODO 流控的值要定义，或者传固定值。
        FlowControlVO flowControlVO = authenticationDTO.convertTokenVOToFlowControlVO(tokenVO,
                BaseType.FLOWCONTROLTYPE.THRESHOLD, 0);

        if (flowControlVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "flowControlVO", responseVO);

        ResponseVO<Boolean> flowControlResponseVO = readAtomFlowControl(flowControlVO);

        if (flowControlResponseVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "flowControlResponseVO", responseVO);

        if(!flowControlResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    flowControlResponseVO.getResponseCode(), flowControlResponseVO.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "readAtomFlowControl", responseVO);

        boolean flowControl = flowControlResponseVO.getResponse();

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);

        //已经达到流控的限制范围，直接返回。
        if(flowControl)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.SUCCESS,
                    Errors.ERROR_BUSINESS_COMMON_FLOW_CONTROL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_FLOW_CONTROL_EXCEPTION + "flowControl", responseVO);

        //8、记录日志，并返回。
        operationRecordVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
        operationRecordVO.setFinishTime(new Date());
        logger.info(operationRecordVO);
        createAtomOperationRecord(operationRecordVO);
        responseVO.setResponse(true);

        return responseVO;
    }

    /**
     * 方法：读取策略
     * 1、获取全局唯一的序列号，作为操作记录、事务的流水号
     * 2、新生成一条操作记录，并作为分布式事务（最终一致性）的开始。状态为初始状态。
     * 3、调用策略原子服务，获取指定条件的策略清单
     * 4、记录日志，并返回。
     * @param orchStrategyVO 编排层策略值对象
     * @return 策略清单
     * @throws Exception 异常
     */
    @Override
    public ResponseVO<List<StrategyVO>> readStrategiesByName(OrchStrategyVO orchStrategyVO) throws Exception
    {
        if (orchStrategyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "orchStrategyVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (authenticationDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "authenticationDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        int rank = 0;
        ResponseVO<List<StrategyVO>> responseVO = new ResponseVO<>();
        responseVO.setResponse(null);

        //1、获取全局唯一的序列号，作为操作记录、事务的流水号
        ResponseVO<Integer> operationRecordIdResponseVO = readAtomOperationRecordId();

        if(operationRecordIdResponseVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordIdResponseVO");

        if(!operationRecordIdResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE,
                    operationRecordIdResponseVO.getResponseCode(), operationRecordIdResponseVO.getResponseDesc(), "readAtomOperationRecordId");

        int operationRecordId = operationRecordIdResponseVO.getResponse();

        if(operationRecordId <= 0)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE,
                    Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION, "operationRecordId:" + String.valueOf(operationRecordId));

        //2、新生成一条操作记录，并作为分布式事务（最终一致性）的开始。状态为初始状态。
        OperationRecordVO operationRecordVO = authenticationDTO.createOperationRecord(operationRecordId, BaseType.OPERATETYPE.CREATE,
                Constants.BUSINESS_COMMON_OPERATOR_CODE, BaseType.DOMAIN.FOUNDATION, Constants.BUSINESS_FOUNDATION_AUTHENTICATION_ORCH_MICROSERVICE,
                BaseType.STATUS.INITIAL, orchStrategyVO.getDescription());

        if (operationRecordVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordVO");

        logger.info(operationRecordVO);

        //3、调用策略原子服务，获取指定条件的策略清单
        rank++;
        OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO =
                authenticationDTO.createOperationRecordDetail(operationRecordVO, rank,
                        BaseType.OPERATETYPE.READ, BaseType.DOMAIN.PARTY,
                        Constants.BUSINESS_COMMON_STRATEGY_ATOM_MICROSERVICE, BaseType.STATUS.INITIAL);

        if (operationRecordDetailVO == null)
            return updateFailureResponseVO(responseVO, Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION, "operationRecordDetailVO");

        ResponseVO<List<StrategyVO>> strategyResponseVO = readAtomStrategiesByName(orchStrategyVO);

        if (strategyResponseVO == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyResponseVO", responseVO);

        if(!strategyResponseVO.getResponseCode().equals(Errors.SUCCESS_EXECUTE.getCode()))
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    strategyResponseVO.getResponseCode(), strategyResponseVO.getResponseDesc(),
                    Errors.ERROR_BUSINESS_COMMON_CALL_ATOM_SERVICE + "readAtomStrategiesByName", responseVO);

        List<StrategyVO> strategyVOS = strategyResponseVO.getResponse();

        if (strategyVOS == null)
            return createOperationRecordDetail(operationRecordVO, operationRecordDetailVO, BaseType.STATUS.FAILURE,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION,
                    Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyVOS", responseVO);

        operationRecordVO = createOperationRecordDetail(operationRecordVO, operationRecordDetailVO,
                BaseType.STATUS.SUCCESS, Errors.SUCCESS_EXECUTE);
        logger.info(operationRecordVO);

        //4、记录日志，并返回。
        operationRecordVO.setStatus(BaseType.STATUS.SUCCESS.ordinal());
        operationRecordVO.setFinishTime(new Date());
        logger.info(operationRecordVO);
        createAtomOperationRecord(operationRecordVO);
        responseVO.setResponse(strategyVOS);

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

        return JsonTransformationUtil.transformJSONString(sequenceDAO.readAtomOperationRecordId());
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

        return JsonTransformationUtil.transformJSONString(sequenceDAO.readAtomRegistryId());
    }

    /**
     * 方法：通过微服务读取全局登录序列号
     * @return 全局唯一登录序列号的ResponseVO封装类对象
     * @throws Exception 异常
     */
    private ResponseVO<Integer> readAtomLoginId() throws Exception
    {
        if (sequenceDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "sequenceDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JsonTransformationUtil.transformJSONString(sequenceDAO.readAtomLoginId());
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

        return JsonTransformationUtil.transformJSONString(sequenceDAO.readAtomPartyId());
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
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (operationRecordDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        ResponseVO<Boolean> responseVO = JsonTransformationUtil.transformJSONString(operationRecordDAO.createAtomOperationRecord(operationRecordVO));

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
     * 方法：通过参与者原子服务按名称读取参与者列表
     * @param idType id类型
     * @param idValue id取值
     * @return 参与者列表的ResponseVO封装对象
     * @throws Exception 异常
     */
    private ResponseVO<List<PartyVO>> readAtomPartyByProperties(String idType, String idValue) throws Exception
    {
        if (idType == null || idType.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "idType");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (idValue == null || idValue.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "idValue");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (partyDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        ResponseVO<List<PartyVO>> responseVO = new ResponseVO<>();
        responseVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);

        ResponseVO<JSONArray> partyResponseVO = JsonTransformationUtil.transformJSONStringIntoVOArray(this.partyDAO.readAtomPartyByProperties(idType, idValue));
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
     * 方法：调用原子服务获取特定条件的策略清单
     * @param orchStrategyVO 编排层策略值对象
     * @return 策略清单
     * @throws Exception 异常
     */
    private ResponseVO<List<StrategyVO>> readAtomStrategiesByName(OrchStrategyVO orchStrategyVO) throws Exception
    {
        if (orchStrategyVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "orchStrategyVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (strategyDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "strategyDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        ResponseVO<List<StrategyVO>> responseVO = new ResponseVO<>();
        responseVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);

        ResponseVO<JSONArray> strategyResponseVO = JsonTransformationUtil.transformJSONStringIntoVOArray(this.strategyDAO.readAtomStrategiesByName(orchStrategyVO));
        responseVO.setResponseCodeAndDesc(strategyResponseVO.getResponseCode(), strategyResponseVO.getResponseDesc());
        JSONArray atomStrategyVOS = strategyResponseVO.getResponse();

        if (atomStrategyVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "atomStrategyVOS");
            return responseVO;
        }

        List<StrategyVO> strategyVOS = new ArrayList<>();

        for (Object atomStrategyVOObject: atomStrategyVOS)
        {
            StrategyVO strategyVO = JsonTransformationUtil.transformPlainObjectIntoValueObject(atomStrategyVOObject, StrategyVO.class);

            if(strategyVO == null)
                continue;

            strategyVOS.add(strategyVO);
        }

        responseVO.setResponse(strategyVOS);
        return responseVO;
    }

    /**
     * 方法：通过安全原子服务按名称读取注册列表
     * @param partyVOS 参与者列表
     * @return 注册列表的ResponseVO封装对象
     * @throws Exception 异常
     */
    private ResponseVO<List<RegistryVO>> readAtomRegisterByParty(List<PartyVO> partyVOS) throws Exception
    {
        if (partyVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "partyVOS");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (securityDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        ResponseVO<List<RegistryVO>> responseVO = new ResponseVO<>();
        responseVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);

        List<Integer> partyIdS = new ArrayList<>();

        for(PartyVO partyVO: partyVOS)
            partyIdS.add(partyVO.getPartyId());

        ResponseVO<JSONArray> registryResponseVO = JsonTransformationUtil.transformJSONStringIntoVOArray(
                this.securityDAO.readAtomRegistryByParty((Integer[]) partyIdS.toArray()));
        responseVO.setResponseCodeAndDesc(registryResponseVO.getResponseCode(), registryResponseVO.getResponseDesc());
        JSONArray atomRegistryVOS = registryResponseVO.getResponse();

        if (atomRegistryVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "atomRegistryVOS");
            return responseVO;
        }

        List<RegistryVO> registryVOS = new ArrayList<>();

        for (Object atomRegistryVOObject: atomRegistryVOS)
        {
            RegistryVO registryVO = JsonTransformationUtil.transformPlainObjectIntoValueObject(atomRegistryVOObject, RegistryVO.class);

            if(registryVO == null)
                continue;

            registryVOS.add(registryVO);
        }

        responseVO.setResponse(registryVOS);
        return responseVO;
    }

    /**
     * 方法：通过安全原子服务按名称读取登录列表
     * @param partyId 参与者编号
     * @return 登录列表的ResponseVO封装对象
     * @throws Exception 异常
     */
    private ResponseVO<List<LoginVO>> readAtomLoginByParty(int partyId) throws Exception
    {
        if (partyId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "partyId");
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if (securityDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        ResponseVO<List<LoginVO>> responseVO = new ResponseVO<>();
        responseVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);

        ResponseVO<JSONArray> loginResponseVO = JsonTransformationUtil.transformJSONStringIntoVOArray(
                this.securityDAO.readAtomLoginsByParty(partyId));
        responseVO.setResponseCodeAndDesc(loginResponseVO.getResponseCode(), loginResponseVO.getResponseDesc());
        JSONArray atomLoginVOS = loginResponseVO.getResponse();

        if (atomLoginVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "atomLoginVOS");
            return responseVO;
        }

        List<LoginVO> loginVOS = new ArrayList<>();

        for (Object atomLoginVOObject: atomLoginVOS)
        {
            LoginVO loginVO = JsonTransformationUtil.transformPlainObjectIntoValueObject(atomLoginVOObject, LoginVO.class);

            if(loginVO == null)
                continue;

            loginVOS.add(loginVO);
        }

        responseVO.setResponse(loginVOS);
        return responseVO;
    }

    /**
     * 方法：根据token中的属性判断当前的登录状态
     * @param tokenVO 令牌值对象
     * @return 是否处于登录状态
     * @throws Exception 异常
     */
    private ResponseVO<LoginVO> readAtomLoginByToken(TokenVO tokenVO) throws Exception
    {
        if (tokenVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "tokenVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (securityDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JsonTransformationUtil.transformJSONStringIntoValueObject(
                this.securityDAO.readAtomLoginByToken(tokenVO), LoginVO.class);
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
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (securityDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JsonTransformationUtil.transformJSONString(this.securityDAO.createAtomRegistry(registryVO));
    }

    /**
     * 方法：调用创建登录信息的原子服务进行注册
     * @param loginVO 登录信息值对象
     * @return 是否登录成功的ResponseVO封装对象
     * @throws Exception 异常
     */
    private ResponseVO<Boolean> createAtomLogin(LoginVO loginVO) throws Exception
    {
        if (loginVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "loginVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (securityDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JsonTransformationUtil.transformJSONString(this.securityDAO.createAtomLogin(loginVO));
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
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (partyDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "partyDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JsonTransformationUtil.transformJSONString(this.partyDAO.createAtomParty(partyVO));
    }

    /**
     * 方法：通过角色编号获取可访问的列表
     * @param roleId 角色编号
     * @param organizationId 组织机构编码
     * @param domain 角色归属域
     * @param privilegeId 权限编码
     * @return 权限访问控制列表
     * @throws Exception 异常
     */
    private ResponseVO<List<AccessControlVO>> readAtomAccessControlsByRole(int roleId, int organizationId, String domain, int privilegeId)
            throws Exception
    {
        if (roleId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "roleId");
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if (organizationId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "organizationId");
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if (domain == null || domain.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "domain");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (privilegeId <= 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE + "privilegeId");
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }

        if (securityDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        ResponseVO<List<AccessControlVO>> responseVO = new ResponseVO<>();
        responseVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);

        ResponseVO<JSONArray> accessControlResponseVO = JsonTransformationUtil.transformJSONStringIntoVOArray(
                this.securityDAO.readAtomAccessControlsByRole(roleId, organizationId, domain, privilegeId));
        responseVO.setResponseCodeAndDesc(accessControlResponseVO.getResponseCode(), accessControlResponseVO.getResponseDesc());
        JSONArray atomAccessControlResponseVOS = accessControlResponseVO.getResponse();

        if (atomAccessControlResponseVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "atomAccessControlResponseVOS");
            return responseVO;
        }

        List<AccessControlVO> accessControlVOS = new ArrayList<>();

        for (Object atomAccessControlVOObject: atomAccessControlResponseVOS)
        {
            AccessControlVO accessControlVO = JsonTransformationUtil.transformPlainObjectIntoValueObject(atomAccessControlVOObject, AccessControlVO.class);

            if(accessControlVO == null)
                continue;

            accessControlVOS.add(accessControlVO);
        }

        responseVO.setResponse(accessControlVOS);
        return responseVO;
    }

    /**
     * 方法：调用原子服务获取按归属域和名称筛选的权限列表
     * @param domain 权限归属域
     * @param category 权限类别
     * @param name 权限名称
     * @return 权限列表
     * @throws Exception 异常
     */
    private ResponseVO<List<PrivilegeVO>> readAtomPrivilegeByName(String domain, String category, String name) throws Exception
    {
        if (domain == null || domain.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "domain");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (category == null || category.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "category");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (name == null || name.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "name");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (securityDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        ResponseVO<List<PrivilegeVO>> responseVO = new ResponseVO<>();
        responseVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);

        ResponseVO<JSONArray> privilegeResponseVO = JsonTransformationUtil.transformJSONStringIntoVOArray(
                this.securityDAO.readAtomPrivilegeByName(domain, category, name));
        responseVO.setResponseCodeAndDesc(privilegeResponseVO.getResponseCode(), privilegeResponseVO.getResponseDesc());
        JSONArray atomPrivilegeResponseVOS = privilegeResponseVO.getResponse();

        if (atomPrivilegeResponseVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "atomPrivilegeResponseVOS");
            return responseVO;
        }

        List<PrivilegeVO> privilegeVOS = new ArrayList<>();

        for (Object atomPrivilegeVOObject: atomPrivilegeResponseVOS)
        {
            PrivilegeVO privilegeVO = JsonTransformationUtil.transformPlainObjectIntoValueObject(atomPrivilegeVOObject, PrivilegeVO.class);

            if(privilegeVO == null)
                continue;

            privilegeVOS.add(privilegeVO);
        }

        responseVO.setResponse(privilegeVOS);
        return responseVO;
    }

    /**
     * 方法：获取流量控制是否放行的权限
     * @param flowControlVO 流控值对象
     * @return 是否放行的ResponseVO封装对象
     * @throws Exception 异常
     */
    private ResponseVO<Boolean> readAtomFlowControl(FlowControlVO flowControlVO) throws Exception
    {
        if (flowControlVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "flowControlVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (flowControlDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "flowControlDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JsonTransformationUtil.transformJSONString(this.flowControlDAO.readAtomFlowControl(flowControlVO));
    }

    /**
     * 方法：创建json web token
     * @param tokenVO token值对象
     * @return token值
     * @throws Exception 异常
     */
    private ResponseVO<String> createAtomToken(TokenVO tokenVO) throws Exception
    {
        if (tokenVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "tokenVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (securityDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JsonTransformationUtil.transformJSONString(this.securityDAO.createAtomToken(tokenVO));
    }

    /**
     * 方法：创建json web token
     * @param jsonWebToken JWT
     * @return token值对象
     * @throws Exception 异常
     */
    private ResponseVO<TokenVO> readAtomToken(String jsonWebToken) throws Exception
    {
        if (jsonWebToken == null || jsonWebToken.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "jsonWebToken");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (securityDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "securityDAO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        return JsonTransformationUtil.transformJSONStringIntoValueObject(this.securityDAO.readAtomToken(jsonWebToken), TokenVO.class);
    }

    /**
     * 方法：提交操作明细记录（按应答码和应答描述设置相应明细并提交）
     * @param operationRecordVO 操作记录值对象
     * @param operationRecordDetailVO 操作记录明细值对象
     * @param status 状态
     * @param responseCode 响应码
     * @param responseDesc 响应描述
     * @param logErrors 日志错误描述
     * @param responseVO 原始响应体
     * @return 响应体
     * @throws Exception 异常
     */
    private <T> ResponseVO<T> createOperationRecordDetail(OperationRecordVO operationRecordVO,
                                                          OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO,
                                                          BaseType.STATUS status,
                                                          String responseCode,
                                                          String responseDesc,
                                                          String logErrors,
                                                          ResponseVO<T> responseVO)
            throws Exception
    {
        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (operationRecordDetailVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordDetailVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (status == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "status");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (responseCode == null || responseCode.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "responseCode");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (responseDesc == null || responseDesc.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "responseDesc");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (responseVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "responseVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        //先打印错误描述
        logger.error(logErrors);

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
     * @param operationErrors 操作记录错误描述体
     * @param logErrors 日志错误描述
     * @param responseVO 原始响应体
     * @return 响应体
     * @throws Exception 异常
     */
    private <T> ResponseVO<T> createOperationRecordDetail(OperationRecordVO operationRecordVO,
                                                          OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO,
                                                          BaseType.STATUS status,
                                                          Errors operationErrors,
                                                          String logErrors,
                                                          ResponseVO<T> responseVO) throws Exception
    {
        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (operationRecordDetailVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordDetailVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (status == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "status");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (operationErrors == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationErrors");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (responseVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "responseVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        //先打印错误信息
        logger.error(logErrors);

        //再赋值
        operationRecordDetailVO.setStatus(status.ordinal());
        operationRecordDetailVO.setFinishTime(new Date());
        operationRecordDetailVO.setDescription(operationErrors);
        logger.info(operationRecordVO);
        operationRecordVO.addOperationRecordDetail(operationRecordDetailVO);
        createAtomOperationRecord(operationRecordVO);

        responseVO.setResponseCodeAndDesc(operationErrors);
        return responseVO;
    }

    /**
     * 方法：提交操作明细记录（增加明显并返回）
     * @param operationRecordVO 操作记录值对象
     * @param operationRecordDetailVO 操作记录明细值对象
     * @param status 状态
     * @param errors 操作记录错误体
     * @return 添加明细后的操作记录
     * @throws Exception 异常
     */
    private OperationRecordVO createOperationRecordDetail(OperationRecordVO operationRecordVO,
                                                          OperationRecordVO.OperationRecordDetailVO operationRecordDetailVO,
                                                          BaseType.STATUS status,
                                                          Errors errors) throws Exception
    {
        if (operationRecordVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (operationRecordDetailVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "operationRecordDetailVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (status == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "status");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (errors == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "errors");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        operationRecordDetailVO.setStatus(status.ordinal());
        operationRecordDetailVO.setFinishTime(new Date());
        operationRecordDetailVO.setDescription(errors);
        operationRecordVO.addOperationRecordDetail(operationRecordDetailVO);

        logger.info(operationRecordVO);

        return operationRecordVO;
    }

    /**
     * 方法：更新带有错误信息的ResponseVO
     * @param responseVO 应答信息值对象
     * @param logErrors 日志错误
     * @param responseErrors  应答错误
     * @param attachment 附属错误描述
     * @param <T> 应答信息值对象的类型
     * @return 应答信息值对象
     * @throws Exception 异常
     */
    private <T> ResponseVO<T> updateFailureResponseVO(ResponseVO<T> responseVO, Errors logErrors, Errors responseErrors,
                                                      String attachment) throws Exception
    {
        if (responseVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "responseVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (logErrors == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "logErrors");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (responseErrors == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "responseErrors");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.error(logErrors + attachment);
        responseVO.setResponseCodeAndDesc(responseErrors);
        return responseVO;
    }

    /**
     * 方法：更新带有错误信息的ResponseVO
     * @param responseVO 应答信息值对象
     * @param logErrors 日志错误
     * @param responseCode 应答错误码
     * @param responseDesc 应答错误描述
     * @param attachment 附属错误描述
     * @param <T> 应答信息值对象的类型
     * @return 应答信息值对象
     * @throws Exception 异常
     */
    private <T> ResponseVO<T> updateFailureResponseVO(ResponseVO<T> responseVO, Errors logErrors, String responseCode,
                                                      String responseDesc, String attachment) throws Exception
    {
        if (responseVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "responseVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (logErrors == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "logErrors");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (responseCode == null || responseCode.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "responseCode");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if (responseDesc == null || responseDesc.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "responseDesc");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.error(logErrors + attachment);
        responseVO.setResponseCodeAndDesc(responseCode, responseDesc);
        return responseVO;
    }
}
