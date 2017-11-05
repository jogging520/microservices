package com.northbrain.product.course.dto.impl;

import org.springframework.stereotype.Component;

import com.northbrain.base.common.model.vo.CourseVO;
import com.northbrain.product.course.dto.ICourseDTO;
import com.northbrain.product.course.model.po.CoursePO;

/**
 * 类名：课程数据传输对象接口的实现类
 * 用途：用于VO和PO之间的转换
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class CourseDTO implements ICourseDTO
{
	/**
	 * 方法：将持久化对象CoursePO转换成值对象CourseVO
	 * @param coursePO 持久化对象
	 * @return CourseVO 值对象
	 */
	@Override
	public CourseVO convertToCourseVO(CoursePO coursePO) throws Exception
	{
		CourseVO courseVO = new CourseVO();
    	
    	courseVO.setCourseId(coursePO.getCourseId());
    	courseVO.setName(coursePO.getName());
    	courseVO.setGrade(coursePO.getGrade());
    	courseVO.setLevel(coursePO.getLevel());
    	courseVO.setSubject(coursePO.getSubject());
    	courseVO.setThumbnail(coursePO.getThumbnail());
    	courseVO.setStatus(coursePO.getStatus());
    	courseVO.setCreateTime(coursePO.getCreateTime());
    	courseVO.setStatusTime(coursePO.getStatusTime());
    	courseVO.setDesciption(coursePO.getDesciption());
    	
    	return courseVO;
	}

	/**
	 * 方法：将值对象CourseVO转换成持久化对象CoursePO
	 * @param courseVO 值对象
	 * @return coursePO 持久化对象
	 */
	@Override
	public CoursePO convertToCoursePO(CourseVO courseVO) throws Exception
	{
		CoursePO coursePO = new CoursePO();
    	
		coursePO.setCourseId(courseVO.getCourseId());
		coursePO.setName(courseVO.getName());
		coursePO.setGrade(courseVO.getGrade());
		coursePO.setLevel(courseVO.getLevel());
		coursePO.setSubject(courseVO.getSubject());
		coursePO.setThumbnail(courseVO.getThumbnail());
		coursePO.setStatus(courseVO.getStatus());
		coursePO.setCreateTime(courseVO.getCreateTime());
		coursePO.setStatusTime(courseVO.getStatusTime());
		coursePO.setDesciption(courseVO.getDesciption());
    	
    	return coursePO;
	}
}
