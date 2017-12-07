package com.northbrain.party.teacher.dao;

import com.northbrain.party.teacher.model.po.TeacherHisPO;

public interface TeacherHisPOMapper {
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(TeacherHisPO record) throws Exception;

    int insertSelective(TeacherHisPO record) throws Exception;

    TeacherHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(TeacherHisPO record) throws Exception;

    int updateByPrimaryKey(TeacherHisPO record) throws Exception;
}