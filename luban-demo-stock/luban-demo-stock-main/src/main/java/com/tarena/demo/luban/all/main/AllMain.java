package com.tarena.demo.luban.all.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tarena.demo.luban.all.com.tarena.demo.luban.all.main.mapper")
public class AllMain {
    public static void main(String[] args) {
        SpringApplication.run(AllMain.class,args);
    }
}
