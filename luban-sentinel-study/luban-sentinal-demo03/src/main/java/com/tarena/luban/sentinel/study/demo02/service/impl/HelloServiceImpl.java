package com.tarena.luban.sentinel.study.demo02.service.impl;

import com.tarena.luban.sentinel.study.demo02.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello sentinel i am "+name;
    }
}
