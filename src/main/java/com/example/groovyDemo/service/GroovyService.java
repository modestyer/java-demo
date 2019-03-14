package com.example.groovyDemo.service;

import com.example.groovyDemo.entity.Person;
import com.example.util.GroovyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/13
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class GroovyService {

    public void runScript(String script,List<Person> list) throws Exception{
        GroovyUtil.runScript(script, "insertTest", new Object[]{list});
    }
}
