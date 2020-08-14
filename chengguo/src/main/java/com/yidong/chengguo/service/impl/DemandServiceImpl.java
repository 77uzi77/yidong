package com.yidong.chengguo.service.impl;

import com.yidong.chengguo.dao.IDemandDao;
import com.yidong.chengguo.dao.IEnterpriseDao;
import com.yidong.chengguo.dao.IUserDao;
import com.yidong.chengguo.entity.Demand;
import com.yidong.chengguo.entity.PageBean;
import com.yidong.chengguo.entity.User;
import com.yidong.chengguo.entity.UserSign;
import com.yidong.chengguo.service.IDemandService;
import com.yidong.chengguo.utils.MailUtils;
import com.yidong.chengguo.utils.SimpleUtils;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 需求的业务实现类
 */
@Service
@Transactional
public class DemandServiceImpl implements IDemandService {

    @Autowired
    private IDemandDao demandDao;

    @Autowired
    private IEnterpriseDao enterpriseDao;


    /**
     * 分页查询需求
     */
    @Override
    public PageBean<Demand> pageQuery(String content, String currentPage, String pageSize) {
        //设置总记录数
        int totalCount = demandDao.findTotalCount(content);
        // 得到分页条件参数
        Object[] pageParams = SimpleUtils.getPageParams(currentPage,pageSize, totalCount);
        PageBean pb = (PageBean) pageParams[0];
        int start = (int) pageParams[1];

        List<Demand> list = demandDao.findByPage(start,pb.getPageSize(),content);
        pb.setList(list);

        return pb;
    }

    /**
     * 需求报名
     */
    @Override
    public void sign(String introduction, String linkName, String phoneNum, String uidStr, String demandIdStr) {
        int uid = Integer.parseInt(uidStr);
        int demandId = Integer.parseInt(demandIdStr);


        Demand demand = demandDao.findOne(demandId);
        if (demand.getUserName() == null){
            demandDao.sign(introduction,linkName,phoneNum,demandId);
        }else{
            demand.setIsFirst("0");
            demand.setUserName(linkName);
            demand.setUserMessage(introduction);
            demand.setUserPhone(phoneNum);
            demandDao.addOne(demand);
            demandId = demand.getId();
            System.out.println(demandId);

        }

        if (demandDao.check(uid, demandId) == null){
            // 如果中间表没有该报名信息
//               demandDao.sign(introduction,linkName,phoneNum,demandId);
            demandDao.save(uid,demandId);
//               return;
        }

    }

    /**
     * 个人需求管理
     */
    @Override
    public UserSign personalManage(int id) {
        UserSign personalDemands =  demandDao.personalManage(id);
        return personalDemands;
    }

    /**
     *    查询所有需求
     */
    @Override
    public List<Demand> findAll(String state) {
        return demandDao.findAll(state);
    }

    /**
     *     管理员通过企业需求
     */
    @Override
    public void passOne(Integer id,Integer enterpriseId) {
        demandDao.updateState(id,"1");

        Integer uid = enterpriseDao.findUid(enterpriseId);
        String email = demandDao.findUserEmail(uid);
        String content = "您发布的需求已通过！！";
        MailUtils.sendMail(email,content,"通知邮件");
    }

    /**
     *     管理员拒绝企业需求
     */
    @Override
    public void refuseOne(Integer id,Integer enterpriseId) {
        demandDao.updateState(id,"-1");

        Integer uid = enterpriseDao.findUid(enterpriseId);
        String email = demandDao.findUserEmail(uid);
        String content = "很抱歉，您发布的需求已被拒绝！";
        MailUtils.sendMail(email,content,"通知邮件");

    }
}
