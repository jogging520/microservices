package com.northbrain.product.course.model.po;

import java.util.Date;

public class ChapterDetailPO {
    private Integer chapterDetailId;

    private Integer chapterId;

    private String attribute;

    private String value;

    private Integer status;

    private Date createTime;

    private Date statusTime;

    private String desciption;

    public Integer getChapterDetailId() {
        return chapterDetailId;
    }

    public void setChapterDetailId(Integer chapterDetailId) {
        this.chapterDetailId = chapterDetailId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
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

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption == null ? null : desciption.trim();
    }
}