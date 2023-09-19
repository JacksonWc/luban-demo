package com.tarena.demo.luban.protocol.stock.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "商品减库存DTO")
@Data
public class StockReduceCountParam implements Serializable {
    @ApiModelProperty(value = "订单编号",name="orderSn",example = "OS100")
    private String orderSn;
    @ApiModelProperty(value = "商品编号",name="productCode",example = "PC100")
    private String productCode;
    @ApiModelProperty(value = "减库存数",name="reduceCount",example = "5")
    private Integer reduceCount;
}








