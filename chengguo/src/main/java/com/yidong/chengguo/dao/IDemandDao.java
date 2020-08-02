package com.yidong.chengguo.dao;

import com.yidong.chengguo.entity.Demand;
import com.yidong.chengguo.entity.UserSign;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 需求的Dao层，使用mybatis注解开发
 */
public interface IDemandDao {

    /**
     *  查询总记录数
     */
    @Select("select count(*) from demand where state = 1 and (title like #{content}" +
            " or briefInfo like #{content} or specificInfo like #{content} or unit like #{content} " +
            " or budget like #{content})")
    int findTotalCount(String content);

    /**
     *  分页查询需求
     */
    @Select("select * from demand where state = 1 and (title like #{content}"  +
            " or briefInfo like #{content} or specificInfo like #{content} or unit like #{content} " +
            " or budget like #{content} )limit #{start},#{pageSize}")
    List<Demand> findByPage(int start, int pageSize, String content);

    /**
     *  通过企业id查询需求
     */
    @Select("select * from demand where enterpriseId = #{id}")
    List<Demand> findByEnterpriseId(Integer id);

    /**
     *  查询id查询需求
     */
    @Select("select * from demand where id = #{id}")
    List<Demand> findById(Integer id);

    /**
     *  更新需求信息
     */
    @Update("update demand set userName = #{linkName},userPhone = #{phoneNum}," +
            "userMessage = #{introduction} where id = #{demandId}")
    void sign(String introduction, String linkName, String phoneNum, int demandId);

    /**
     *  保存需求信息到中间表
     */
    @Insert("insert into usersign (userId,demandId) values (#{uid},#{demandId})")
    void save(int uid,int demandId);

    /**
     *  通过中间表，查询id信息
     */
    @Select("select id from usersign where userId = #{uid} and demandId = #{demandId}")
    void check(int uid, int demandId);

    /**
     *  通过id查询单个需求
     */
    @Select("select * from demand where id = #{demandId}")
    Demand findOne(int demandId);

    /**
     *  增加需求
     */
    @Insert("insert into demand (enterpriseId,title,briefInfo,specificInfo,unit,budget,deadline,state,userName," +
            "userMessage,userPhone) values (#{enterpriseId},#{title},#{briefInfo},#{specificInfo},#{unit}," +
            "#{budget},#{deadline},#{state},#{linkName},#{introduction},#{phoneNum})")
    void addOne(Integer enterpriseId, String title, String briefInfo, String specificInfo, String unit, String budget,
                String deadline, String state, String linkName, String introduction, String phoneNum);

    /**
     *  通过中间表，进行多对多查询
     */
    @Select("select * from usersign where userId = #{id}")
    @Results(id = "personalMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "userId",property = "userId"),
            @Result(column = "demandId",property = "demandId"),
            @Result(property = "demands",column = "demandId",
                    many = @Many(select = "com.yidong.chengguo.dao.IDemandDao.findById",fetchType = FetchType.LAZY))
    })
    UserSign personalManage(int id);

    /**
     *  通过中间表 查询用户id
     */
    @Select("select userId from usersign where demandId = #{id}")
    Integer findUserId(Integer id);

    /**
     *  查询用户邮箱
     */
    @Select("select email from user where id = #{userId}")
    String findUserEmail(Integer userId);

    /**
     *  更新需求的结果
     */
    @Update("update demand set result = #{result} where id = #{id}")
    void updateResult(String result,Integer id);
}
