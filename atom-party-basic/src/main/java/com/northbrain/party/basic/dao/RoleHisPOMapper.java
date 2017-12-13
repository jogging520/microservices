package com.northbrain.party.basic.dao;

import com.northbrain.party.basic.model.po.RoleHisPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="roleHisPOMapper")
public interface RoleHisPOMapper
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(RoleHisPO record) throws Exception;

    int insertSelective(RoleHisPO record) throws Exception;

    RoleHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(RoleHisPO record) throws Exception;

    int updateByPrimaryKey(RoleHisPO record) throws Exception;
}