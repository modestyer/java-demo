package com.example.groovyDemo.service;

import com.example.groovyDemo.dao.PersonDao;
import com.example.groovyDemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void insert(Person person) { //插入一条记录
        personDao.insert(person);
    }
}
