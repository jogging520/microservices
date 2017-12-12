package com.northbrain.party.organization.domain;

import com.northbrain.base.common.model.vo.OrganizationVO;
import com.northbrain.base.common.model.vo.SubjectionVO;

/**
 * 类名：组织机构和隶属关系DOMAIN接口
 * 用途：实现组织机构的增删改查，对DAO进行封装。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IOrganizationDomain
{
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
