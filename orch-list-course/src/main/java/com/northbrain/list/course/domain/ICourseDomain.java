package com.northbrain.list.course.domain;

import java.util.List;

import com.northbrain.base.common.model.vo.OrchCourseVO;

/**
 * 类名：课程DOMAIN接口
 * 用途：实现课程相关业务逻辑
 * @author Jiakun
 * @version 1.0
 */
public interface ICourseDomain
{
    /**
     * 方法：读取在用的课程列表
     * @return 在用的课程列表
     */
    List<OrchCourseVO> readInUsedCourses() throws Exception;
}
