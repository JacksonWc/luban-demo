package com.tarena.demo.luban.all.main.service;

import com.tarena.demo.luban.protocol.cart.param.CartAddParam;
import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;

public interface CartService {
    void cartAdd(CartAddParam param);

    void deleteCart(CartDeleteParam param);
}
