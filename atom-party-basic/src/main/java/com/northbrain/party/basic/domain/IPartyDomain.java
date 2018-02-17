package com.northbrain.party.basic.domain;

import com.northbrain.base.common.model.vo.atom.OrganizationVO;
import com.northbrain.base.common.model.vo.atom.PartyVO;
import com.northbrain.base.common.model.vo.atom.RoleVO;
import com.northbrain.base.common.model.vo.atom.SubjectionVO;

import java.util.List;

/**
 * 类名：参与者域接口
 * 用途：实现参与者的增删改查等功能。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IPartyDomain
{
    /**
     * 方法：根据属性组合获取参与者信息
     * @param idType id类型
     * @param idValue id取值
     * @return 参与者值对象
     * @throws Exception 异常
     */
    List<PartyVO> readPartyByProperties(String idType, String idValue) throws Exception;

    /**
     * 方法：根据名称获取角色信息
     * @param name 名称
     * @return 角色值对象
     * @throws Exception 异常
     */
    List<RoleVO> readRolesByName(String name) throws Exception;

    /**
     * 方法：新建一条角色，根据RoleVO再转换成相应的PO
     * @param roleVO 角色值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    boolean createRole(RoleVO roleVO) throws Exception;

    /**
     * 方法：更新一条角色，根据RoleVO再转换成相应的PO
     * @param roleVO 角色值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    boolean updateRole(RoleVO roleVO) throws Exception;

    /**
     * 方法：删除一条角色，根据RoleVO再转换成相应的PO
     * @param roleVO 角色值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    boolean deleteRole(RoleVO roleVO) throws Exception;

    /**
     * 方法：新建一条参与者，根据PartyVO再转换成相应的PO
     * @param partyVO 参与者值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    boolean createParty(PartyVO partyVO) throws Exception;

    /**
     * 方法：更新一条参与者，根据PartyVO再转换成相应的PO
     * @param partyVO 参与者值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    boolean updateParty(PartyVO partyVO) throws Exception;

    /**
     * 方法：删除一条参与者，根据PartyVO再转换成相应的PO
     * @param partyVO 参与者值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    boolean deleteParty(PartyVO partyVO) throws Exception;

    /**
     * 方法：新建一条组织机构，根据OrganizationVO再转换成相应的PO
     * @param organizationVO 组织机构值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    boolean createOrganization(OrganizationVO organizationVO) throws Exception;

    /**
     * 方法：更新一条组织机构，根据OrganizationVO再转换成相应的PO
     * @param organizationVO 组织机构值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    boolean updateOrganization(OrganizationVO organizationVO) throws Exception;

    /**
     * 方法：删除一条组织机构，根据OrganizationVO再转换成相应的PO
     * @param organizationVO 组织机构值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    boolean deleteOrganization(OrganizationVO organizationVO) throws Exception;

    /**
     * 方法：新建一条隶属关系，根据SubjectionVO再转换成相应的PO
     * @param subjectionVO 隶属关系值对象
     * @return 是否新增成功
     * @throws Exception 异常
     */
    boolean createSubjection(SubjectionVO subjectionVO) throws Exception;

    /**
     * 方法：更新一条隶属关系，根据SubjectionVO再转换成相应的PO
     * @param subjectionVO 隶属关系值对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    boolean updateSubjection(SubjectionVO subjectionVO) throws Exception;

    /**
     * 方法：删除一条隶属关系，根据SubjectionVO再转换成相应的PO
     * @param subjectionVO 隶属关系值对象
     * @return 是否删除成功
     * @throws Exception 异常
     */
    boolean deleteSubjection(SubjectionVO subjectionVO) throws Exception;
}
