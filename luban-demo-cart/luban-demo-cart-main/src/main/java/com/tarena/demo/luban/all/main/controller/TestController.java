package com.tarena.demo.luban.all.main.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/lb")
    public String lb(){
        return "i am from "+port;
    }
}
