package com.tarena.luban.spring.boot.config.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * 根据指定的bean对象存在或不存在判断条件是否满足的
 */
//案例1 Bean02 对象在容器存在 条件满足的 所以配置扫描到之后会加载使用
//@ConditionalOnBean(type={"com.tarena.luban.spring.boot.beans.Bean02"})
//案例2 Bean07 根本不存在 所以bean也不存在 条件不满足
@ConditionalOnBean(type={"com.tarena.luban.spring.boot.beans.Bean07"})
//案例3 Bean07 不存在bean 条件满足
//@ConditionalOnMissingBean(type={"com.tarena.luban.spring.boot.beans.Bean07"})
public class ConditionalDemo02Conf {
    public ConditionalDemo02Conf() {
        System.out.println("条件配置类ConditionalDemo02Conf条件满足");
    }
}
