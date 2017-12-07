package com.northbrain.party.teacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.teacher.model.po.TeacherPO;

@Mapper
@Component(value="teacherPOMapper")
public interface TeacherPOMapper {
    int deleteByPrimaryKey(Integer teacherId);

    int insert(TeacherPO record);

    int insertSelective(TeacherPO record);

    TeacherPO selectByPrimaryKey(Integer teacherId);

    int updateByPrimaryKeySelective(TeacherPO record);

    int updateByPrimaryKey(TeacherPO record);
}