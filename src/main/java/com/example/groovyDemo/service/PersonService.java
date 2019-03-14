package com.example.groovyDemo.service;

import com.example.groovyDemo.dao.PersonDao;
import com.example.groovyDemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/2/26
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PersonService {
    @Autowired
    private PersonDao personDao;

    public void insert() { //插入一条记录
        List<Person> list = new ArrayList<>();

        Person person = new Person();
        person.setAge("16");
        person.setName("小红");

        Person person1 = new Person();
        person1.setAge("17");
        person1.setName("小王");

        Person person2 = new Person();
        person2.setAge("19");
        person2.setName("小刚");

        Person person3 = new Person();
        person3.setAge("18");
        person3.setName("小明");

        Person person4 = new Person();
        person4.setAge("20");
        person4.setName("小李");

        list.add(person);
        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);

        int n=3;
        for(Person person5 : list){
            person5.setId(n);
            if(n==5){
                person5.setId(3);
            }
            personDao.insert(person5);
            n++;
        }
    }
}
