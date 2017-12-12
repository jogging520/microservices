package com.northbrain.party.organization.dto;

import com.northbrain.base.common.model.vo.OrganizationVO;
import com.northbrain.base.common.model.vo.SubjectionVO;
import com.northbrain.party.organization.model.po.OrganizationHisPO;
import com.northbrain.party.organization.model.po.OrganizationPO;
import com.northbrain.party.organization.model.po.SubjectionHisPO;
import com.northbrain.party.organization.model.po.SubjectionPO;

/**
 * 类名：组织机构传输对象接口
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IOrganizationDTO
{
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
