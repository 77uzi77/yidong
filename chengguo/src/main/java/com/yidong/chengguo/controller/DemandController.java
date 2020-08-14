package com.yidong.chengguo.controller;

import com.yidong.chengguo.entity.Demand;
import com.yidong.chengguo.entity.PageBean;
import com.yidong.chengguo.entity.UserSign;
import com.yidong.chengguo.service.IDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.PushBuilder;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * 需要管理
 */
@Controller
@RequestMapping("demand")
public class DemandController {

    @Autowired
    private IDemandService demandService;


    /**
     *   分页查询需求，并添加模糊查询
     */
    @ResponseBody
    @GetMapping("pageQuery")
    public Object pageQuery(String content, String currentPage, String pageSize, HttpSession session){
        // 字符串拼接，实现模糊查询
        String realContent = "%"+content+"%";
        // 得到页面类，并返回给前端
        PageBean<Demand> pb =demandService.pageQuery(realContent,currentPage,pageSize);
        session.setAttribute("pb",pb);
        return pb;

    }

    /**
     *  用户报名需求
     */
    @ResponseBody
    @PostMapping("sign")
    public String sign(String introduction,String linkName,String phoneNum,String uid,String demandId){

        demandService.sign(introduction,linkName,phoneNum,uid,demandId);

        return "true";
    }

    /**
     *  用户的个人需求管理
     */
    @GetMapping("personalManage")
    public String personalManage(String userId,HttpSession session){
        UserSign personalManage = demandService.personalManage(Integer.parseInt(userId));

        // 判断用户需求是否存在
        if (personalManage != null){
            session.setAttribute("personalDemands",personalManage.getDemands());
        }

        // 重定向到个人管理jsp页面
        return "redirect:/personalManage.jsp";
    }

    /**
     *   管理员管理需求
     */
    @RequestMapping("enterpriseDemand")
    public String enterpriseDemand(HttpSession session){
        List<Demand> demands = demandService.findAll("0");
        session.setAttribute("demands",demands);
        return  "redirect:/enterpriseDemand.jsp";
    }

    /**
     *     管理员通过企业需求
     */
    @RequestMapping("passOne")
    public String passOne(Integer id,Integer enterpriseId,HttpSession session){
        demandService.passOne(id,enterpriseId);

        return enterpriseDemand(session);
    }

    /**
     *     管理员拒绝企业需求
     */
    @RequestMapping("refuseOne")
    public String refuseOne(Integer id,Integer enterpriseId,HttpSession session){
        demandService.refuseOne(id,enterpriseId);

        return enterpriseDemand(session);
    }

    @RequestMapping("platformDemand")
    public String platformDemand(HttpSession session){
        List<Demand> demands = demandService.findAll("1");
        session.setAttribute("demands",demands);

        return "redirect:/platformDemand.jsp";
    }
}
