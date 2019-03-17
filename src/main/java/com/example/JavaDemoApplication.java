package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "com.example.groovyDemo.dao")
public class JavaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaDemoApplication.class, args);
	}

}

