package com.northbrain.foundation.authentication.dto;

import com.northbrain.base.common.model.vo.atom.PartyVO;
import com.northbrain.base.common.model.vo.atom.RegistryVO;
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
    RegistryVO ConvertOrchRegistryVOToRegistryVO(OrchRegistryVO orchRegistryVO, int recordId, int registryId, int partyId) throws Exception;

    /**
     * 方法：将OrchRegistryVO值对象转换成PartyVO值对象
     * @param orchRegistryVO 编排层注册值对象
     * @param recordId 操作流水记录
     * @param registryId 注册编码
     * @param partyId 参与者编码
     * @return 微服务层参与者值对象
     * @throws Exception 异常
     */
    PartyVO ConvertOrchRegistryVOToPartyVO(OrchRegistryVO orchRegistryVO, int recordId, int registryId, int partyId) throws Exception;
}
