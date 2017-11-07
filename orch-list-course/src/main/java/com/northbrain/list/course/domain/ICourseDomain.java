package com.northbrain.list.course.domain;

import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.list.course.domain.hystrix.HystrixCourseDomain;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类名：课程DOMAIN接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@FeignClient(name = Constants.BUSINESS_PRODUCT_COURSE_ATOM_MICROSERVICE, fallback = HystrixCourseDomain.class)
@RequestMapping(Constants.URI_ATOM_PRODUCT_DOMAIN_REQUEST_MAPPING)
public interface ICourseDomain
{
    /**
     * 方法：获取在用的课程列表
     * @return 在用的课程列表
     */
    @RequestMapping(value=Constants.URI_ATOM_PRODUCT_GET_COURSES_IN_USED, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readInUsedCourses() throws Exception;
}
