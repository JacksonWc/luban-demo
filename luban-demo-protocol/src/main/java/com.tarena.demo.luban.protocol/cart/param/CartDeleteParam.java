package com.tarena.demo.luban.protocol.cart.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartDeleteParam implements Serializable {
    private String userId;
    private String productCode;
}
