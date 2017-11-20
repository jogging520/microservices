package com.northbrain.list.course.dao;

import com.alibaba.fastjson.JSON;
import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.ServiceVO;

import org.apache.log4j.Logger;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类名：课程DAO接口
 * 用途：用于通知Feign组件对该接口进行代理
 */
@FeignClient(name = Constants.BUSINESS_PRODUCT_COURSE_ATOM_MICROSERVICE, fallback = ICourseDAO.HystrixCourseDAO.class)
public interface ICourseDAO
{
    /**
     * 方法：通过原子服务获取在用的课程列表
     * @return 在用的课程列表
     */
    @RequestMapping(value = Constants.URI_ATOM_PRODUCT_COURSE_REQUEST_MAPPING, method = RequestMethod.GET, produces = Constants.BUSINESS_COMMON_HTTP_REQUEST_PRODUCERS)
    @ResponseBody
    String readAtomInUsedCourses() throws Exception;

    /**
     * 类名：课程DAO接口的熔断器实现类
     * 用途：用于Hystrix熔断时fallback调用
     */
    @Component
    class HystrixCourseDAO implements ICourseDAO
    {
        private static Logger logger = Logger.getLogger(HystrixCourseDAO.class);

        /**
         * 方法：Hystrix熔断时的fallback调用
         * @return 在用的课程列表
         */
        @Override
        public String readAtomInUsedCourses() throws Exception
        {
            logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "readAtomInUsedCourses");

            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

            return JSON.toJSONString(serviceVO);
        }
    }
}
