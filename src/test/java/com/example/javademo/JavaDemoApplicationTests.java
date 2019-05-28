package com.example.javademo;

import com.example.JavaDemoApplication;
import com.example.boot.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaDemoApplication.class)
public class JavaDemoApplicationTests {

	@Autowired
	Person person;

	@Autowired
	ApplicationContext ioc;

	@Autowired
	DataSource dataSource;

	@Test
	public void test1(){
		boolean b = ioc.containsBean("helloBootService");
		System.out.println(b);
	}

	@Test
	public void contextLoads() {
		System.out.println(person);
	}

	@Test
	public void test2(){
		System.out.println(dataSource.getClass());
	}

}

