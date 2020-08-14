package com.yidong.chengguo.service.impl;

import com.yidong.chengguo.dao.IEnterpriseDao;
import com.yidong.chengguo.dao.IUserDao;
import com.yidong.chengguo.entity.User;
import com.yidong.chengguo.service.IUserService;
import com.yidong.chengguo.utils.MailUtils;
import com.yidong.chengguo.utils.SimpleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户业务实现类
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IEnterpriseDao enterpriseDao;
    /**
     * 保存用户
     */
    @Override
    public boolean save(User user) {
        //根据用户名查询用户对象
        String username = userDao.findUsername(user.getUsername());
        String email = userDao.findUserEmail(user.getEmail());
        //判断u是否为null
        if(username != null || email != null){
            //用户名或邮箱存在，注册失败
            return false;
        }

        //2.保存用户信息
        //2.1设置激活码，唯一字符串
        user.setCode(UUID.randomUUID().toString().replace("-",""));
        //2.2设置激活状态
//        user.setStatus("0");
        userDao.saveUser(user);

        //3.激活邮件发送，邮件正文？

        String content="<a href='http://127.0.0.1:8080/chengguo/user/active?code="+user.getCode()+"'>【点击激活账号】</a>";

        MailUtils.sendMail(user.getEmail(),content,"激活邮件");

        return true;
    }
    /**
     * 激活用户
     */
    @Override
    public boolean active(String code) {
        //1.根据激活码查询用户对象
        int id = 0;
        try {
            id = userDao.findByCode(code);
        }catch (Exception e){
            return false;
        }
        userDao.updateState("1",id);
        return true;
    }
    /**
     * 用户登录
     */
    @Override
    public Object[] login(String username, String password) {
        Object[] result = new Object[2];
        User user = userDao.login(username,password);
        if (user != null){
            if (user.getState().equals("-1")){
                Date date = new Date();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = dateFormat.format(date);
                if (user.getBanTime().compareTo(currentDate) > 0 ){
                    result[1] = "账号已被封禁...请于"+user.getBanTime()+"后登陆";
                }else{
                    userDao.updateState("1",user.getId());
                    result[1] = "true";
                }
            }else if (user.getState().equals("0")){
                result[1] = "账号尚未激活！请先激活！";
            }else{
                result[1] = "true";
            }
        }
        result[0] = user;
        return result;
    }
    /**
     * 发送验证码
     */
    @Override
    public String findCode(String email) {
        if ( userDao.findUserEmail(email) == null){
            return null;
        }else{
            String checkCode = SimpleUtils.getCheckCode();
            String content="您的验证码为：" + checkCode;
            MailUtils.sendMail(email,content,"验证邮件");
            return checkCode;
        }
    }
    /**
     * 找回用户
     */
    @Override
    public void findUser(String email, String password) {
        userDao.findUser(email,password);
    }
    /**
     * 修改个人信息
     */
    @Override
    public void revise(Integer id, String type, String data) {
        userDao.revise(id,type,data);
    }
    /**
     * 检查用户名
     */
    @Override
    public boolean checkName(Integer id, String data) {
        if ( userDao.findUsername(data) != null){
            return false;
        }

        return true;
    }


    /**
     *    查询所有用户
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     *    封禁用户
     */
    @Override
    public void banOne(Integer id,String banTime) {
        userDao.banOne(id,banTime);
    }

    /**
     *    通过用户认证
     */
    @Override
    public void passIdentify(Integer id,String email) {
        Integer uid = enterpriseDao.findUid(id);
        userDao.revise(uid,"status","2");
        enterpriseDao.updateState(id,"2");

        String content = "你的企业认证已通过！";
        MailUtils.sendMail(email,content,"通知邮件");
    }

    /**
     *   拒绝用户认证
     */
    @Override
    public void refuseIdentify(Integer id,String email) {
        enterpriseDao.updateState(id,"-1");

        String content = "很抱歉，你的企业认证申请没有通过，您可以尝试重新申请！";
        MailUtils.sendMail(email,content,"通知邮件");
    }
}
