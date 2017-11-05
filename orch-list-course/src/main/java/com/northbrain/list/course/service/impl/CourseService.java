package com.northbrain.list.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.CourseVO;
import com.northbrain.base.common.model.vo.OrchCourseVO;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.base.common.model.vo.StorageVO;
import com.northbrain.list.course.domain.ICourseDomain;
import com.northbrain.list.course.domain.IOperationRecordDomain;
import com.northbrain.list.course.domain.IStorageDomain;
import com.northbrain.list.course.service.ICourseService;

/**
 * 类名：课程服务接口的实现类
 * 用途：读取课程相关信息
 * @author Jiakun
 * @version 1.0
 */
@Service
public class CourseService implements ICourseService
{
    private static Logger logger = Logger.getLogger(CourseService.class);
    private final ICourseDomain courseDomain;
    private final IStorageDomain storageDomain;
    private final IOperationRecordDomain operationRecordDomain;

    @Autowired
    public CourseService(ICourseDomain courseDomain, IStorageDomain storageDomain, IOperationRecordDomain operationRecordDomain)
    {
        this.courseDomain = courseDomain;
        this.storageDomain = storageDomain;
        this.operationRecordDomain = operationRecordDomain;
    }

    /**
     * 方法：读取在用的课程列表
     * @return 在用的课程列表，用ServiceVO封装
     */
    public ServiceVO readInUsedCourses()
    {
        ServiceVO orchServiceVO = new ServiceVO();

        if(courseDomain == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDomain");
            return orchServiceVO;
        }

        if(storageDomain == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "storageDomain");
            return orchServiceVO;
        }

        ServiceVO atomServiceVO = this.courseDomain.readInUsedCourses();

        if(atomServiceVO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomServiceVO");
            return orchServiceVO;
        }

        List<CourseVO> atomCourseVOS = (List<CourseVO>)atomServiceVO.getResponse();

        if(atomCourseVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomCourseVOS");
            return orchServiceVO;
        }

        if(atomCourseVOS == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomCourseVOS");
            return orchServiceVO;
        }

        if(atomCourseVOS.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "atomCourseVOS");
            return orchServiceVO;
        }

        List<OrchCourseVO> orchCourseVOS = new ArrayList<>();
        OrchCourseVO orchCourseVO;

        for(CourseVO courseVO: atomCourseVOS)
        {
            orchCourseVO = new OrchCourseVO();
            orchCourseVO.setCourseId(courseVO.getCourseId());
            orchCourseVO.setName(courseVO.getName());
            orchCourseVO.setGrade(courseVO.getGrade());
            orchCourseVO.setLevel(courseVO.getLevel());
            orchCourseVO.setSubject(courseVO.getSubject());

            ServiceVO thumbnailVO = this.storageDomain.readStorage(courseVO.getCourseId());

            if(thumbnailVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "thumbnailVO");
                return orchServiceVO;
            }

            StorageVO atomStorageVO = (StorageVO)thumbnailVO.getResponse();

            if(atomStorageVO == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "atomStorageVO");
                return orchServiceVO;
            }

            orchCourseVO.setThumbnail(atomStorageVO.getUri());
            orchCourseVO.setStatus(courseVO.getStatus());
            orchCourseVO.setCreateTime(courseVO.getCreateTime());
            orchCourseVO.setStatusTime(courseVO.getStatusTime());
            orchCourseVO.setDesciption(courseVO.getDesciption());

            orchCourseVOS.add(orchCourseVO);
        }

        orchServiceVO.setResponse(orchCourseVOS);
        orchServiceVO.setResponseCodeAndDesc(Errors.SUCCESS_EXECUTE);

        return orchServiceVO;
    }
}
