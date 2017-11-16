package com.northbrain.list.course.dao;

import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.list.course.dao.hystrix.HystrixCourseDAO;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类名：课程DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@FeignClient(name = Constants.BUSINESS_PRODUCT_COURSE_ATOM_MICROSERVICE, fallback = HystrixCourseDAO.class)
@RequestMapping(Constants.URI_ATOM_PRODUCT_COURSE_REQUEST_MAPPING)
public interface ICourseDAO
{
    /**
     * 方法：通过原子服务获取在用的课程列表
     * @return 在用的课程列表
     */
    @RequestMapping(method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readAtomInUsedCourses() throws Exception;
}
