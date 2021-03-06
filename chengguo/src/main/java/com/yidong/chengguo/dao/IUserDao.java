package com.yidong.chengguo.dao;

import com.yidong.chengguo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;

/**
 * 用户的Dao层
 */
public interface IUserDao {

    /**
     *  判断用户名是否存在
     */
    @Select("select username from user where username = #{username} ")
    String findUsername(String username);

    /**
     *  判断邮箱是否存在
     */
    @Select("select email from user where email = #{email}")
    String findUserEmail(String email);

    /**
     *  保存用户
     */
    @Insert("insert into user (username,email,password,icon,code)" +
            " values (#{username},#{email},#{password},#{icon},#{code})")
    void saveUser(User user);

    /**
     *  更新用户状态
     */
    @Update("update user set state = #{state}  where id = #{id}")
    void updateState(String state,Integer id);

    /**
     *  通过用户标识码判断用户
     */
    @Select("select id from user where code = #{code}")
    int findByCode(String code);

    /**
     *  用户登录检查
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    User login(String username, String password);

    /**
     *  更新用户密码
     */
    @Update("update user set password = #{password} where email = #{email}")
    void findUser(String email, String password);

    /**
     *  更新用户信息
     */
    @Update("update user set ${type} = #{data} where id = #{id}")
    void revise(Integer id, String type, String data);

    /**
     *     通过id查询用户
     */
    @Select("select username,email from user where id = #{id}")
    User findOne(Integer id);

    /**
     *     查询所有用户和企业
     */
    @Select("select * from user where state = 1 and (status = 1 or status = 2)")
    List<User> findAll();

    /**
     *   封禁用户
     */
    @Update("update user set state = -1 , banTime = #{banTime} where id = #{id}")
    void banOne(Integer id, String banTime);
}
