package com.example.boot.config;

import com.example.boot.service.HelloBootService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuf
 * @create 2019-05-16 22:35
 * @Configuration：指明当前类是一个配置类；就是来替代之前的Spring配置文件
 *
 * 在配置文件中用<bean><bean/>标签添加组件
 */
@Configuration
public class MyConfig {

    @Bean
    public HelloBootService helloBootService(){
        System.out.println("添加此类东容器中。。。。");
        return new HelloBootService();
    }
}
