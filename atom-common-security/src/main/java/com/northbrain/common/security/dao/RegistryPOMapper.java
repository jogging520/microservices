package com.northbrain.common.security.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.common.security.model.po.RegistryPO;

@Mapper
@Component(value="registryPOMapper")
public interface RegistryPOMapper {
    int deleteByPrimaryKey(Integer registryId);

    int insert(RegistryPO record);

    int insertSelective(RegistryPO record);

    RegistryPO selectByPrimaryKey(Integer registryId);

    int updateByPrimaryKeySelective(RegistryPO record);

    int updateByPrimaryKey(RegistryPO record);
}