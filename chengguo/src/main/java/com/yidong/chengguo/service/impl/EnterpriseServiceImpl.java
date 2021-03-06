package com.yidong.chengguo.service.impl;

import com.yidong.chengguo.dao.IDemandDao;
import com.yidong.chengguo.dao.IEnterpriseDao;
import com.yidong.chengguo.entity.Demand;
import com.yidong.chengguo.entity.Enterprise;
import com.yidong.chengguo.service.IEnterpriseService;
import com.yidong.chengguo.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 企业业务实现类
 */
@Service
@Transactional
public class EnterpriseServiceImpl implements IEnterpriseService {

    @Autowired
    IEnterpriseDao enterpriseDao;

    @Autowired
    IDemandDao demandDao;
    /**
     * 保存企业认证信息
     */
    @Override
    public void save(Integer uid ,String introduction, String linkName, String phoneNum) {
        enterpriseDao.save(uid,introduction,linkName,phoneNum);
    }
    /**
     * 查询企业
     */
    @Override
    public Enterprise findOne(Integer userId) {
        return  enterpriseDao.findOne(userId);
    }
    /**
     * 发布需求
     */
    @Override
//    public void release(String enterpriseId, String title, String briefInfo, String specificInfo, String unit, String budget, String deadline) {
    public void release(Demand demand) {
//        Demand demand = new Demand();
//        demand.setEnterpriseId(Integer.parseInt(enterpriseId));
        demand.setIsFirst("1");
//        demand.setTitle(title);
//        demand.setBriefInfo(briefInfo);
//        demand.setSpecificInfo(specificInfo);
//        demand.setUnit(unit);
//        demand.setBudget(budget);
//        demand.setDeadline(deadline);
        demand.setState("0");

        demandDao.addOne(demand);
    }
    /**
     * 拒绝用户报名
     */
    @Override
    public void refuseOne(Integer id) {
        Integer userId = demandDao.findUserId(id);
        String userEmail = demandDao.findUserEmail(userId);
        String result = "已拒绝";

        demandDao.updateResult(result,id);

        // 发送邮件通知用户
        String content = "您的报名需求已被拒绝！";
        MailUtils.sendMail(userEmail,content,"通知邮件");
    }

    /**
     * 通过用户报名
     */
    @Override
    public void passOne(Integer id) {
        Integer userId = demandDao.findUserId(id);
        String userEmail = demandDao.findUserEmail(userId);
        String result = "已通过";

        demandDao.updateResult(result,id);
        // 发送邮件通知用户
        String content = "您的报名需求已通过！";
        MailUtils.sendMail(userEmail,content,"通知邮件");
    }

    /**
     *    查询所有待审核企业
     */
    @Override
    public List<Enterprise> findAll() {
        return enterpriseDao.findAll();
    }
}
