package com.yidong.chengguo.entity;

import java.util.List;

/**
 * 企业实体类
 */
public class Enterprise {
    // id 自增长 主键
    private Integer id;
    // 用户id
    private Integer uid;
    // 认证信息
    private String message;
    // 联系人
    private String linkName;
    // 电话号码
    private String phoneNum;
    // 认证状态 默认1 认证成功2
    private String state;
    // 企业发布的需求的集合
    private List<Demand> demands;

    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Demand> getDemands() {
        return demands;
    }

    public void setDemands(List<Demand> demands) {
        this.demands = demands;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "id=" + id +
                ", uid=" + uid +
                ", message='" + message + '\'' +
                ", linkName='" + linkName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", state='" + state + '\'' +
                ", demands=" + demands +
                ", user=" + user +
                '}';
    }
}
