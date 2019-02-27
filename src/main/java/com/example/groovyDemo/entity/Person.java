package com.example.groovyDemo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/2/26
 **/
@Data
public class Person {

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "年龄")
    private String age;

    private int id;

    private String var1;

    private String var2;

    private String passWord;
}
