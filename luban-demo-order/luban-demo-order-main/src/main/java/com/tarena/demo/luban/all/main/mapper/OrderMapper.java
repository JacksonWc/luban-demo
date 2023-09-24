package com.tarena.demo.luban.all.main.mapper;

import com.tarena.demo.luban.protocol.order.dos.OrderDO;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    // 新增订单的方法
    @Insert("insert into order_tbl(user_id,order_sn,product_code,count,total_money) values" +
            "(#{userId},#{orderSn},#{productCode},#{count},#{totalMoney})")
    int insertOrder(OrderDO order);
}







