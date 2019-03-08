package com.example.groovyDemo.controller;

import com.example.groovyDemo.Groovy;
import com.example.groovyDemo.service.PersonService;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
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
        while(true){

            Object res3 = groovy.runGroovyScriptByFile(null, "hello3.groovy", "hello", new String[] { "param3", "param4" });
            System.out.println(res3);
            return "excel 导入成功";
        }
        /*Object res3 = groovy.runGroovyScriptByFile(new String[] { "F:\\" }, "hello2.groovy", "importExcel", new Object[] { file,personService});*/
//        System.out.println(res3);

    }

    @RequestMapping(value="/testExcel", method = RequestMethod.GET)
    public String testExcel(){
        Groovy groovy = new Groovy();
        int i=0;
        while(i<100){

            Object res3 = groovy.runGroovyScriptByFile(null, "hello3.groovy", "hello", new String[] { "param3", "param4" });
            System.out.println(res3);
            i++;
        }
        /*Object res3 = groovy.runGroovyScriptByFile(new String[] { "F:\\" }, "hello2.groovy", "importExcel", new Object[] { file,personService});*/
//        System.out.println(res3);
        return null;
    }

    @RequestMapping(value="/hello1")
    public synchronized String hello(){
        System.out.println("进来了");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("出来了");
        return "hello";
    }

    @RequestMapping(value="/testgroovy")
    public void testGroovy(){
        int i=0;
        Groovy groovy = new Groovy();
        while(i<10000){
            String script = "def hello(param1,param2) {return \"the params is $param1 and $param2\"}";
            Object res1 = groovy.runGroovyScript(script, "hello", new String[] { "param1", "param2" });
            System.out.println(res1);
            i++;
        }

    }

    @RequestMapping("/test2")
    public void test2(){
        while (true){
            StringBuffer buffer = new StringBuffer();
            //define API
            buffer.append("class User{")
                    .append("String name;Integer age;")
                    //.append("User(String name,Integer age){this.name = name;this.age = age};")
                    .append("String sayHello(){return 'Hello,I am ' + name + ',age ' + age;}}\n");
            //Usage
            buffer.append("def user = new User(name:'zhangsan',age:1);")
                    .append("user.sayHello();");
            //groovy.lang.Binding
            System.out.println(buffer.toString());
            Binding binding = new Binding();
            GroovyShell shell = new GroovyShell(binding);
            String message = (String)shell.evaluate(buffer.toString());
            System.out.println(message);
            //重写main方法,默认执行
            String mainMethod = "static void main(String[] args){def user = new User(name:'lisi',age:12);print(user.sayHello());}";
            shell.evaluate(mainMethod);
            shell = null;
        }
    }
}
