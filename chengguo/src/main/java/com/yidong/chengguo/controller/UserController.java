package com.yidong.chengguo.controller;


import com.yidong.chengguo.entity.User;
import com.yidong.chengguo.service.IUserService;
import com.yidong.chengguo.utils.MD5Util;
import com.yidong.chengguo.utils.SimpleUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户controller
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     *  得到登录时的验证码
     */
    @GetMapping("getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0,0, width,height);

        //产生4个随机验证码，12Ey
        String checkCode = SimpleUtils.getCheckCode();
        //将验证码放入HttpSession中
        session.setAttribute("code",checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的小大
        g.setFont(new Font("黑体",Font.BOLD,24));
        //向图片上写入验证码
        g.drawString(checkCode,15,25);

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        ImageIO.write(image,"PNG",response.getOutputStream());
    }

    /**
     * 用户注册
     */
    @ResponseBody
    @PostMapping("regist")
    public String regist(HttpServletRequest request) throws IOException {

        User user = new User();

        user.setUsername(request.getParameter("username"));
        String password = request.getParameter("password");
        // 对用户密码进行md5加密
        password = MD5Util.md5Jdk(password);

        user.setPassword(password);
        user.setEmail(request.getParameter("email"));

        // 得到用户上传的头像文件，并保存到本地
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("icon");
        String fileName = UUID.randomUUID().toString().replace("-","")+"."+
                FilenameUtils.getExtension(file.getOriginalFilename());
        String path =  request.getSession().getServletContext().getRealPath("images");

        File targetFile = new File(path, fileName);
        file.transferTo(targetFile);

        user.setIcon(fileName);

        // 判断用户注册是否成功
        if (user == null ){
            return "false";
        }else{
            if (userService.save(user)){
                return "true";
            }else{
                return "false";
            }
        }
    }

    /**
     *  用户进行激活账号
     */
    @GetMapping("active")
    public void activeUser (HttpServletRequest request,HttpServletResponse response) throws IOException {

        //1.获取激活码
        String code = request.getParameter("code");
        if (code != null) {
            //2.调用service完成激活
            boolean flag = userService.active(code);

            //3.判断标记
            String msg = null;
            if (flag) {
                //激活成功
                msg = "激活成功，请登录！";
            } else {
                //激活失败
                msg = "激活失败，请联系管理员!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);

        }
    }

    /**
     * 用户登录
     */
    @ResponseBody
    @RequestMapping("login")
    public String login(String username,String password,String code,HttpSession session){
        // 得到正确验证码
        String realCode = (String) session.getAttribute("code");

        //将用户输入的密码 进行md5加密后，与正确的密码进行比对
        password = MD5Util.md5Jdk(password);
        Object[] result = userService.login(username, password);
        User user = (User) result[0];
        // 判断用户账号密码，及验证码是否正确
        if (user != null && realCode.equalsIgnoreCase(code)){
            if (result[1] == "true"){
                session.setAttribute("user",user);
                // 设置session过期时间
                session.setMaxInactiveInterval(60 * 60 *30);
            }
            return (String) result[1];
        }else{
            return "账号密码或验证码错误！";
        }
    }

    /**
     *  发送验证码到用户邮箱，进行找回密码的校验
     */
    @ResponseBody
    @PostMapping("sendFindCode")
    public String sendFindCode(String email,HttpSession session){
        String checkCode = userService.findCode(email);
        // 判断 验证码是否发送成功
        if (  checkCode != null ){
            session.setAttribute("realCode",checkCode);
            return "true";
        }else{
            return "发送失败！请检查邮箱！";
        }
    }

    /**
     *  用户找回密码
     */
    @ResponseBody
    @PostMapping("findUser")
    public String findUser(String email,String code,String password,HttpSession session){
        // 得到正确验证码
        String realCode = (String) session.getAttribute("realCode");
        password = MD5Util.md5Jdk(password);
        // 判断验证码是否正确
        if ( realCode.equalsIgnoreCase(code) ){
            userService.findUser(email,password);
            return "true";
        }else{
            return "验证码错误！请重新输入！";
        }
    }

    /**
     * 用户修改个人信息
     */
    @ResponseBody
    @PostMapping("revise")
    public String revise(HttpServletRequest request,HttpSession session) throws IOException {
        String type = request.getParameter("type");
        User user = (User) session.getAttribute("user");
        String data = request.getParameter("username");

        // 判断用户修改的信息类型
        if ("icon".equals(type)){
            // 用户修改头像，则找到并删除本地的原头像，保存新头像到本地
            File lastFile = new File(session.getServletContext().getRealPath("images")+"/"+user.getIcon());
            if (lastFile.isFile() && lastFile.exists()){
                lastFile.delete();
            }

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("icon");
            String fileName = UUID.randomUUID().toString().replace("-","")+"."+
                    FilenameUtils.getExtension(file.getOriginalFilename());
            String path =  request.getSession().getServletContext().getRealPath("images");

            File targetFile = new File(path, fileName);
            file.transferTo(targetFile);
            user.setIcon(fileName);
            data = fileName;

        }else if ("password".equals(type)) {
            // 用户修改密码，则检查用户输入的原密码是否正确，并加密保存新密码
            String lastPassword = request.getParameter("lastPassword");
            lastPassword = MD5Util.md5Jdk(lastPassword);
            if ( !user.getPassword().equals(lastPassword) ){
                return "密码错误！请重新输入！";
            }
            data = request.getParameter("newPassword");
            data = MD5Util.md5Jdk(data);
            user.setPassword(data);
        }else{
            // 用户修改用户名，保持用户名的唯一性，判断用户名是否存在
            if ( !userService.checkName(user.getId(),data) ){
                return "该用户名已存在！";
            }
            user.setUsername(data);
        }

        // 修改个人信息
        userService.revise(user.getId(),type,data);
        session.setAttribute("user",user);

        return "true";

    }

    /**
     * 用户退出登录
     */
    @GetMapping("exit")
    public String exit(HttpSession session){
        // 注销session
        session.invalidate();
        // 转发到登录页面
        return "redirect:/login.jsp";
    }

    /**
     *     管理员查询所有用户
     */
    @RequestMapping("ban")
    public String ban(HttpSession session){
        List<User> users = userService.findAll();
        session.setAttribute("users",users);
        return "redirect:/banUser.jsp";
    }

    /**
     *     管理员封禁用户
     */
    @RequestMapping("banOne")
    public String banOne(Integer id, String banTime,HttpSession session){
//        System.out.println(banTime);
        userService.banOne(id,banTime);

        return ban(session);
    }


}
