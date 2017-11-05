package com.northbrain.product.course.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.CollectionEmptyException;
import com.northbrain.base.common.exception.NumberScopeException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.exception.ParameterConfigException;
import com.northbrain.base.common.exception.ParametersStateException;
import com.northbrain.base.common.exception.RedisSessionException;
import com.northbrain.base.common.exception.ThreadPoolExecutorException;
import com.northbrain.base.common.exception.ZooKeeperSessionException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.CourseVO;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.base.common.util.StackTracerUtil;
import com.northbrain.product.course.domain.ICourseDomain;
import com.northbrain.product.course.service.ICourseService;

/**
 * 类名：课程服务接口的实现类
 * 用途：封装课程、章、节、评论、星级等Domain，管理事务，并封装ServiceVO
 * @author Jiakun
 *
 */
@Service
public class CourseService implements ICourseService
{
	private static Logger logger = Logger.getLogger(CourseService.class);
    private final ICourseDomain courseDomain;

    @Autowired
    public CourseService(ICourseDomain courseDomain)
    {
        this.courseDomain = courseDomain;
    }

    /**
	 * 方法：获取在用的课程列表（只有课程本身）
	 * @return ServiceVO封装类
	 */
	@Override
	public ServiceVO readInUsedCourses() 
	{
		ServiceVO serviceVO = new ServiceVO();
		List<CourseVO> courseVOS;
		
		try
        {
			if(courseDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                
                return serviceVO;
            }

            courseVOS = courseDomain.readInUsedCourses();
            serviceVO.setResponse(courseVOS);
            serviceVO.setResponseCodeAndDesc(Errors.SUCCESS_EXECUTE);
        }
		catch(NumberFormatException numberFormatException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(numberFormatException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_NUMBER_FORMAT_EXCEPTION);
        }
		catch(InterruptedException interruptedException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(interruptedException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_INTERRUPTED_EXCEPTION);
        }
		catch(FileNotFoundException fileNotFoundException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(fileNotFoundException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FILE_NOT_FOUND_EXCEPTION);
        }
		catch(IOException iOException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(iOException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_IO_EXCEPTION);
        }
		catch(IllegalAccessException illegalAccessException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(illegalAccessException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ACCESS_EXCEPTION);
        }
		catch(IllegalArgumentException illegalArgumentException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(illegalArgumentException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ARGUMENT_EXCEPTION);
        }
		catch(ArgumentInputException argumentInputException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(argumentInputException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
		catch(CollectionEmptyException collectionEmptyException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(collectionEmptyException));
            serviceVO.setResponseCodeAndDesc(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
        }
		catch(NumberScopeException numberScopeException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(numberScopeException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }
		catch(ObjectNullException objectNullException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(objectNullException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }
		catch(ParameterConfigException parameterConfigException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(parameterConfigException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
		catch(ParametersStateException parametersStateException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(parametersStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_STATUS_EXCEPTION);
        }
		catch(RedisSessionException redisSessionException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(redisSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }
		catch(ThreadPoolExecutorException threadPoolExecutorException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(threadPoolExecutorException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_THREAD_POOL_EXECUTOR_EXCEPTION);
        }
		catch(ZooKeeperSessionException zooKeeperSessionException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(zooKeeperSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }
        catch(Exception exception)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(exception));
        	serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }
		catch(Throwable throwable)
		{
			logger.error(StackTracerUtil.getExceptionInfo(throwable));
			serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
		}

		serviceVO.setResponseTime(new Date());
		
        return serviceVO;
	}

	/**
	 * 方法：获取特定的课程（只有课程本身）
	 * @param courseId 课程编号
	 * @return ServiceVO封装类
	 */
	@Override
	public ServiceVO readCourse(int courseId)
	{
		ServiceVO serviceVO = new ServiceVO();
		CourseVO courseVO;
		
		try
        {
            if(courseDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
                
                return serviceVO;
            }

            courseVO = courseDomain.readCourse(courseId);
            serviceVO.setResponse(courseVO);
            serviceVO.setResponseCodeAndDesc(Errors.SUCCESS_EXECUTE);
        }
		catch(NumberFormatException numberFormatException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(numberFormatException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_NUMBER_FORMAT_EXCEPTION);
        }
		catch(InterruptedException interruptedException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(interruptedException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_INTERRUPTED_EXCEPTION);
        }
		catch(FileNotFoundException fileNotFoundException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(fileNotFoundException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FILE_NOT_FOUND_EXCEPTION);
        }
		catch(IOException iOException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(iOException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_IO_EXCEPTION);
        }
		catch(IllegalAccessException illegalAccessException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(illegalAccessException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ACCESS_EXCEPTION);
        }
		catch(IllegalArgumentException illegalArgumentException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(illegalArgumentException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_ARGUMENT_EXCEPTION);
        }
		catch(ArgumentInputException argumentInputException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(argumentInputException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_ARGUMENT_INPUT_EXCEPTION);
        }
		catch(CollectionEmptyException collectionEmptyException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(collectionEmptyException));
            serviceVO.setResponseCodeAndDesc(Errors.EROOR_BUSINESS_COMMON_COLLECTION_EMPTY_EXCEPTION);
        }
		catch(NumberScopeException numberScopeException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(numberScopeException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_NUMBER_SCOPE_EXCEPTION);
        }
		catch(ObjectNullException objectNullException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(objectNullException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);
        }
		catch(ParameterConfigException parameterConfigException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(parameterConfigException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_CONFIG_EXCEPTION);
        }
		catch(ParametersStateException parametersStateException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(parametersStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_PARAMETER_STATUS_EXCEPTION);
        }
		catch(RedisSessionException redisSessionException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(redisSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_REDIS_SESSION_EXCEPTION);
        }
		catch(ThreadPoolExecutorException threadPoolExecutorException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(threadPoolExecutorException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_THREAD_POOL_EXECUTOR_EXCEPTION);
        }
		catch(ZooKeeperSessionException zooKeeperSessionException)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(zooKeeperSessionException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_STORAGE_ZOOKEEPER_SESSION_EXCEPTION);
        }
        catch(Exception exception)
        {
        	logger.error(StackTracerUtil.getExceptionInfo(exception));
        	serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }
		catch(Throwable throwable)
		{
			logger.error(StackTracerUtil.getExceptionInfo(throwable));
			serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
		}

		serviceVO.setResponseTime(new Date());
		
        return serviceVO;
	}
}
