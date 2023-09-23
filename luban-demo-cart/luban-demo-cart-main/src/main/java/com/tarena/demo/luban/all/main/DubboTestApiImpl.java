package com.tarena.demo.luban.all.main;

import luban.demo.cart.api.DubboTestApi;

/**
 * 步骤3
 */

public class DubboTestApiImpl implements DubboTestApi {
    @Override
    public String sayHi(String name) {
        return "hello dubbo,我是"+name;
    }
}
