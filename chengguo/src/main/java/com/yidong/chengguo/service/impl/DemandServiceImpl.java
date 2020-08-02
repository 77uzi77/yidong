package com.yidong.chengguo.service.impl;

import com.yidong.chengguo.dao.IDemandDao;
import com.yidong.chengguo.entity.Demand;
import com.yidong.chengguo.entity.PageBean;
import com.yidong.chengguo.entity.User;
import com.yidong.chengguo.entity.UserSign;
import com.yidong.chengguo.service.IDemandService;
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
        // 判断该需求是否存在，不存在则报异常，通过catch捕获
        try {
            demandDao.check(uid,demandId);
        }catch (Exception e){
            // 捕获到异常，则增加需求
            demandDao.sign(introduction,linkName,phoneNum,demandId);
            demandDao.save(uid,demandId);
            return;
        }

        Demand demand = demandDao.findOne(demandId);
        demandDao.addOne(demand.getEnterpriseId(),demand.getTitle(),demand.getBriefInfo(),demand.getSpecificInfo(),
            demand.getUnit(),demand.getBudget(),demand.getDeadline(),demand.getState(),linkName,introduction,phoneNum);

    }

    /**
     * 个人需求管理
     */
    @Override
    public UserSign personalManage(int id) {
        UserSign personalDemands =  demandDao.personalManage(id);
        return personalDemands;
    }
}
