package com.northbrain.base.common.model.vo.atom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.northbrain.base.common.model.bo.Constants;

/**
 * 类名：操作记录值对象类
 * 用途：用于持久层以上的操作记录对象传递
 * @author Jiakun
 * @version 1.0
 */
public class OperationRecordVO
{
    //流水记录编号
    private Integer recordId;

    //操作类型
    private String operateType;

    //操作员编号
    private Integer operatorId;

    //归属域名
    private String domain;

    //服务名称
    private String serviceName;

    //状态
    private Integer status;

    //创建时间
    @JSONField(format=Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date startTime;

    //状态时间
    @JSONField(format=Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date finishTime;

    //描述
    private String description;

    //操作记录明细列表
    private List<OperationRecordDetailVO> operationRecordDetailVOS;

    public OperationRecordVO()
    {

    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public List<OperationRecordDetailVO> getOperationRecordDetailVOS() {
        return operationRecordDetailVOS;
    }

    public void setOperationRecordDetailVOS(List<OperationRecordDetailVO> operationRecordDetailVOS) {
        this.operationRecordDetailVOS = operationRecordDetailVOS;
    }

    public boolean addOperationRecordDetail(OperationRecordDetailVO newOperationRecordDetailVO)
    {
        if(newOperationRecordDetailVO == null)
            return false;

        if(this.operationRecordDetailVOS == null)
            this.operationRecordDetailVOS = new ArrayList<>();

        boolean isContain = false;

        for(OperationRecordDetailVO operationRecordDetailVO: this.operationRecordDetailVOS)
            if(Objects.equals(operationRecordDetailVO.getRecordDetailId(), newOperationRecordDetailVO.getRecordDetailId()))
                isContain = true;

        if(!isContain)
            this.operationRecordDetailVOS.add(newOperationRecordDetailVO);

        return true;
    }

    public String toString()
    {
        return JSON.toJSONString(this);
    }

    public static class OperationRecordDetailVO
    {
        private Long recordDetailId;

        private Integer rank;

        private String operateType;

        private String domain;

        private String serviceName;

        private Integer status;

        @JSONField(format=Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
        private Date startTime;

        @JSONField(format=Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
        private Date finishTime;

        public Long getRecordDetailId() {
            return recordDetailId;
        }

        public void setRecordDetailId(Long recordDetailId) {
            this.recordDetailId = recordDetailId;
        }

        public Integer getRank() {
            return rank;
        }

        public void setRank(Integer rank) {
            this.rank = rank;
        }

        public String getOperateType() {
            return operateType;
        }

        public void setOperateType(String operateType) {
            this.operateType = operateType == null ? null : operateType.trim();
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain == null ? null : domain.trim();
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName == null ? null : serviceName.trim();
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public Date getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(Date finishTime) {
            this.finishTime = finishTime;
        }
    }
}
