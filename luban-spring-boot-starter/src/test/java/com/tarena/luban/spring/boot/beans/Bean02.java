package com.tarena.luban.spring.boot.beans;

import org.springframework.stereotype.Component;

@Component
public class Bean02 {
    public Bean02(){
        System.out.println("bean02被容器加载了");
    }
}
