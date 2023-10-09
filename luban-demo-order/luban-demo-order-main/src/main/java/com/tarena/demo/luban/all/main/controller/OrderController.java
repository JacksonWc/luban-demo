package com.tarena.demo.luban.all.main.controller;



import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import commons.restful.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.tarena.demo.luban.all.main.service.OrderService;
import luban.demo.cart.api.DubboTestApi;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/order")
@Api(tags = "订单")
public class OrderController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @Autowired
    private OrderService orderService;
    //步骤5
//    @Autowired
//    private DubboTestApi dubboTestApi;

    @PostMapping("/add")
    @ApiOperation("新增订单的功能")
    public JsonResult addOrder(OrderAddParam orderAddParam){
       // System.out.println(dubboTestApi.sayHi("王翠花"));
        //orderService.addOrder(orderAddParam);
        //发送一条消息 目的实现业务层代码的调用
        //新增订单 orderAddParam作为payLoad发送 需要确定这个类型是否实现了序列化接口
        //准备一个消息对象
        Message<OrderAddParam> message=
                MessageBuilder.withPayload(orderAddParam).build();
        //发送消息 同步发送
        //***:** 这种格式的desitination 底层翻译的时候 解析成 topic:tag
        rocketMQTemplate.syncSend("luban-demo-order-topic:addOrder",message);



        return JsonResult.ok("新增订单完成!");
    }

}
