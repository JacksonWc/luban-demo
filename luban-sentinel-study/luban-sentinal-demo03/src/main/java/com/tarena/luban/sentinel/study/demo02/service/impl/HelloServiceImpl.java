package com.tarena.luban.sentinel.study.demo02.service.impl;

import com.tarena.luban.sentinel.study.demo02.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {

        try {
            Thread.sleep(110);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello sentinel i am "+name;
    }
}
