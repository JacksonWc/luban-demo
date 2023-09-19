package com.tarena.demo.luban.protocol.cart.dos;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartDO implements Serializable {

    private Integer id;
    //userName user_name
    //productCode product_code
    private String productCode;
    private Integer price;
    private Integer count;
    private String userId;

}
