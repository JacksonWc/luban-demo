package com.tarena.demo.luban.protocol.order.dos;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDO implements Serializable {

    private Integer id;
    private String orderSn;
    private String userId;
    private String productCode;
    private Integer count;
    private Integer totalMoney;

}
