package com.yidong.chengguo.entity;


import java.util.List;

/**
 * 用户与需求的中间表
 */
public class UserSign {
    // id 自增长 主键
    private Integer id;
    // 用户id
    private Integer userId;
    // 需求id
    private Integer demandId;
    // 用户需求集合
    private List<Demand> demands;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public List<Demand> getDemands() {
        return demands;
    }

    public void setDemands(List<Demand> demands) {
        this.demands = demands;
    }

    @Override
    public String toString() {
        return "UserSign{" +
                "id=" + id +
                ", userId=" + userId +
                ", demandId=" + demandId +
                ", demands=" + demands +
                '}';
    }
}

