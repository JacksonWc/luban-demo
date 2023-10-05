package com.tarena.luban.spring.boot.config;

import com.tarena.luban.spring.boot.beans.Bean03;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Demo02Conf {
    @Bean
    public Bean03 bean03(){
        return new Bean03();
    }
}
