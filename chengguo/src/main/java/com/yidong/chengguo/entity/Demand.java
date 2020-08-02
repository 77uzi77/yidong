package com.yidong.chengguo.entity;


/**
 * 需求的实体类，对于数据库表
 */
public class Demand {
    // id 主键 自增长
    private Integer id;
    // 企业id
    private Integer enterpriseId;
    // 标题
    private String title;
    // 简介
    private String briefInfo;
    // 详情
    private String specificInfo;
    // 单元
    private String unit;
    // 预算
    private String budget;
    // 时间要求
    private String deadline;
    // 状态 默认0 审核通过1 不通过 -1
    private String state;
    // 报名的用户名
    private String userName;
    // 用户信息
    private String userMessage;
    // 用户电话
    private String userPhone;
    // 报名结果
    private String result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public String getSpecificInfo() {
        return specificInfo;
    }

    public void setSpecificInfo(String specificInfo) {
        this.specificInfo = specificInfo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", title='" + title + '\'' +
                ", briefInfo='" + briefInfo + '\'' +
                ", specificInfo='" + specificInfo + '\'' +
                ", unit='" + unit + '\'' +
                ", budget='" + budget + '\'' +
                ", deadline='" + deadline + '\'' +
                ", state='" + state + '\'' +
                ", userName='" + userName + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
