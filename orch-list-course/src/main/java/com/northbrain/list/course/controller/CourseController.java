package com.northbrain.list.course.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.list.course.service.ICourseService;

/**
 * 类名：课程控制层类（封装服务）
 * 用途：解析http servlet，调用service层服务，返回交互表现层应答数据。
 */
@RestController
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
    @GetMapping(value= Constants.URI_ORCH_LIST_GET_COURSES_IN_USED, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    public JSONObject readInUsedCourses()
    {
        logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "readInUsedCourses");

        if(courseService == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "courseService");
            return null;
        }

        ServiceVO serviceVO = this.courseService.readInUsedCourses();

        return (JSONObject) JSON.toJSON(serviceVO);
    }
}
