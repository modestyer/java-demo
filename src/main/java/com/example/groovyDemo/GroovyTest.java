package com.example.groovyDemo;

import org.apache.commons.io.IOUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/2/26
 **/
public class GroovyTest {
    static String groovyFile = "hello3.groovy";

    public static void main(String[] args) throws ScriptException, FileNotFoundException, IOException {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("groovy");
        //先测试下，行不行
        System.out.println("groovy解析结果:"+engine.eval("println 'HelloWord' \n 'HelloWordReturn'"));
        //读取源Groovy源程序
        String fileFullPath = groovyFile;
        String scriptContent = IOUtils.toString(new FileInputStream(fileFullPath));
        System.out.println("----------groovy-exec----------");
        Object object = engine.eval(scriptContent);
        System.out.println(object);
    }

    public static String rootDir(){
        String classDir=GroovyTest.class.getClassLoader().getResource("").getPath();
        int idx = classDir.lastIndexOf("/",classDir.length()-2);
        return classDir.substring(0,idx);
    }

}
