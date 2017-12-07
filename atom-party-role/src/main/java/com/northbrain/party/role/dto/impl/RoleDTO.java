package com.northbrain.party.role.dto.impl;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.RoleVO;
import com.northbrain.party.role.dto.IRoleDTO;
import com.northbrain.party.role.model.po.RolePO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 类名：角色传输对象接口的实现类
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class RoleDTO implements IRoleDTO
{
    private static Logger logger = Logger.getLogger(RoleDTO.class);
    /**
     * 方法：将角色PO转换成VO
     *
     * @param rolePO 角色持久化对象
     * @return 角色值对象
     */
    @Override
    public RoleVO convertToRoleVO(RolePO rolePO) throws Exception
    {
        if(rolePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "rolePO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        RoleVO roleVO = new RoleVO();

        roleVO.setRoleId(rolePO.getRoleId());
        roleVO.setName(rolePO.getName());
        roleVO.setAlias(rolePO.getAlias());
        roleVO.setDomain(rolePO.getDomain());
        roleVO.setCategory(rolePO.getCategory());
        roleVO.setType(rolePO.getType());
        roleVO.setStatus(rolePO.getStatus());
        roleVO.setCreateTime(rolePO.getCreateTime());
        roleVO.setStatusTime(rolePO.getStatusTime());
        roleVO.setDesciption(rolePO.getDesciption());

        return roleVO;
    }

    /**
     * 方法：将角色VO转换成PO
     *
     * @param roleVO 角色值对象
     * @return 角色持久化对象
     */
    @Override
    public RolePO convertToRolePO(RoleVO roleVO) throws Exception
    {
        if(roleVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "roleVO");
            throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

        RolePO rolePO = new RolePO();

        rolePO.setRoleId(roleVO.getRoleId());
        rolePO.setName(roleVO.getName());
        rolePO.setAlias(roleVO.getAlias());
        rolePO.setDomain(roleVO.getDomain());
        rolePO.setCategory(roleVO.getCategory());
        rolePO.setType(roleVO.getType());
        rolePO.setStatus(roleVO.getStatus());
        rolePO.setCreateTime(roleVO.getCreateTime());
        rolePO.setStatusTime(roleVO.getStatusTime());
        rolePO.setDesciption(roleVO.getDesciption());

        return rolePO;
    }
}
