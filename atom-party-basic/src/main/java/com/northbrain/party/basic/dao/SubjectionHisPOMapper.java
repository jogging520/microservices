package com.northbrain.party.basic.dao;

import com.northbrain.party.basic.model.po.SubjectionHisPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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