package com.tarena.demo.luban.all.main.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;
import com.tarena.demo.luban.protocol.order.param.OrderAddParam;

import lombok.extern.slf4j.Slf4j;
import luban.demo.cart.api.CartApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 只负责包装 定义的资源方法
 * 目前需要包装的是 购物车删除
 */
@Component
@Slf4j
public class CartRpcComponent {
    public boolean cartHandler(OrderAddParam param, BlockException e){
        //记录日志
        log.info(
                "当前购物车:{},删除没有执行,熔断规则限制了,message:{}",
                param,
                e.getClass().getName());
        //TODO 记录失败信息到数据库,或者发送失败消息,异步处理
        return true;
    }
    public boolean cartFallback(OrderAddParam param, Throwable e){
        //记录日志
        log.info(
                "当前购物车:{},删除没有执行,熔断规则限制了,message:{}",
                param,
                e.getClass().getName());
        //TODO 记录失败信息到数据库,或者发送失败消息,异步处理
        return true;
    }
    @Autowired
    private CartApi cartApi;
    //sentinel资源方法
    @SentinelResource(
            value="orderAdd",
            blockHandler = "cartHandler",
            fallback = "cartFallback")
    public boolean deleteCart(OrderAddParam param){
        CartDeleteParam cartDeleteParam=new CartDeleteParam();
        cartDeleteParam.setUserId(param.getUserId());
        cartDeleteParam.setProductCode(param.getProductCode());
        cartApi.deleteCart(cartDeleteParam);
        return true;
    }
}
