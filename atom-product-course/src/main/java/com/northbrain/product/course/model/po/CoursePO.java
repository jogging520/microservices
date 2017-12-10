package com.northbrain.product.course.model.po;

import java.util.Date;

/**
 * 类名：课程持久化对象类
 * 用途：与数据库中课程表对应的持久化对象
 * @author Jiakun
 * @version 1.0
 */
public class CoursePO
{
    //课程编号
    private Integer courseId;

    //课程名称
    private String name;

    //学部
    private String department;

    //年级
    private Integer grade;

    //学科名称
    private String subject;

    //缩略图ID
    private Integer thumbnail;

    //状态
    private Integer status;

    //创建时间
    private Date createTime;

    //状态时间
    private Date statusTime;

    //描述
    private String description;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Integer getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Integer thumbnail) {
        this.thumbnail = thumbnail;
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