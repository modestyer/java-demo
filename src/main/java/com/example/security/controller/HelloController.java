package com.example.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/2/25
 **/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello spring Security";
    }
}
