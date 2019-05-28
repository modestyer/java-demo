package com.example.boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuf
 * @create 2019-05-16 22:37
 */
@RestController
public class HelloBootController {

    @Value("${person.lastName}")
    private String name;

    @RequestMapping("/hello/boot")
    public String helloBoot(){
        return "Hello"+name;
    }
}
