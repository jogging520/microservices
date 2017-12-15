package com.northbrain.product.course.domain.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.northbrain.base.common.exception.CollectionEmptyException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.BaseType;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.atom.CourseVO;
import com.northbrain.product.course.dao.CoursePOMapper;
import com.northbrain.product.course.domain.ICourseDomain;
import com.northbrain.product.course.dto.ICourseDTO;
import com.northbrain.product.course.model.po.CoursePO;

/**
 * 类名：课程域接口的实现类
 * 用途：实现课程的管理，包括章、节、评论、星级等。
 * @author Jiakun
 * @version 1.0
 *
 */
@Component
public class CourseDomain implements ICourseDomain
{
	private static Logger logger = Logger.getLogger(CourseDomain.class);
	private final CoursePOMapper coursePOMapper;
	private final ICourseDTO courseDTO;

	@Autowired
	public CourseDomain(CoursePOMapper coursePOMapper, ICourseDTO courseDTO)
	{
		this.coursePOMapper = coursePOMapper;
		this.courseDTO = courseDTO;
	}

	/**
	 * 方法：获取全量在用的课程清单
	 * @return 课程清单
	 */
	@Override
	public List<CourseVO> readInUsedCourses() throws Exception
	{
		if(coursePOMapper == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "CoursePOMapper");
			throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
		}
		
		if(courseDTO == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDTO");
			throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
		}
		
		List<CoursePO> coursePOs = coursePOMapper.selectByStatus(BaseType.STATUS.INUSED.ordinal());

        if(coursePOs == null || coursePOs.size() == 0)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "coursePOs");
            throw new CollectionEmptyException(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
        }
        
        List<CourseVO> courseVOS = new ArrayList<>();
        
        CourseVO courseVO;

        for(CoursePO coursePO: coursePOs)
        {
        	courseVO = courseDTO.convertToCourseVO(coursePO);
        	
        	if(courseVO == null)
        		continue;
        	
        	courseVOS.add(courseVO);
        }

        return courseVOS;
	}
	
	/**
	 * 方法：获取特定的课程
	 * @param courseId 课程编号
	 * @return 课程
	 */
	public CourseVO readCourse(int courseId) throws Exception
	{
		if(coursePOMapper == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "CoursePOMapper");
			throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
		}
		
		if(courseDTO == null)
		{
			logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDTO");
			throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
		}
		
		CoursePO coursePO = coursePOMapper.selectByPrimaryKey(courseId);
		
		if(coursePO == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_EMPTY + "coursePO");
            throw new ObjectNullException(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }

		return courseDTO.convertToCourseVO(coursePO);
	}
}
