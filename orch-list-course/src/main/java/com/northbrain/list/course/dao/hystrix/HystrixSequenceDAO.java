package com.northbrain.list.course.dao.hystrix;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.list.course.dao.ISequenceDAO;

/**
 * 类名：序列号DAO接口的熔断器实现类
 * 用途：用于Hystrix熔断时fallback调用
 */
public class HystrixSequenceDAO implements ISequenceDAO
{
    private static Logger logger = Logger.getLogger(HystrixSequenceDAO.class);

    /**
     * 方法：Hystrix熔断时的fallback调用
     * @return 在用的课程列表
     */
    @Override
    public String readAtomNextGlobalValue() throws Exception
    {
        logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "readNextGlobalValue");

        ServiceVO serviceVO = new ServiceVO();
        serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

        return JSON.toJSONString(serviceVO);
    }
}
