package com.northbrain.product.course.service;

import com.northbrain.base.common.model.vo.ServiceVO;

/**
 * 类名：课程服务接口
 * 用途：封装课程、章、节、评论、星级等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
public interface ICourseService
{
    /**
     * 方法：获取在用的课程列表（只有课程本身）
     * @return ServiceVO封装类
     */
    ServiceVO readInUsedCourses();

    /**
     * 方法：获取特定的课程（只有课程本身）
     * @param courseId 课程编号
     * @return ServiceVO封装类
     */
    ServiceVO readCourse(int courseId);
}
