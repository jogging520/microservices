package com.northbrain.party.organization.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.organization.model.po.SubjectionHisPO;

@Mapper
@Component(value="subjectionHisPOMapper")
public interface SubjectionHisPOMapper 
{
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(SubjectionHisPO record) throws Exception;

    int insertSelective(SubjectionHisPO record) throws Exception;

    SubjectionHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(SubjectionHisPO record) throws Exception;

    int updateByPrimaryKey(SubjectionHisPO record) throws Exception;
}