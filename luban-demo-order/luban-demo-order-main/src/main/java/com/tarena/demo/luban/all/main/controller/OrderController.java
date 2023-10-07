package com.tarena.demo.luban.all.main.controller;



import com.tarena.demo.luban.protocol.order.param.OrderAddParam;
import commons.restful.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.tarena.demo.luban.all.main.service.OrderService;
import luban.demo.cart.api.DubboTestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/order")
@Api(tags = "订单")
public class OrderController {
    @Autowired
    private OrderService orderService;
    //步骤5
//    @Autowired
//    private DubboTestApi dubboTestApi;

    @PostMapping("/add")
    @ApiOperation("新增订单的功能")
    public JsonResult addOrder(OrderAddParam orderAddParam){
       // System.out.println(dubboTestApi.sayHi("王翠花"));
        orderService.addOrder(orderAddParam);
        return JsonResult.ok("新增订单完成!");
    }

}
