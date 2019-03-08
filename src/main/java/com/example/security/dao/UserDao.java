package com.example.security.dao;

import com.example.security.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/7
 **/
@Mapper
public interface UserDao {
    @Select("select id,username,password,enabled,email from users where username=#{userName}")
    Users selectOneUser(String userName);

}
