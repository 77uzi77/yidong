package com.yidong.chengguo.service;

import com.yidong.chengguo.entity.User;

import java.sql.Date;
import java.util.List;

/**
 * 用户业务接口
 */
public interface IUserService {
    /**
     * 保存用户
     */
    boolean save(User user);
    /**
     * 激活用户
     */
    boolean active(String code);
    /**
     * 用户登录
     */
    Object[] login(String username, String password);
    /**
     * 发送验证码
     */
    String findCode(String email);
    /**
     * 找回用户
     */
    void findUser(String email, String password);
    /**
     * 修改个人信息
     */
    void revise(Integer id, String type, String data);
    /**
     * 检查用户名
     */
    boolean checkName(Integer id, String data);

    /**
     *    查询所有用户
     */
    List<User> findAll();

    /**
     *    封禁用户
     */
    void banOne(Integer id,String banTime);

    /**
     *    通过用户认证
     */
    void passIdentify(Integer id,String email);

    /**
     *   拒绝用户认证
     */
    void refuseIdentify(Integer id,String email);
}
