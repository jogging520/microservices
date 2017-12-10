package com.northbrain.party.organization.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.organization.model.po.OrganizationHisPO;

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