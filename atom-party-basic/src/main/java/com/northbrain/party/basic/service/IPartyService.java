package com.northbrain.party.basic.service;

import com.northbrain.base.common.model.vo.atom.OrganizationVO;
import com.northbrain.base.common.model.vo.atom.PartyVO;
import com.northbrain.base.common.model.vo.atom.RoleVO;
import com.northbrain.base.common.model.vo.atom.SubjectionVO;
import com.northbrain.base.common.model.vo.basic.ServiceVO;

/**
 * 类名：参与者服务接口
 * 用途：封装参与者增删改查等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
public interface IPartyService
{
    /**
     * 方法：根据属性组合获取参与者
     * @param idType id类型
     * @param idValue id取值
     * @return ServiceVO封装类
     */
    ServiceVO readPartyByProperties(String idType, String idValue);

    /**
     * 方法：根据名称获取权限清单
     * @param name 角色名称
     * @return ServiceVO封装类
     */
    ServiceVO readRolesByName(String name);

    /**
     * 方法：新建一条角色，根据RoleVO再转换成相应的PO
     * @param roleVO 角色值对象
     * @return ServiceVO封装类
     */
    ServiceVO createRole(RoleVO roleVO);

    /**
     * 方法：更新一条角色，根据RoleVO再转换成相应的PO
     * @param roleVO 角色值对象
     * @return ServiceVO封装类
     */
    ServiceVO updateRole(RoleVO roleVO);

    /**
     * 方法：删除一条角色，根据RoleVO再转换成相应的PO
     * @param roleVO 角色值对象
     * @return ServiceVO封装类
     */
    ServiceVO deleteRole(RoleVO roleVO);

    /**
     * 方法：新建一条参与者，根据PartyVO再转换成相应的PO
     * @param partyVO 参与者值对象
     * @return ServiceVO封装类
     */
    ServiceVO createParty(PartyVO partyVO);

    /**
     * 方法：更新一条参与者，根据PartyVO再转换成相应的PO
     * @param partyVO 参与者值对象
     * @return ServiceVO封装类
     */
    ServiceVO updateParty(PartyVO partyVO);

    /**
     * 方法：删除一条参与者，根据PartyVO再转换成相应的PO
     * @param partyVO 参与者值对象
     * @return ServiceVO封装类
     */
    ServiceVO deleteParty(PartyVO partyVO);

    /**
     * 方法：创建一条组织机构
     * @param organizationVO 组织机构值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO createOrganization(OrganizationVO organizationVO);

    /**
     * 方法：更新一条组织机构
     * @param organizationVO 组织机构值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO updateOrganization(OrganizationVO organizationVO);

    /**
     * 方法：删除一条组织机构
     * @param organizationVO 组织机构值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO deleteOrganization(OrganizationVO organizationVO);

    /**
     * 方法：创建一条隶属关系
     * @param subjectionVO 隶属关系值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO createSubjection(SubjectionVO subjectionVO);

    /**
     * 方法：更新一条隶属关系
     * @param subjectionVO 隶属关系值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO updateSubjection(SubjectionVO subjectionVO);

    /**
     * 方法：删除一条隶属关系
     * @param subjectionVO 隶属关系值对象
     * @return 是否操作成功（用ServiceVO封装）
     */
    ServiceVO deleteSubjection(SubjectionVO subjectionVO);
}
