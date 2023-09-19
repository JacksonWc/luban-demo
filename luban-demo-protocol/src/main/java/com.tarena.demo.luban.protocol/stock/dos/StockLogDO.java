package com.tarena.demo.luban.protocol.stock.dos;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockLogDO implements Serializable {
    private Integer id;
    private String orderSn;
    //数据库的字段对应是reduce——stock
    private Integer reduceCount;
    private String productCode;
}
