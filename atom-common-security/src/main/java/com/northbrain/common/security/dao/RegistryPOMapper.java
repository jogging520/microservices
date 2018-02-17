package com.northbrain.common.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.northbrain.common.security.model.po.RegistryPO;

@Mapper
@Component(value="registryPOMapper")
public interface RegistryPOMapper
{
    int deleteByPrimaryKey(Integer registryId) throws Exception;

    int insert(RegistryPO record) throws Exception;

    int insertSelective(RegistryPO record) throws Exception;

    RegistryPO selectByPrimaryKey(Integer registryId) throws Exception;

    List<RegistryPO> selectByPartyId(@Param("partyId") Integer partyId) throws Exception;

    int updateByPrimaryKeySelective(RegistryPO record) throws Exception;

    int updateByPrimaryKey(RegistryPO record) throws Exception;
}