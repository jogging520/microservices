package com.northbrain.party.role.dao;

import com.northbrain.party.role.model.po.RolePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value="rolePOMapper")
public interface RolePOMapper
{
    int deleteByPrimaryKey(Integer roleId) throws Exception;

    int insert(RolePO record) throws Exception;

    int insertSelective(RolePO record) throws Exception;

    RolePO selectByPrimaryKey(Integer roleId) throws Exception;

    List<RolePO> selectByName(@Param("name")String name) throws Exception;

    int updateByPrimaryKeySelective(RolePO record) throws Exception;

    int updateByPrimaryKey(RolePO record) throws Exception;
}