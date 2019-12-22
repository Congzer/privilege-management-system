package com.congzer.pms.dao;

import com.congzer.pms.domain.UserInfo;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface UserDao {

    @Select("select * from users where username= #{username}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select="com.congzer.pms.dao.RoleDao.findByUserId"))
    })
    UserInfo findByUsername(String username) throws  Exception;

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users (email,username,password,phoneNum,status) values (#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.congzer.pms.dao.RoleDao.findByUserId"))
    })
    UserInfo findById(String id);

    @Insert("insert into users_role (userId,roleId) values(#{userId},#{roleId})")
    void saveRole(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;
}
