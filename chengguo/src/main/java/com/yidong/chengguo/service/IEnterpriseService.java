package com.yidong.chengguo.service;

import com.yidong.chengguo.entity.Enterprise;


/**
 * 企业业务接口
 */
public interface IEnterpriseService {

    /**
     * 保存企业认证信息
     */
    void save(Integer uid ,String introduction, String linkName, String phoneNum);

    /**
     * 查询企业
     */
    Enterprise findOne(Integer userId);

    /**
     * 发布需求
     */
    void release(String enterpriseId, String title, String briefInfo, String specificInfo, String unit, String budget, String deadline);

    /**
     * 拒绝用户报名
     */
    void refuseOne(Integer id);

    /**
     * 通过用户报名
     */
    void passOne(Integer id);
}
