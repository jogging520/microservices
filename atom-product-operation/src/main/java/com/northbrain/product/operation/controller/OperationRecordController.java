package com.northbrain.product.operation.controller;

import com.northbrain.base.common.model.bo.Constants;
import com.northbrain.base.common.model.bo.Errors;
import com.northbrain.base.common.model.bo.Hints;
import com.northbrain.base.common.model.vo.OperationRecordVO;
import com.northbrain.base.common.model.vo.ServiceVO;
import com.northbrain.product.operation.service.IOperationRecordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名：操作记录控制层类（原子服务）
 * 用途：解析http servlet，调用service层服务，返回给服务编排层应答数据。
 */
@RestController
public class OperationRecordController
{
    private static Logger logger = Logger.getLogger(OperationRecordController.class);

    private final IOperationRecordService operationRecordService;

    @Autowired
    public OperationRecordController(IOperationRecordService operationRecordService)
    {
        this.operationRecordService = operationRecordService;
    }

    /**
     * 方法：增加一条操作记录
     * @return 以ServiceVO封装的课程列表
     */
    @PostMapping(value= Constants.URI_ATOM_PRODUCT_POST_OPERATION_RECORD)
    @ResponseBody
    public ServiceVO createOperationRecord(@RequestBody OperationRecordVO operationRecordVO)
    {
        logger.info(Hints.HINT_SYSTEM_PROCESS_CALL_CONTROLLER + "createOperationRecord");

        if(operationRecordService == null)
        {
            logger.error(Errors.ERROR_BUSINESS_COMMON_OBJECT_NULL + "operationRecordService");

            return new ServiceVO();
        }

        return operationRecordService.createOperationRecord(operationRecordVO);
    }
}
