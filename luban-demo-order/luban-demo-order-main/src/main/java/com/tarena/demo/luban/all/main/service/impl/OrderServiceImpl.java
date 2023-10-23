package com.tarena.demo.luban.all.main.service.impl;



import com.tarena.demo.luban.protocol.order.dos.OrderDO;
import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import com.tarena.demo.luban.protocol.stock.param.StockReduceCountParam;
import com.tarena.demo.luban.all.main.mapper.OrderMapper;
import com.tarena.demo.luban.all.main.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import luban.demo.cart.api.CartApi;
import luban.demo.stock.api.StockApi;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
//    @Autowired(required = false)
//    private CartApi consumerCartApi;
    @Autowired
    private StockApi stockApiImpl;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartRpcComponent cartRpcComponent;



    @Override public void addOrder(OrderAddParam param) {
        //利用订单编号orderSn到数据库查询
        int result=orderMapper.selectCountByOrderSn(param.getOrderSn());
        if (result>0){
            //下面的方法不在执行
            log.info("当前订单:{},已经存在了",param.getOrderSn());
            return;
        }


        // 减库存
        StockReduceCountParam stockReduceCountParam=new StockReduceCountParam();
        stockReduceCountParam.setReduceCount(param.getCount());
        stockReduceCountParam.setProductCode(param.getProductCode());
        stockReduceCountParam.setOrderSn(param.getOrderSn());
        stockApiImpl.reduceCommodityCount(stockReduceCountParam);
        //增订单
        OrderDO orderDO=new OrderDO();
        BeanUtils.copyProperties(param,orderDO);
        orderMapper.insertOrder(orderDO);

        //远程调用购物车删除 虽然底层远程调用可能失败,但是 调用者总可以拿到一个可用的数据
        cartRpcComponent.deleteCart(param);

        //在当前orderServiceImpl中 定义一个单独的方法 将删除购物车单独封装
        //不能触发 sentinel组件的代理逻辑

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
