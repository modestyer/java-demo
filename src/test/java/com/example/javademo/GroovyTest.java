package com.example.javademo;

import com.example.JavaDemoApplication;
import com.example.groovyDemo.entity.Person;
import com.example.groovyDemo.service.GroovyService;
import com.example.groovyDemo.service.PersonService;
import com.example.util.GroovyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/13
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaDemoApplication.class,webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GroovyTest {

    @Autowired
    GroovyService groovyService;

    @Autowired
    PersonService personService;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromAddr;

    @Test
    public void test1() throws Exception{

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

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("import com.example.groovyDemo.dao.PersonDao\n");
        stringBuffer.append("import com.example.groovyDemo.entity.Person\n");
        stringBuffer.append("import org.springframework.beans.factory.annotation.Autowired\n");
        stringBuffer.append("import org.springframework.stereotype.Component\n");
        stringBuffer.append("import org.springframework.transaction.annotation.Transactional\n");
//        stringBuffer.append("import com.example.groovyDemo.service.PersonService\n");
//        stringBuffer.append("@Transactional(rollbackFor = Exception.class)\n");
        stringBuffer.append("@Component\n");
        stringBuffer.append("@Transactional(rollbackFor = Exception.class)\n");
        stringBuffer.append("class TestGroovy {\n");
        stringBuffer.append("@Autowired\n");
        stringBuffer.append("PersonDao personDao;\n");
        stringBuffer.append("public Object insertTest(List<Person> list){\n");
        stringBuffer.append("int n = 3;\n");
        stringBuffer.append("for (Person person : list) {\n");
        stringBuffer.append("person.setId(n);\n");
        stringBuffer.append("if(n==5){\n");
        stringBuffer.append(" person.setId(3);\n");
        stringBuffer.append("}\n");
        stringBuffer.append("person.setPassWord('123456')\n");
        stringBuffer.append("personDao.insert(person)\n");
        stringBuffer.append("n++;\n");
        stringBuffer.append("}\n");
        stringBuffer.append("}\n");
        stringBuffer.append("}\n");
        System.out.println(stringBuffer.toString());

//        groovyService.runScript(stringBuffer.toString(),list);
        GroovyUtil.runScript(stringBuffer.toString(), "insertTest", new Object[]{list});
        /*GroovyService service = SpringContextUtils.getBean(GroovyService.class);
        service.execute(list);*/



    }


    @Test
    public void test2(){
        personService.insert();
    }


    @Test
    public void test3() throws Exception{
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

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("import com.example.groovyDemo.dao.PersonDao\n");
        stringBuffer.append("import com.example.groovyDemo.entity.Person\n");
        stringBuffer.append("import org.springframework.beans.factory.annotation.Autowired\n");
//        stringBuffer.append("import org.springframework.stereotype.Service\n");
//        stringBuffer.append("import org.springframework.transaction.annotation.Transactional\n");
//        stringBuffer.append("import com.example.groovyDemo.service.PersonService\n");
//        stringBuffer.append("@Transactional(rollbackFor = Exception.class)\n");
//        stringBuffer.append("@Service\n");
//        stringBuffer.append("@Transactional(rollbackFor = Exception.class)\n");
        stringBuffer.append("class TestGroovy {\n");
        stringBuffer.append("@Autowired\n");
        stringBuffer.append("PersonDao personDao;\n");
        stringBuffer.append("public Object insertTest(List<Person> list){\n");
        stringBuffer.append("int n = 3;\n");
        stringBuffer.append("for (Person person : list) {\n");
        stringBuffer.append("person.setId(n);\n");
        stringBuffer.append("if(n==5){\n");
        stringBuffer.append(" person.setId(3);\n");
        stringBuffer.append("}\n");
        stringBuffer.append("person.setPassWord('123456')\n");
        stringBuffer.append("personDao.insert(person)\n");
        stringBuffer.append("n++;\n");
        stringBuffer.append("}\n");
        stringBuffer.append("}\n");
        stringBuffer.append("}\n");
        groovyService.runScript(stringBuffer.toString(), list);
    }


    @Test
    public void sendMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddr);
        message.setTo("810033500@qq.com");
        message.setSubject("测试");
        // 内容
        message.setText("测试邮件");

        mailSender.send(message);
    }
}
