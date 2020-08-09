package com.yidong.chengguo.dao;

import com.yidong.chengguo.entity.Enterprise;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 企业的Dao层
 */
public interface IEnterpriseDao {

    /**
     *  保存企业认证信息
     */
    @Insert("insert into enterprise (uid,message,linkName,phoneNum) values (#{uid},#{message},#{linkName},#{phoneNum})")
    void save(Integer uid,String message, String linkName, String phoneNum);


    /**
     *  一对多查询企业及其发布的需求
     */
    @Select("select * from enterprise where uid = #{uid}")
    @Results(id = "demandMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "message",property = "message"),
            @Result(column = "linkName",property = "linkName"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "state",property = "state"),
            @Result(property = "demands",column = "id",
                    many = @Many(select = "com.yidong.chengguo.dao.IDemandDao.findByEnterpriseId",fetchType = FetchType.LAZY))
    })
    Enterprise findOne(int uid);

    /**
     *  查询所有待审核的企业，及其用户信息
     */
    @Select("select * from enterprise where state = 1")
    @Results(id = "enterpriseMap",value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "message", property = "message"),
            @Result(column = "linkName", property = "linkName"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "state", property = "state"),
            @Result(property = "user", column = "uid",
                    one = @One(select = "com.yidong.chengguo.dao.IUserDao.findOne",fetchType = FetchType.LAZY))
    })
    List<Enterprise> findAll();

    /**
     *     更新企业认证状态
     */
    @Update("update enterprise set state = #{state} where id = #{id}")
    void updateState(Integer id, String state);

    /**
     *     查询企业的用户id
     */
    @Select("select uid from enterprise where id = #{id}")
    Integer findUid(Integer enterpriseId);

}
