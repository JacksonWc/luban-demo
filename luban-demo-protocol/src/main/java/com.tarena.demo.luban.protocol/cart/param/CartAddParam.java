package com.tarena.demo.luban.protocol.cart.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
// 声明使用在Knife4j测试中的类
@ApiModel
public class CartAddParam implements Serializable {

    @ApiModelProperty(value = "商品编号",name="productCode",example = "PC100")
    private String productCode;
    @ApiModelProperty(value = "价格",name="price",example = "50")
    private Integer price;
    @ApiModelProperty(value = "数量",name="count",example = "5")
    private Integer count;
    @ApiModelProperty(value = "用户id",name="userId",example = "UU100")
    private String userId;


}
