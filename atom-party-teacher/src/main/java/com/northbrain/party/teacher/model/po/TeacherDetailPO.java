package com.northbrain.party.teacher.model.po;

import java.util.Date;

public class TeacherDetailPO {
    private Integer teacherDetailId;

    private Integer teacherId;

    private String attribute;

    private String value;

    private Integer status;

    private Date createTime;

    private Date statusTime;

    private String description;

    public Integer getTeacherDetailId() {
        return teacherDetailId;
    }

    public void setTeacherDetailId(Integer teacherDetailId) {
        this.teacherDetailId = teacherDetailId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute == null ? null : attribute.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}