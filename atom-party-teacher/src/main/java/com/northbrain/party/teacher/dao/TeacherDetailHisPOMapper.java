package com.northbrain.party.teacher.dao;

import com.northbrain.party.teacher.model.po.TeacherDetailHisPO;

public interface TeacherDetailHisPOMapper {
    int deleteByPrimaryKey(Integer recordId) throws Exception;

    int insert(TeacherDetailHisPO record) throws Exception;

    int insertSelective(TeacherDetailHisPO record) throws Exception;

    TeacherDetailHisPO selectByPrimaryKey(Integer recordId) throws Exception;

    int updateByPrimaryKeySelective(TeacherDetailHisPO record) throws Exception;

    int updateByPrimaryKey(TeacherDetailHisPO record) throws Exception;
}