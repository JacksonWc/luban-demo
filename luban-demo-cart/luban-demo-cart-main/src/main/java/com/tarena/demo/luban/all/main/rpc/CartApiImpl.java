package com.tarena.demo.luban.all.main.rpc;

import com.tarena.demo.luban.all.main.service.CartService;
import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;
import luban.demo.cart.api.CartApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CartApiImpl implements CartApi {
    @Autowired
    private CartService cartService;


    @Override
    public void deleteCart(CartDeleteParam param) {
        cartService.deleteCart(param);
    }
}
