package com.northbrain.party.basic.dao;

import com.northbrain.party.basic.model.po.OrganizationHisPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="organizationHisPOMapper")
public interface OrganizationHisPOMapper
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(OrganizationHisPO record) throws Exception;

    int insertSelective(OrganizationHisPO record) throws Exception;

    OrganizationHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(OrganizationHisPO record) throws Exception;

    int updateByPrimaryKey(OrganizationHisPO record) throws Exception;
}