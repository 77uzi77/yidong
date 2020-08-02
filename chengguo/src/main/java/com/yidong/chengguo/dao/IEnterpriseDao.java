package com.yidong.chengguo.dao;

import com.yidong.chengguo.entity.Enterprise;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

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
}
