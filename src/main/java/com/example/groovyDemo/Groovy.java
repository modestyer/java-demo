package com.example.groovyDemo;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.*;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/2/26
 **/
@RestController
public class Groovy {
    @Autowired
    Environment env;

    /**
     * @author hpym365
     * @创建时间 2017年3月2日 下午10:07:47
     * @desc 描述
     * @param filepath
     *            groovy脚本文件的路径(当参数为null默认指定工程路径下的groovy文件夹)
     * @param filename
     *            groovy脚本文件名字
     * @param params
     *            执行脚本的参数
     * @return 返回执行结果
     */
    public Object runGroovyScriptByFile(String[] filepath, String filename, Map<String, Object> params) {

        if (filepath == null || filepath.length == 0)
            filepath = new String[] { "grovvy\\" };// 定义Groovy脚本引擎的根路径

        try {
            // String[]{".\\src\\main\\java\\com\\senyint\\util\\"}
            GroovyScriptEngine engine = new GroovyScriptEngine(filepath);
            return engine.run(filename, new Binding(params));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author hpym365
     * @创建时间 2017年3月2日 下午10:17:42
     * @desc 描述
     * @param filepath
     *            groovy脚本文件的路径(当参数为null默认指定工程路径下的groovy文件夹)
     * @param filename
     *            groovy脚本文件名字
     * @param funname
     *            执行指定的方法名称
     * @param params
     *            执行脚本的参数
     * @return 返回执行结果
     */
    public Object runGroovyScriptByFile(String[] filepath, String filename, String funname, Object[] params) {

        if (filepath == null || filepath.length == 0)
            filepath = new String[] { "grovvy\\" };// 定义Groovy脚本引擎的根路径
        try {
            // String[]{".\\src\\main\\java\\com\\senyint\\util\\"}
            GroovyScriptEngine engine = new GroovyScriptEngine(filepath);
            Script script = engine.createScript(filename, new Binding());
            return script.invokeMethod(funname, params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author hpym365
     * @创建时间 2017年3月2日 下午9:43:08
     * @desc 执行groovy脚本(不指定方法)
     * @param script
     *            要执行的脚本 通过字符串传入，应用场景 如从数据库中读取出来的脚本等
     * @param params
     *            执行grovvy需要传入的参数
     * @return 脚本执行结果
     */
    public Object runGroovyScript(String script, Map<String, Object> params) {
        if (script == null || "".equals(script))
            throw new RuntimeException("方法runGroovyScript无法执行，传入的脚本为空");

        try {
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("groovy");
            Bindings bindings = engine.createBindings();
            bindings.putAll(params);
            return engine.eval(script, bindings);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author hpym365
     * @创建时间 2017年3月2日 下午9:43:08
     * @desc 执行groovy脚本(需要指定方法名)
     * @param script
     *            要执行的脚本 通过字符串传入，应用场景 如从数据库中读取出来的脚本等
     * @param funName
     *            要执行的方法名
     * @param params
     *            执行grovvy需要传入的参数
     * @return
     */
    public Object runGroovyScript(String script, String funName, Object[] params) {
        try {
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("groovy");
            // String HelloLanguage = "def hello(language) {return \"Hello
            // $language\"}";
            engine.eval(script);
            Invocable inv = (Invocable) engine;
            return inv.invokeFunction(funName, params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public void getScriptEngineFactoryList() {
        ScriptEngineManager manager = new ScriptEngineManager();

        List<ScriptEngineFactory> factories = manager.getEngineFactories();

        for (ScriptEngineFactory factory : factories) {

            System.out.printf(
                    "Name: %s%n" + "Version: %s%n" + "Language name: %s%n" + "Language version: %s%n"
                            + "Extensions: %s%n" + "Mime types: %s%n" + "Names: %s%n",
                    factory.getEngineName(), factory.getEngineVersion(), factory.getLanguageName(),
                    factory.getLanguageVersion(), factory.getExtensions(), factory.getMimeTypes(), factory.getNames());
            // 得到当前的脚本引擎

            ScriptEngine engine = factory.getScriptEngine();
            System.out.println(engine);
        }
    }


    public static void evalScriptTextFull() throws Exception{
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


    @RequestMapping("/groovy")
    private String helloGroovy(){
        Groovy groovy = new Groovy();
        Object res3 = groovy.runGroovyScriptByFile(null, "hello3.groovy", "hello", new String[] { "param3", "param4" });
        System.out.println(res3);
        return res3.toString();
    }

    public static void main(String[] args) throws Exception {
        Groovy groovy = new Groovy();
        /*groovy.getScriptEngineFactoryList();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("language", "groovy test");
        Object res = groovy.runGroovyScript("return \"Hello $language\"", params);
        */
        /*String script = "def hello(param1,param2) {return \"the params is $param1 and $param2\"}";
        Object res1 = groovy.runGroovyScript(script, "hello", new String[] { "param1", "param2" });
//        System.out.println(res);
        System.out.println(res1);*/

        /*Object res3 = groovy.runGroovyScriptByFile(null, "hello3.groovy", "hello", new String[] { "param3", "param4" });
        System.out.println(res3);*/

        /*String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(uuid);*/


        /*String script = "class Person {\n"+"def hello(param1,param2) {return \"the params is $param1 and $param2\"}"+"\n}";
        System.out.println(script);
        Object res1 = groovy.runGroovyScript(script, "hello", new String[] { "param1", "param2" });
        System.out.println(res1);*/
        evalScriptTextFull();
    }


}
