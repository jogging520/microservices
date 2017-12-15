package com.northbrain.product.course.domain;

import java.util.List;

import com.northbrain.base.common.model.vo.atom.CourseVO;

/**
 * 类名：课程域接口
 * 用途：实现课程的管理，包括章、节、评论、星级等。
 * @author Jiakun
 * @version 1.0
 *
 */
public interface ICourseDomain 
{
	/**
	 * 方法：获取全量在用的课程清单
	 * @return 课程清单
	 */
	List<CourseVO> readInUsedCourses() throws Exception;
	
	/**
	 * 方法：获取特定的课程
	 * @param courseId 课程编号
	 * @return 课程
	 */
	CourseVO readCourse(int courseId) throws Exception;
}
