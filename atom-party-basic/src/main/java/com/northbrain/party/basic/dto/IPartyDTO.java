package com.northbrain.party.basic.dto;

import com.northbrain.base.common.model.vo.OrganizationVO;
import com.northbrain.base.common.model.vo.PartyVO;
import com.northbrain.base.common.model.vo.RoleVO;
import com.northbrain.base.common.model.vo.SubjectionVO;
import com.northbrain.party.basic.model.po.*;

import java.util.List;

/**
 * 类名：参与者传输对象接口
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IPartyDTO
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

    /**
     * 方法：将组织机构PO转换成VO
     * @param organizationPO 组织机构持久化对象
     * @return 组织机构值对象
     */
    OrganizationVO convertToOrganizationVO(OrganizationPO organizationPO) throws Exception;

    /**
     * 方法：将组织机构VO转换成PO
     * @param organizationVO 组织机构值对象
     * @return 组织机构持久化对象
     */
    OrganizationPO convertToOrganizationPO(OrganizationVO organizationVO) throws Exception;

    /**
     * 方法：将组织机构PO转换成历史PO
     * @param recordId 操作记录编号
     * @param operateType 操作类型
     * @param organizationPO 组织机构持久化对象
     * @return 组织机构历史持久化对象
     */
    OrganizationHisPO convertToOrganizationHisPO(Integer recordId, String operateType, OrganizationPO organizationPO) throws Exception;

    /**
     * 方法：将隶属关系PO转换成VO
     * @param subjectionPO 隶属关系持久化对象
     * @return 隶属关系值对象
     */
    SubjectionVO convertToSubjectionVO(SubjectionPO subjectionPO) throws Exception;

    /**
     * 方法：将隶属关系VO转换成PO
     * @param subjectionVO 隶属关系值对象
     * @return 隶属关系持久化对象
     */
    SubjectionPO convertToSubjectionPO(SubjectionVO subjectionVO) throws Exception;

    /**
     * 方法：将组织机构PO转换成历史PO
     * @param recordId 操作记录编号
     * @param operateType 操作类型
     * @param subjectionPO 隶属关系持久化对象
     * @return 隶属关系历史持久化对象
     */
    SubjectionHisPO convertToSubjectionHisPO(Integer recordId, String operateType, SubjectionPO subjectionPO) throws Exception;
}
