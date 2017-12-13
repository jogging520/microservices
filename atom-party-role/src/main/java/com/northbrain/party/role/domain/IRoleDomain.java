package com.northbrain.party.role.domain;


import com.northbrain.base.common.model.vo.PartyVO;
import com.northbrain.base.common.model.vo.RoleVO;
import com.northbrain.base.common.model.vo.RoleVO;

import java.util.List;

/**
 * 类名：角色域接口
 * 用途：实现角色的增删改查等功能。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface IRoleDomain
{
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
}
