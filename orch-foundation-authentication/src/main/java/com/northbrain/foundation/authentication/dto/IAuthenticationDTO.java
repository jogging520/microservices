package com.northbrain.foundation.authentication.dto;

import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.vo.atom.*;
import com.northbrain.base.common.model.vo.orch.OrchLoginVO;
import com.northbrain.base.common.model.vo.orch.OrchRegistryVO;

/**
 * 类名：鉴权数据转换对象接口
 * 用途：转换鉴权相关的数据，包括注册、登录、权限、参数等
 * @author Jiakun
 * @version 1.0
 */
public interface IAuthenticationDTO
{
    /**
     * 方法：将OrchRegistryVO值对象转换成RegistryVO值对象
     * @param orchRegistryVO 编排层注册值对象
     * @param recordId 操作流水记录
     * @param registryId 注册编码
     * @param partyId 参与者编码
     * @return 微服务层注册值对象
     * @throws Exception 异常
     */
    RegistryVO convertOrchRegistryVOToRegistryVO(OrchRegistryVO orchRegistryVO, int recordId, int registryId, int partyId) throws Exception;

    /**
     * 方法：将OrchRegistryVO值对象转换成PartyVO值对象
     * @param orchRegistryVO 编排层注册值对象
     * @param recordId 操作流水记录
     * @param registryId 注册编码
     * @param partyId 参与者编码
     * @return 微服务层注册值对象
     * @throws Exception 异常
     */
    PartyVO convertOrchRegistryVOToPartyVO(OrchRegistryVO orchRegistryVO, int recordId, int registryId, int partyId) throws Exception;

    /**
     * 方法：将编排层OrchLoginVO对象转换成原子服务的LoginVO值对象
     * @param orchLoginVO 编排层登录值对象
     * @param recordId 操作流水记录
     * @param loginId 登录编号
     * @param registryId 注册编码
     * @param partyId 参与者编码
     * @param roleId 角色编码
     * @return 微服务层登录值对象
     * @throws Exception 异常
     */
    LoginVO convertOrchLoginVOToLoginVO(OrchLoginVO orchLoginVO, int recordId, int loginId, int registryId,
                                        int partyId, int roleId) throws Exception;

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
    OperationRecordVO createOperationRecord(int operationRecordId, BaseType.OPERATETYPE operationType, int operatorId,
                                            BaseType.DOMAIN domain, String serviceName, BaseType.STATUS status, String description)
            throws Exception;

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
    OperationRecordVO.OperationRecordDetailVO createOperationRecordDetail(OperationRecordVO operationRecordVO, int rank, BaseType.OPERATETYPE operationType,
                                                                          BaseType.DOMAIN domain, String serviceName, BaseType.STATUS status)
            throws Exception;

    /**
     * 方法：将登录参与者VO转换成令牌VO
     * @param orchLoginVO 登录信息VO
     * @param partyId 参与者编码
     * @return 令牌VO
     * @throws Exception 异常
     */
    TokenVO convertOrchLoginVOToTokenVO(OrchLoginVO orchLoginVO, int partyId) throws Exception;

    /**
     * 方法：将token值对象转换成流控控制值对象
     * @param tokenVO 令牌值对象
     * @param flowControlType 流控类型
     * @param flow 流控值
     * @return 流量控制值对象
     * @throws Exception 异常
     */
    FlowControlVO convertTokenVOToFlowControlVO(TokenVO tokenVO, BaseType.FLOWCONTROLTYPE flowControlType, float flow) throws Exception;
}
