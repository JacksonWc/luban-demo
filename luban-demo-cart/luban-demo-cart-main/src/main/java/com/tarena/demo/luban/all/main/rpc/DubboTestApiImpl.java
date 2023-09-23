package com.tarena.demo.luban.all.main.rpc;

import luban.demo.cart.api.DubboTestApi;
import org.springframework.stereotype.Component;

/**
 * 步骤3
 */
@Component
public class DubboTestApiImpl implements DubboTestApi {
    @Override
    public String sayHi(String name) {
        return "hello dubbo,我是"+name;
    }
}
