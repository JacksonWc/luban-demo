package com.tarena.luban.sentinel.study.demo02.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloBlockHandler {
    //sentinel 熔断限流的降级逻辑
    public static String sayHelloBlock(String name, BlockException e){
        log.info("出现异常",e);
        return "出现了熔断限流异常"+name;
    }
}
