package com.tarena.demo.luban.protocol.order.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "新增订单的DTO")
@Data
public class OrderAddParam implements Serializable {
    @ApiModelProperty(value = "用户id",name="userId",example = "UU100")
    private String userId;
    @ApiModelProperty(value = "商品编号",name="productCode",example = "PC100")
    private String productCode;
    @ApiModelProperty(value = "购买数量",name="count",example = "5")
    private Integer count;
    @ApiModelProperty(value = "总金额",name="totalMoney",example = "500")
    private Integer totalMoney;
    @ApiModelProperty(value = "订单编号",name="orderSn",example = "0")
    private String orderSn;

}
