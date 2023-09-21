package com.tarena.demo.luban.all.main.service.impl;


import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;
import com.tarena.demo.luban.protocol.order.dos.OrderDO;
import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import com.tarena.demo.luban.protocol.stock.param.StockReduceCountParam;
import com.tarena.demo.luban.all.main.mapper.OrderMapper;
import com.tarena.demo.luban.all.main.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
//    @Autowired
//    private CartService cartService;
//    @Autowired
//    private StockService stockService;
    @Autowired
    private OrderMapper orderMapper;
    @Override public void addOrder(OrderAddParam param) {
        // 减库存
        StockReduceCountParam stockReduceCountParam=new StockReduceCountParam();
        stockReduceCountParam.setReduceCount(param.getCount());
        stockReduceCountParam.setProductCode(param.getProductCode());
        stockReduceCountParam.setOrderSn(param.getOrderSn());
       // stockService.reduceCommodityCount(stockReduceCountParam);
        //增订单
        OrderDO orderDO=new OrderDO();
        BeanUtils.copyProperties(param,orderDO);
        orderMapper.insertOrder(orderDO);
        //删除购物车
        CartDeleteParam cartDeleteParam=new CartDeleteParam();
        cartDeleteParam.setUserId(param.getUserId());
        cartDeleteParam.setProductCode(param.getProductCode());
        //cartService.deleteCart(cartDeleteParam);
    }
}
