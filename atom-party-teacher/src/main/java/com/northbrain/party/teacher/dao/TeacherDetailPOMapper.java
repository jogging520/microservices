package com.northbrain.party.teacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.northbrain.party.teacher.model.po.TeacherDetailPO;

@Mapper
@Component(value="teacherDetailPOMapper")
public interface TeacherDetailPOMapper {
    int deleteByPrimaryKey(Integer teacherDetailId) throws Exception;

    int insert(TeacherDetailPO record) throws Exception;

    int insertSelective(TeacherDetailPO record) throws Exception;

    TeacherDetailPO selectByPrimaryKey(Integer teacherDetailId) throws Exception;

    int updateByPrimaryKeySelective(TeacherDetailPO record) throws Exception;

    int updateByPrimaryKey(TeacherDetailPO record) throws Exception;
}