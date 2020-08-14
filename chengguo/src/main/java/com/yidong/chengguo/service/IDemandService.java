package com.yidong.chengguo.service;

import com.yidong.chengguo.entity.Demand;
import com.yidong.chengguo.entity.PageBean;
import com.yidong.chengguo.entity.UserSign;

import java.util.List;

/**
 * 需求的业务接口
 */
public interface IDemandService {
    /**
     * 分页查询需求
     */
    PageBean<Demand> pageQuery(String content, String currentPage, String pageSize);
    /**
     * 需求报名
     */
    void sign(String introduction, String linkName, String phoneNum, String uid, String demandId);
    /**
     * 个人需求管理
     */
    UserSign personalManage(int id);

    /**
     *    查询所有需求
     */
    List<Demand> findAll(String state);

    /**
     *     管理员通过企业需求
     */
    void passOne(Integer id,Integer enterpriseId);

    /**
     *     管理员拒绝企业需求
     */
    void refuseOne(Integer id,Integer enterpriseId);
}
