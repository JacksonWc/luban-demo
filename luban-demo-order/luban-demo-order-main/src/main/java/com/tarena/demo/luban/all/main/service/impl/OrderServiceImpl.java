package com.tarena.demo.luban.all.main.service.impl;


import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;
import com.tarena.demo.luban.protocol.order.dos.OrderDO;
import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import com.tarena.demo.luban.protocol.stock.param.StockReduceCountParam;
import com.tarena.demo.luban.all.main.mapper.OrderMapper;
import com.tarena.demo.luban.all.main.service.OrderService;
import luban.demo.cart.api.CartApi;
import luban.demo.stock.api.StockApi;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired(required = false)
    private CartApi consumerCartApi;
    @Autowired
    private StockApi stockApi;
    @Autowired
    private OrderMapper orderMapper;
    @Override public void addOrder(OrderAddParam param) {
        // 减库存
        StockReduceCountParam stockReduceCountParam=new StockReduceCountParam();
        stockReduceCountParam.setReduceCount(param.getCount());
        stockReduceCountParam.setProductCode(param.getProductCode());
        stockReduceCountParam.setOrderSn(param.getOrderSn());
        stockApi.reduceCommodityCount(stockReduceCountParam);
        //增订单
        OrderDO orderDO=new OrderDO();
        BeanUtils.copyProperties(param,orderDO);
        orderMapper.insertOrder(orderDO);
        //删除购物车
        CartDeleteParam cartDeleteParam=new CartDeleteParam();
        cartDeleteParam.setUserId(param.getUserId());
        cartDeleteParam.setProductCode(param.getProductCode());
        //cartService.deleteCart(cartDeleteParam);
        consumerCartApi.deleteCart(cartDeleteParam);

        /*this.deleteCart(param);*/
        //删除购物车
        /*Entry entry=null;
        try{
            entry= SphU.entry("orderAdd");
        CartDeleteParam cartDeleteParam=new CartDeleteParam();
        cartDeleteParam.setUserId(param.getUserId());
        cartDeleteParam.setProductCode(param.getProductCode());
        cartApi.deleteCart(cartDeleteParam);
        }catch (BlockException e){
            //捕获异常 记录异常数 如果不记录 无法触发异常数熔断机制
            entry.setBlockError(e);
        }catch(Throwable e){
            //捕获异常 记录异常数 如果不记录 无法触发异常数熔断机制
            entry.setError(e);
        }finally {
            if (entry!=null) entry.exit();
        }*/
        //上述这种方式 定义资源 侵入性强,减少侵入
        //使用注解
    }
}
