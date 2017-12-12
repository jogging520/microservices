package com.northbrain.party.organization.service;

import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.base.common.model.vo.OrganizationVO;
import com.northbrain.base.common.model.vo.SubjectionVO;

/**
 * 类名：组织机构和隶属关系服务接口
 * 用途：封装组织机构等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
public interface IOrganizationService 
{
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
