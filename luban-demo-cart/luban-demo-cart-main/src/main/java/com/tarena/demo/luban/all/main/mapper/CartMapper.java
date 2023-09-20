package com.tarena.demo.luban.all.main.mapper;

import com.tarena.demo.luban.protocol.cart.dos.CartDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface CartMapper {

    // 新增购物车的方法
    @Insert("insert into cart_tbl(product_code,price,count,user_id) values" +
            "(#{productCode},#{price},#{count},#{userId})")
    int insertCart(CartDO cart);

    // 删除购物车的方法
    @Delete("delete from cart_tbl where user_id=#{userId} and " +
                                        "product_code=#{productCode}")
    int deleteCart(CartDO cartDO);
}









