package com.example.groovyDemo.controller;

import com.example.groovyDemo.Groovy;
import com.example.groovyDemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/2/26
 **/
@RestController
public class GroovyImportExcelController {


    @Autowired
    private PersonService personService;

    @RequestMapping(value="/import", method = RequestMethod.POST)
    public String importExcel(@RequestParam MultipartFile file){
        Groovy groovy = new Groovy();
        Object res3 = groovy.runGroovyScriptByFile(new String[] { "F:\\" }, "hello2.groovy", "importExcel", new Object[] { file,personService});
//        System.out.println(res3);
        return "excel 导入成功";
    }

    @RequestMapping(value="/hello")
    public String hello(){
        return "hello";
    }

}
