package com.northbrain.common.security.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.common.security.model.po.RegistryHisPO;

@Mapper
@Component(value="registryHisPOMapper")
public interface RegistryHisPOMapper
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(RegistryHisPO record) throws Exception;

    int insertSelective(RegistryHisPO record) throws Exception;

    RegistryHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(RegistryHisPO record) throws Exception;

    int updateByPrimaryKey(RegistryHisPO record) throws Exception;
}