package com.northbrain.list.course.dto.impl;

import com.northbrain.base.common.model.bo.Hints;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.northbrain.base.common.exception.ArgumentInputException;
import com.northbrain.base.common.exception.AtomicServiceResponseException;
import com.northbrain.base.common.exception.ObjectNullException;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.vo.atom.CourseVO;
import com.northbrain.base.common.model.vo.basic.ServiceVO;
import com.northbrain.base.common.model.vo.atom.StorageVO;
import com.northbrain.list.course.dto.ICourseDTO;

/**
 * 类名：课程数据转换对象接口的实现类
 * 用途：转换课程相关的数据
 * @author Jiakun
 * @version 1.0
 */
@Component
public class CourseDTO implements ICourseDTO
{
    private static Logger logger = Logger.getLogger(CourseDTO.class);


}
