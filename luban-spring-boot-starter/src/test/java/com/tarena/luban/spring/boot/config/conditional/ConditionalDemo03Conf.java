package com.tarena.luban.spring.boot.config.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
//案例1 判断 当前环境变量是否存在一个user.email=123的数据 不存在,或者不等于123 条件都不满足
//@ConditionalOnProperty(prefix="user",value="email",havingValue ="123")
//案例2 test.properties user.email=123 所以条件不满足
//@ConditionalOnProperty(prefix="user",value="email",havingValue ="1234")
//案例3 test.properties user.email不存在 直接通过matchIfMissing 返回条件满足的结果
@ConditionalOnProperty(prefix="user",value="email",havingValue ="1234",matchIfMissing = true)
public class ConditionalDemo03Conf {
    public ConditionalDemo03Conf() {
        System.out.println("条件配置类ConditionalDemo03Conf条件满足");
    }
}
