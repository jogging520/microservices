package com.northbrain.list.course.domain.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.CourseVO;
import com.northbrain.base.common.model.vo.OrchCourseVO;
import com.northbrain.base.common.model.vo.StorageVO;
import com.northbrain.list.course.dao.ICourseDAO;
import com.northbrain.list.course.dao.IOperationRecordDAO;
import com.northbrain.list.course.dao.ISequenceDAO;
import com.northbrain.list.course.dao.IStorageDAO;
import com.northbrain.list.course.domain.ICourseDomain;
import com.northbrain.list.course.dto.ICourseDTO;

/**
 * 类名：课程DOMAIN接口的实现类
 * 用途：实现课程相关业务逻辑
 * @author Jiakun
 * @version 1.0
 */
@Component
public class CourseDomain implements ICourseDomain
{
    private static Logger logger = Logger.getLogger(CourseDomain.class);
    private final ICourseDAO courseDAO;
    private final IStorageDAO storageDAO;
    private final IOperationRecordDAO operationRecordDAO;
    private final ICourseDTO courseDTO;
    private final ISequenceDAO sequenceDAO;

    @Autowired
    public CourseDomain(ICourseDAO courseDAO, IStorageDAO storageDAO, IOperationRecordDAO operationRecordDAO, ICourseDTO courseDTO, ISequenceDAO sequenceDAO)
    {
        this.courseDAO = courseDAO;
        this.storageDAO = storageDAO;
        this.operationRecordDAO = operationRecordDAO;
        this.courseDTO = courseDTO;
        this.sequenceDAO = sequenceDAO;
    }

    /**
     * 方法：读取在用的课程列表
     *
     * @return 在用的课程列表
     */
    @Override
    public List<OrchCourseVO> readInUsedCourses() throws Exception
    {
        List<OrchCourseVO> orchCourseVOS;

        if (courseDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDAO");
            return null;
        }

        if (storageDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDAO");
            return null;
        }

        if (sequenceDAO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "sequenceDAO");
            return null;
        }

        if (courseDTO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDTO");
            return null;
        }

        Integer operationRecordId = courseDTO.convertToInteger(sequenceDAO.readNextGlobalValue());

        logger.info("---------next value:" + operationRecordId);

        JSONArray atomCourseVOS = courseDTO.convertToCourseVOArray(this.courseDAO.readAtomInUsedCourses());

        if (atomCourseVOS == null || atomCourseVOS.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "atomCourseVOS");
            return null;
        }

        orchCourseVOS = new ArrayList<>();

        for (Object object : atomCourseVOS)
        {
            OrchCourseVO orchCourseVO = new OrchCourseVO();
            CourseVO courseVO = courseDTO.convertToCourseVO(object);

            orchCourseVO.setCourseId(courseVO.getCourseId());
            orchCourseVO.setName(courseVO.getName());
            orchCourseVO.setGrade(courseVO.getGrade());
            orchCourseVO.setLevel(courseVO.getLevel());
            orchCourseVO.setSubject(courseVO.getSubject());

            StorageVO atomStorageVO = courseDTO.convertToStorageVO(this.storageDAO.readAtomStorage(courseVO.getThumbnail()));

            if (atomStorageVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomStorageVO");
                continue;
            }

            orchCourseVO.setThumbnail(atomStorageVO.getUri());
            orchCourseVO.setStatus(courseVO.getStatus());
            orchCourseVO.setCreateTime(courseVO.getCreateTime());
            orchCourseVO.setStatusTime(courseVO.getStatusTime());
            orchCourseVO.setDesciption(courseVO.getDesciption());

            orchCourseVOS.add(orchCourseVO);
        }

        return orchCourseVOS;
    }
}
