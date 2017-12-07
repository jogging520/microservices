package com.northbrain.party.teacher.dao;

import com.northbrain.party.teacher.model.po.TeacherPO;

public interface TeacherPOMapper {
    int deleteByPrimaryKey(Integer teacherId) throws Exception;

    int insert(TeacherPO record) throws Exception;

    int insertSelective(TeacherPO record) throws Exception;

    TeacherPO selectByPrimaryKey(Integer teacherId) throws Exception;

    int updateByPrimaryKeySelective(TeacherPO record) throws Exception;

    int updateByPrimaryKey(TeacherPO record) throws Exception;
}