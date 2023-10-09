package com.tarena.demo.luban.all.main.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderCartHandler {
    public static boolean cartHandler(CartDeleteParam param, BlockException e){
        //记录日志
        log.info(
                "当前购物车:{},删除没有执行,熔断规则限制了,message:{}",
                param,
                e.getClass().getName());
        //TODO 记录失败信息到数据库,或者发送失败消息,异步处理
        return true;
    }
    public static boolean cartFallback(CartDeleteParam param, Throwable e){
        //记录日志
        log.info(
                "当前购物车:{},删除没有执行,熔断规则限制了,message:{}",
                param,
                e.getClass().getName());
        //TODO 记录失败信息到数据库,或者发送失败消息,异步处理
        return true;
    }
}
