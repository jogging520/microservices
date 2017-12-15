package com.northbrain.list.course.service.impl;

import java.util.Date;

import com.netflix.client.ClientException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.basic.ServiceVO;
import com.northbrain.base.common.util.StackTracerUtil;
import com.northbrain.list.course.domain.ICourseDomain;
import com.northbrain.list.course.service.ICourseService;

import feign.FeignException;

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

    @Autowired
    public CourseService(ICourseDomain courseDomain)
    {
        this.courseDomain = courseDomain;
    }

    /**
     * 方法：读取在用的课程列表
     * @return 在用的课程列表，用ServiceVO封装
     */
    public ServiceVO readInUsedCourses()
    {
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if (courseDomain == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseDomain");
                serviceVO.setResponseCodeAndDesc(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL_EXCEPTION);

                return serviceVO;
            }

            serviceVO.setResponse(courseDomain.readInUsedCourses());
            serviceVO.setResponseCodeAndDesc(Errors.SUCCESS_EXECUTE);
        }
        catch (ClassCastException classCastException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(classCastException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_CLASS_CAST_EXCEPTION);
        }
        catch(IllegalStateException illegalStateException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(illegalStateException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_ILLEGAL_STATE_EXCEPTION);
        }
        catch (JSONException jSONException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(jSONException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_JSON_EXCEPTION);
        }
        catch (FeignException feignException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(feignException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_FEIGN_EXCEPTION);
        }
        catch (ClientException clientException)
        {
            logger.error(StackTracerUtil.getExceptionInfo(clientException));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_CLIENT_EXCEPTION);
        }
        catch (Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }
        catch (Throwable throwable)
        {
            logger.error(StackTracerUtil.getExceptionInfo(throwable));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }

        serviceVO.setResponseTime(new Date());
        return serviceVO;
    }
}
