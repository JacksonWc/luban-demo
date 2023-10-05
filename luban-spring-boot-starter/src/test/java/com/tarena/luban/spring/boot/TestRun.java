package com.tarena.luban.spring.boot;

import com.tarena.luban.spring.boot.beans.Bean01;
import com.tarena.luban.spring.boot.config.Demo01Conf;
import com.tarena.luban.spring.boot.config.Demo02Conf;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestRun {
    /**
     * 提供2个方法
     * 一个方法读取xml加载spring容器
     * 另一个方法读取配置类加载spring容器
     */

    //加载xml
    @Test
    public void loadXml(){
        ClassPathXmlApplicationContext context=
                new ClassPathXmlApplicationContext(
                        "demo01.xml");
    }
    //加载配置类
    @Test
    public void loadConfig(){
        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext(
                        Demo01Conf.class);
    }
}
