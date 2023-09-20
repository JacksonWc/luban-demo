package com.tarena.demo.luban.all.main.mapper;

import com.tarena.demo.luban.protocol.stock.dos.StockDO;
import com.tarena.demo.luban.protocol.stock.dos.StockLogDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMapper {
    // 修改(减少)指定商品库存数的方法
    @Update("update stock_tbl set stock=stock-#{stock} where " +
            " product_code=#{productCode} and stock>=#{stock}")
    int updateStockCount(StockDO stockDO);

    @Insert("insert into stock_log_tbl (id,order_sn,product_code,reduce_stock) values " +
            "(null,#{orderSn},#{productCode},#{reduceCount})")
    int insertStockLog(StockLogDO stockLogDO);
}
