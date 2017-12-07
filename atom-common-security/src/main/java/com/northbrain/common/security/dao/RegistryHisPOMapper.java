package com.northbrain.common.security.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.common.security.model.po.RegistryHisPO;

@Mapper
@Component(value="registryHisPOMapper")
public interface RegistryHisPOMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(RegistryHisPO record);

    int insertSelective(RegistryHisPO record);

    RegistryHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(RegistryHisPO record);

    int updateByPrimaryKey(RegistryHisPO record);
}