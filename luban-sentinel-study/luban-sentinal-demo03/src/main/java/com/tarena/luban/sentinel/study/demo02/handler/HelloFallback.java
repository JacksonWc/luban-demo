package com.tarena.luban.sentinel.study.demo02.handler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloFallback {
    public static String sayHelloFallback(String name,Throwable e){
        log.info("出现业务其他异常",e);
        return "系统出现其他异常,sorry"+name;
    }
}
