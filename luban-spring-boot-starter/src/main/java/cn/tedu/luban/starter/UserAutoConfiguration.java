package cn.tedu.luban.starter;

import cn.tedu.luban.starter.user.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "user",value="enable",havingValue = "true",matchIfMissing = false)
public class UserAutoConfiguration {
    @Bean(name = "user")
    //如果容器中存在User的bean对象 方法不加载了
    @ConditionalOnMissingBean(value={User.class})
    public User initUser(){
        User user=new User();
        user.setUsername("王翠花");
        return user;
    }
}
