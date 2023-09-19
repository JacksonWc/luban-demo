package com.tarena.demo.luban.protocol.stock.dos;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockDO implements Serializable {

    private Integer id;
    private String productCode;
    private Integer stock;
}






