package com.northbrain.list.course.dao.hystrix;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.OperationRecordVO;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.list.course.dao.IOperationRecordDAO;

/**
 * 类名：操作记录DAO接口的熔断器实现类
 * 用途：用于Hystrix熔断时fallback调用
 */
public class HystrixOperationRecord implements IOperationRecordDAO
{
    private static Logger logger = Logger.getLogger(HystrixOperationRecord.class);

    /**
     * 方法：通过原子服务新增一条产品域操作记录
     *
     * @param operationRecordVO 操作记录值对象
     * @return 是否增加成功
     */
    @Override
    public String createAtomOperationRecord(OperationRecordVO operationRecordVO) throws Exception
    {
        logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_HYSTRIX_DAO + "createAtomOperationRecord");

        ServiceVO serviceVO = new ServiceVO();
        serviceVO.setResponseCodeAndDesc(Errors.ERROR_SYSTEM_SERVICE_HYSTRIX_EXCEPTION);

        return JSON.toJSONString(serviceVO);
    }
}
