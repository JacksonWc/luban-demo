package com.tarena.demo.luban.all.main.service.impl;

import com.tarena.demo.luban.protocol.cart.dos.CartDO;
import com.tarena.demo.luban.protocol.cart.param.CartAddParam;
import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;

import com.tarena.demo.luban.all.main.mapper.CartMapper;
import com.tarena.demo.luban.all.main.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Override public void cartAdd(CartAddParam param) {
        CartDO cartDO=new CartDO();
        BeanUtils.copyProperties(param,cartDO);
        cartMapper.insertCart(cartDO);
    }

    @Override public void deleteCart(CartDeleteParam param) {
        CartDO cartDO=new CartDO();
        BeanUtils.copyProperties(param,cartDO);
        cartMapper.deleteCart(cartDO);
    }
}
