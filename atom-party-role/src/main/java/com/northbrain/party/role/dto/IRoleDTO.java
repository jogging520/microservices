package com.northbrain.party.role.dto;

import java.util.List;

import com.northbrain.base.common.model.vo.PartyVO;
import com.northbrain.base.common.model.vo.RoleVO;
import com.northbrain.base.common.model.vo.StrategyVO;
import com.northbrain.party.role.model.po.*;

/**
 * 类名：角色传输对象接口
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IRoleDTO
{
    /**
     * 方法：将角色PO转换成VO
     * @param rolePO 角色持久化对象
     * @return 角色值对象
     */
    RoleVO convertToRoleVO(RolePO rolePO) throws Exception;

    /**
     * 方法：将角色VO转换成PO
     * @param roleVO 角色值对象
     * @return 角色持久化对象
     */
    RolePO convertToRolePO(RoleVO roleVO) throws Exception;

    /**
     * 方法：将角色持久化对象转换成角色历史持久化对象
     * @param recordId 操作记录编号
     * @param operateType 操作类型
     * @param rolePO 角色持久化对象
     * @return 角色历史持久化对象
     * @throws Exception 异常
     */
    RoleHisPO convertToRoleHisPO(Integer recordId, String operateType, RolePO rolePO) throws Exception;

    /**
     * 方法：将参与者持久化对象转换成值对象
     * @param partyPO 参与者持久化对象
     * @param partyDetailPOS 参与者明细持久化对象
     * @return 参与者值对象
     * @throws Exception 异常
     */
    PartyVO convertToPartyVO(PartyPO partyPO, List<PartyDetailPO> partyDetailPOS) throws Exception;

    /**
     * 方法：将参与者对象转换成参与者持久化对象
     * @param partyVO 参与者值对象
     * @return 参与者持久化对象
     * @throws Exception 异常
     */
    PartyPO convertToStrategyPO(PartyVO partyVO) throws Exception;

    /**
     * 方法：将参与者值对象转换成参与者明细持久化对象列表
     * @param partyVO 参与者值对象
     * @return 参与者明细持久化对象列表
     * @throws Exception 异常
     */
    List<PartyDetailPO> convertToPartyDetailPOS(PartyVO partyVO) throws Exception;

    /**
     * 方法：将参与者持久化对象转换成策略历史持久化对象
     * @param recordId 操作记录编号
     * @param operateType 操作类型
     * @param partyPO 参与者持久化对象
     * @return 参与者历史持久化对象
     * @throws Exception 异常
     */
    PartyHisPO convertToPartyHisPO(Integer recordId, String operateType, PartyPO partyPO) throws Exception;

    /**
     * 方法：将参与者明细持久化对象转换成参与者明细历史持久化对象
     * @param recordId 操作记录编号
     * @param operateType 操作类型
     * @param partyDetailPO 参与者明细持久化对象
     * @return 参与者明细历史持久化对象
     * @throws Exception 异常
     */
    PartyDetailHisPO convertToPartyDetailHisPO(Integer recordId, String operateType, PartyDetailPO partyDetailPO) throws Exception;

}
