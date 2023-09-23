package com.tarena.demo.luban.all.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.tarena.demo.luban.all.main.mapper")
//步骤2
@ImportResource("applicationContext.xml")
public class CartMain {
    public static void main(String[] args) {
        SpringApplication.run(CartMain.class,args);
    }
}
