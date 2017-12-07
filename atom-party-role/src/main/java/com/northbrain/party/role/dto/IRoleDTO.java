package com.northbrain.party.role.dto;

import com.northbrain.base.common.model.vo.RoleVO;
import com.northbrain.party.role.model.po.RolePO;

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
}
