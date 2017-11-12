package com.northbrain.base.common.model.vo;

import java.util.Date;
import java.util.List;

import com.northbrain.base.common.model.bo.Constants;

public class OperationRecordVO
{
    private Integer recordId;

    private String operateType;

    private Integer operatorId;

    private String domain;

    private String serviceName;

    private Integer status;

    private Date startTime;

    private Date finishTime;

    private String description;

    private List<OperationRecordDetailVO> operationRecordDetailVOS;

    public static class OperationRecordDetailVO
    {
        private Long recordDetailId;

        private Integer recordId;

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

        public Integer getRecordId() {
            return recordId;
        }

        public void setRecordId(Integer recordId) {
            this.recordId = recordId;
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

    public String toString()
    {
        return String.valueOf
                (
                        this.getRecordId() + Constants.BUSINESS_COMMON_COMMAND_LINE_END_SYMBOL +
                        this.getOperatorId() + Constants.BUSINESS_COMMON_COMMAND_LINE_END_SYMBOL +
                        this.getDescription()
                );
    }
}
