package com.northbrain.list.course.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.list.course.domain.ICourseDomain;
import com.northbrain.list.course.domain.IOperationRecordDomain;
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
    private final IOperationRecordDomain operationRecordDomain;

    @Autowired
    public CourseService(ICourseDomain courseDomain, IOperationRecordDomain operationRecordDomain)
    {
        this.courseDomain = courseDomain;
        this.operationRecordDomain = operationRecordDomain;
    }

    /**
     * 方法：读取在用的课程列表
     * @return 在用的课程列表，用ServiceVO封装
     */
    public ServiceVO readInUsedCourses()
    {
        if(courseDomain == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDomain");
            return null;
        }

        return this.courseDomain.readInUsedCourses();
    }
}
