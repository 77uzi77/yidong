package com.yidong.chengguo.service;

import com.yidong.chengguo.entity.User;

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
}
