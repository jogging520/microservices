package com.northbrain.product.course.dto.impl;

import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Hints;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.atom.CourseVO;
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
	private static Logger logger = Logger.getLogger(CourseDTO.class);
	/**
	 * 方法：将持久化对象CoursePO转换成值对象CourseVO
	 * @param coursePO 持久化对象
	 * @return CourseVO 值对象
	 */
	@Override
	public CourseVO convertToCourseVO(CoursePO coursePO) throws Exception
	{
		if(coursePO == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "coursePO");
			throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
		}

		logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

		CourseVO courseVO = new CourseVO();

		courseVO.setRecordId(Constants.BUSINESS_COMMON_OPERATION_RECORD_DEFAULT_ID);
    	courseVO.setCourseId(coursePO.getCourseId());
    	courseVO.setName(coursePO.getName());
		courseVO.setDepartment(coursePO.getDepartment());
    	courseVO.setGrade(coursePO.getGrade());
    	courseVO.setSubject(coursePO.getSubject());
    	courseVO.setThumbnail(coursePO.getThumbnail());
    	courseVO.setStatus(coursePO.getStatus());
    	courseVO.setCreateTime(coursePO.getCreateTime());
    	courseVO.setStatusTime(coursePO.getStatusTime());
    	courseVO.setDescription(coursePO.getDescription());
    	
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
		if(courseVO == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_NULL + "courseVO");
			throw new ArgumentInputException(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
		}

		logger.debug(Hints.HINT_BUSINESS_COMMON_PO_VO_CONVERTION);

		CoursePO coursePO = new CoursePO();
    	
		coursePO.setCourseId(courseVO.getCourseId());
		coursePO.setName(courseVO.getName());
		coursePO.setDepartment(courseVO.getDepartment());
		coursePO.setGrade(courseVO.getGrade());
		coursePO.setSubject(courseVO.getSubject());
		coursePO.setThumbnail(courseVO.getThumbnail());
		coursePO.setStatus(courseVO.getStatus());
		coursePO.setCreateTime(courseVO.getCreateTime());
		coursePO.setStatusTime(courseVO.getStatusTime());
		coursePO.setDescription(courseVO.getDescription());
    	
    	return coursePO;
	}
}
