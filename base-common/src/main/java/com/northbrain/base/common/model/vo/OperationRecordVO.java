package com.northbrain.base.common.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.northbrain.base.common.model.bo.Constants;

public class OperationRecordVO
{
    private Integer recordId;

    private String operateType;

    private Integer operatorId;

    private String domain;

    private String serviceName;

    private Integer status;

    @JSONField(format=Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date startTime;

    @JSONField(format=Constants.BUSINESS_COMMON_JSON_RESPONSE_DATE_FORMART)
    private Date finishTime;

    private String description;

    private List<OperationRecordDetailVO> operationRecordDetailVOS;

    public static class OperationRecordDetailVO
    {
        private Long recordDetailId;

        private Integer rank;

        private String operateType;

        private String domain;

        private String serviceName;

        private Integer status;

        private Date startTime;

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

    public void addOperationRecordDetail(OperationRecordDetailVO newOperationRecordDetailVO)
    {
        if(this.operationRecordDetailVOS == null)
            this.operationRecordDetailVOS = new ArrayList<>();

        boolean isContain = false;

        for(OperationRecordDetailVO operationRecordDetailVO: this.operationRecordDetailVOS)
            if(Objects.equals(operationRecordDetailVO.getRecordDetailId(), newOperationRecordDetailVO.getRecordDetailId()))
                isContain = true;

        if(!isContain)
            this.operationRecordDetailVOS.add(newOperationRecordDetailVO);
    }

    public String toString()
    {
        return JSON.toJSONString(this);
    }
}
