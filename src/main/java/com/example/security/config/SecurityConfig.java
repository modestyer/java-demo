package com.example.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger= LoggerFactory.getLogger(SecurityConfig.class);

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
        logger.info("设置内存用户{}","admin;liuf");
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
        logger.info("基于数据库表的用户存储");
        auth.jdbcAuthentication().passwordEncoder(passwordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled,email from users where username=?")
                .authoritiesByUsernameQuery("select username,authority from authorities where username = ?")
                .groupAuthoritiesByUsername("select g.id, g.group_name, ga.authority from groups g, group_members gm, group_authorities ga where gm.username = ? and g.id = ga.group_id and g.id = gm.group_id");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("设置拦截资源");
        http.formLogin()          // 定义当需要用户登录时候，转到的登录页面。
                .and()
                .authorizeRequests()    // 定义哪些URL需要被保护、哪些不需要被保护
                .anyRequest()        // 任何请求,登录后可以访问
                .authenticated();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密
        String encodedPassword = passwordEncoder.encode("123456");
        System.out.println(encodedPassword);
    }
}
