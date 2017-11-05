package com.northbrain.product.course.dto;

import com.northbrain.base.common.model.vo.CourseVO;
import com.northbrain.product.course.model.po.CoursePO;

/**
 * 类名：课程数据传输对象接口
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
public interface ICourseDTO 
{
	/**
	 * 方法：将持久化对象CoursePO转换成值对象CourseVO
	 * @param coursePO 持久化对象
	 * @return CourseVO值对象
	 */
	CourseVO convertToCourseVO(CoursePO coursePO) throws Exception;
	
	/**
	 * 方法：将值对象CourseVO转换成持久化对象CoursePO
	 * @param courseVO VO值对象
	 * @return coursePO持久化对象
	 */
	CoursePO convertToCoursePO(CourseVO courseVO) throws Exception;
}
