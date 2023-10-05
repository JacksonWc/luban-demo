package com.tarena.luban.spring.boot.config;

import com.tarena.luban.spring.boot.beans.Bean01;
import org.springframework.context.annotation.*;

/**
 * 标识这个类,不是一个普通的类
 * 而是spring可以加载的元数据配置类
 */
@Configuration
@ComponentScan(basePackages =
        {"com.tarena.luban.spring.boot.beans"} )
@Import(Demo02Conf.class)
@PropertySource("test.properties")
public class Demo01Conf {
    @Bean
    public Bean01 bean01(){
        return new Bean01();
    }
}
