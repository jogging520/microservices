package com.northbrain.list.course.service;

import com.northbrain.base.common.model.vo.basic.ServiceVO;

/**
 * 类名：课程服务接口
 * 用途：读取课程相关信息
 * @author Jiakun
 * @version 1.0
 */
public interface ICourseService
{
    /**
     * 方法：读取在用的课程列表
     * @return 在用的课程列表，用ServiceVO封装
     */
    ServiceVO readInUsedCourses();
}
