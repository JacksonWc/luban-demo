package com.tarena.luban.spring.boot.config.conditional;

import com.tarena.luban.spring.boot.beans.Bean03;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**在条件注解中指定的类如果在当前依赖环境存在 条件则满足
如果满足 配置类才会被加载,不满足则不加载*/
//案例1 条件满足 所以加载
//@ConditionalOnClass(name={"com.tarena.luban.spring.boot.beans.Bean01"})
//案例2 条件不满足 所以扫描到 也不会加载这个配置类
//@ConditionalOnClass(name={"com.tarena.luban.spring.boot.beans.Bean07"})
//存在类则不满足 条件 不存在类则满足条件
@ConditionalOnMissingClass(value={"com.tarena.luban.spring.boot.beans.Bean02"})
public class ConditionalDemo01Conf {
    public ConditionalDemo01Conf() {
        System.out.println("条件配置类ConditionalDemo01Conf条件满足");
    }
}
