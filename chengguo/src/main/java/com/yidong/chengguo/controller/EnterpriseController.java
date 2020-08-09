package com.yidong.chengguo.controller;

import com.yidong.chengguo.entity.Enterprise;
import com.yidong.chengguo.entity.User;
import com.yidong.chengguo.service.IEnterpriseService;
import com.yidong.chengguo.service.IUserService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 企业控制类
 */
@Controller
@RequestMapping("enterprise")
public class EnterpriseController {

    @Autowired
    private IEnterpriseService enterpriseService;

    @Autowired
    private IUserService userService;

    /**
     *  用户进行企业认证
     */
    @ResponseBody
    @PostMapping("identify")
    public String identify(String uid,String introduction,String linkName,String phoneNum){
        enterpriseService.save(Integer.parseInt(uid),introduction,linkName,phoneNum);

        return "true";
    }

    /**
     *  企业对其发布需求的管理
     */
    @GetMapping("demandManage")
    public String demandManage(Integer userId, HttpSession session){
        Enterprise enterprise = enterpriseService.findOne(userId);

        session.setAttribute("demandManage",enterprise.getDemands());
        session.setAttribute("enterpriseId",enterprise.getId());
        // 重定向到需求管理jsp页面
        return "redirect:/demandManage.jsp";
    }

    /**
     *  企业发布需求
     */
    @ResponseBody
    @PostMapping("release")
    public String release(String enterpriseId,String title,String briefInfo,String specificInfo,String unit,
                          String budget,String deadline){

        enterpriseService.release(enterpriseId,title,briefInfo,specificInfo,unit,budget,deadline);

        return "true";
    }

    /**
     *  通过用户的需求报名
     */
    @RequestMapping("refuseOne")
    public String refuseOne(Integer id,HttpSession session){
        enterpriseService.refuseOne(id);
        User user = (User) session.getAttribute("user");
        return demandManage(user.getId(),session);
    }

    /**
     *  拒绝用户的需求报名
     */
    @RequestMapping("passOne")
    public String passOne(Integer id,HttpSession session){
        enterpriseService.passOne(id);
        User user = (User) session.getAttribute("user");
        return demandManage(user.getId(),session);
    }

    /**
     *  企业认证
     */
    @RequestMapping("identify")
    public String identify(HttpSession session){
        List<Enterprise> enterprises= enterpriseService.findAll();
        System.out.println(enterprises);
        session.setAttribute("enterprises",enterprises);
        return "redirect:/identify.jsp";
    }

    /**
     *     管理员通过企业认证
     */
    @RequestMapping("passIdentify")
    public String passIdentify(Integer id,String email,HttpSession session){
        userService.passIdentify(id,email);

        return identify(session);
    }

    /**
     *     管理员拒绝企业认证
     */
    @RequestMapping("refuseIdentify")
    public String refuseIdentify(Integer id,String email,HttpSession session){
        userService.refuseIdentify(id,email);

        return identify(session);
    }

}
