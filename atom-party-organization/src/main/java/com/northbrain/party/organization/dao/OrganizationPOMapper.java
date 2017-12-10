package com.northbrain.party.organization.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.organization.model.po.OrganizationPO;

@Mapper
@Component(value="organizationPOMapper")
public interface OrganizationPOMapper 
{
    int deleteByPrimaryKey(Integer organizationId) throws Exception;

    int insert(OrganizationPO record) throws Exception;

    int insertSelective(OrganizationPO record) throws Exception;

    OrganizationPO selectByPrimaryKey(Integer organizationId) throws Exception;

    int updateByPrimaryKeySelective(OrganizationPO record) throws Exception;

    int updateByPrimaryKey(OrganizationPO record) throws Exception;
}