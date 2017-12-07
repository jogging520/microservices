package com.northbrain.party.teacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.teacher.model.po.TeacherHisPO;

@Mapper
@Component(value="teacherHisPOMapper")
public interface TeacherHisPOMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(TeacherHisPO record);

    int insertSelective(TeacherHisPO record);

    TeacherHisPO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(TeacherHisPO record);

    int updateByPrimaryKey(TeacherHisPO record);
}