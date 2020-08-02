package com.yidong.chengguo.entity;


/**
 * 用户实体类
 */
public class User {
    //用户id 自增长 主键
    private Integer id;
    // 用户名
    private String username;
    // 邮箱
    private String email;
    // 密码
    private String password;
    // 头像
    private String icon;
    // 身份 默认 1 企业 2 管理员3
    private String status;
    // 状态 默认 0 注册已激活1
    private String state;
    // 用户标识码，用于用户激活
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", icon='" + icon + '\'' +
                ", status='" + status + '\'' +
                ", state='" + state + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
