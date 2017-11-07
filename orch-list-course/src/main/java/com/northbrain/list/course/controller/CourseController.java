package com.northbrain.list.course.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.base.common.util.StackTracerUtil;
import com.northbrain.list.course.service.ICourseService;

import feign.FeignException;

/**
 * 类名：课程控制层类（封装服务）
 * 用途：解析http servlet，调用service层服务，返回交互表现层应答数据。
 */
@RestController
@RequestMapping(Constants.URI_ORCH_LIST_DOMAIN_REQUEST_MAPPING)
public class CourseController
{
    private static Logger logger = Logger.getLogger(CourseController.class);

    private final ICourseService courseService;

    @Autowired
    public CourseController(ICourseService courseService)
    {
        this.courseService = courseService;
    }

    /**
     * 方法：读取在用的课程列表
     * @return 以ServiceVO封装的课程列表的JSON对象
     */
    @RequestMapping(value= Constants.URI_ORCH_LIST_GET_COURSES_IN_USED, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    public String readInUsedCourses()
    {
        logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "readInUsedCourses");
        ServiceVO serviceVO = new ServiceVO();

        try
        {
            if(courseService == null)
            {
                logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseService");
                return JSON.toJSONString(serviceVO);
            }

            return JSON.toJSONString(this.courseService.readInUsedCourses());
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
        catch(Exception exception)
        {
            logger.error(StackTracerUtil.getExceptionInfo(exception));
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_OTHER_UNKNOW_EXCEPTION);
        }

        return JSON.toJSONString(serviceVO);
    }
}
