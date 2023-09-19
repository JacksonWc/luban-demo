package com.tarena.luban.log.springboot.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * springboot应用核心注解
 */
@SpringBootApplication
public class LogSpringBoot {
    //获取日志对象
    private static final Logger logger=
            LoggerFactory.getLogger(LogSpringBoot.class);
    //args是什么？ jvm参数
    public static void main(String[] args) {
        //运行spring应用
        SpringApplication.run(LogSpringBoot.class,args);
        logger.trace("trace");
        logger.debug("debug");
        //打印一些日志
        logger.info("获取jvm 参数："+Arrays.toString(args));
    }
}
