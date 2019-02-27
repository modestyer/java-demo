package com.example.groovyDemo.dao;

import com.example.groovyDemo.entity.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/2/26
 **/
@Mapper
public interface PersonDao {
    @Insert("INSERT INTO users(id,username, password) VALUES (#{id,jdbcType=INTEGER},#{name}, #{passWord})")
    void insert(Person person);
}
