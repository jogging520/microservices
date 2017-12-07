package com.northbrain.party.role.domain.impl;

import com.northbrain.base.common.exception.CollectionEmptyException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.RoleVO;
import com.northbrain.party.role.dao.*;
import com.northbrain.party.role.domain.IRoleDomain;
import com.northbrain.party.role.dto.IRoleDTO;
import com.northbrain.party.role.model.po.RolePO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：角色域接口的实现类
 * 用途：实现角色的增删改查等功能。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class RoleDomain implements IRoleDomain
{
    private static Logger logger = Logger.getLogger(RoleDomain.class);

    private final RolePOMapper rolePOMapper;
    private final RoleHisPOMapper roleHisPOMapper;
    private final PartyPOMapper partyPOMapper;
    private final PartyHisPOMapper partyHisPOMapper;
    private final PartyDetailPOMapper partyDetailPOMapper;
    private final PartyDetailHisPOMapper partyDetailHisPOMapper;
    private final IRoleDTO roleDTO;

    @Autowired
    public RoleDomain(RolePOMapper rolePOMapper, RoleHisPOMapper roleHisPOMapper,
                      PartyPOMapper partyPOMapper, PartyHisPOMapper partyHisPOMapper,
                      PartyDetailPOMapper partyDetailPOMapper, PartyDetailHisPOMapper partyDetailHisPOMapper,
                      IRoleDTO roleDTO)
    {
        this.rolePOMapper = rolePOMapper;
        this.roleHisPOMapper = roleHisPOMapper;
        this.partyPOMapper = partyPOMapper;
        this.partyHisPOMapper = partyHisPOMapper;
        this.partyDetailPOMapper = partyDetailPOMapper;
        this.partyDetailHisPOMapper = partyDetailHisPOMapper;
        this.roleDTO = roleDTO;
    }

    /**
     * 方法：根据名称获取角色信息
     *
     * @param name 名称
     * @return 角色值对象
     * @throws Exception 异常
     */
    @Override
    public List<RoleVO> readRolesByName(String name) throws Exception
    {
        if(name == null || name.equals(""))
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + name);
            throw new NumberScopeException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }

        if(rolePOMapper == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "rolePOMapper");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        if(roleDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "roleDTO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

        List<RolePO> rolePOS = rolePOMapper.selectByName(name);

        if(rolePOS == null || rolePOS.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "rolePOS");
            throw new CollectionEmptyException(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
        }

        List<RoleVO> roleVOS = new ArrayList<>();
        RoleVO roleVO;

        for(RolePO rolePO: rolePOS)
        {
            roleVO = roleDTO.convertToRoleVO(rolePO);

            if(roleVO == null)
                continue;

            roleVOS.add(roleVO);
        }

        return roleVOS;
    }
}
