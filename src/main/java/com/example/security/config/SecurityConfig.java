package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/2/25
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    /**
     * @Author liuf
     * @Description: 基于内存的用户存储。
     * @Date 16:20 2019/2/25
     * @Param [auth]
     * @return void
     **/
    /*@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("liuf").password(new BCryptPasswordEncoder().encode("123456")).roles("USER").and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");
    }*/


    /**
     * @Author liuf
     * @Description: 基于数据库表的用户存储
     * @Date 16:21 2019/2/25
     * @Param [auth]
     * @return void
     **/
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled,email from users where username=?")
                .authoritiesByUsernameQuery("select username,authority from authorities where username = ?")
                .groupAuthoritiesByUsername("select g.id, g.group_name, ga.authority from groups g, group_members gm, group_authorities ga where gm.username = ? and g.id = ga.group_id and g.id = gm.group_id");

    }
}
