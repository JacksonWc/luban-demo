package com.tarena.demo.luban.all.main.controller;

import com.tarena.demo.luban.protocol.cart.param.CartAddParam;
import commons.restful.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.tarena.demo.luban.all.main.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/cart")
@Api(tags = "购物车")
public class CartController {
    // 控制层装配业务逻辑层对象
    @Autowired
    private CartService cartService;
    @PostMapping("/add")
    @ApiOperation("新增购物车中的商品")
    public JsonResult cartAdd(CartAddParam cartAddParam){
        cartService.cartAdd(cartAddParam);
        return JsonResult.ok("新增购物车完成!");
    }
}
