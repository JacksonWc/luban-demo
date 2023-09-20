package com.tarena.demo.luban.all.main.service.impl;

import com.tarena.demo.luban.protocol.stock.dos.StockDO;
import com.tarena.demo.luban.protocol.stock.dos.StockLogDO;
import com.tarena.demo.luban.protocol.stock.param.StockReduceCountParam;
import com.tarena.demo.luban.all.main.mapper.StockMapper;
import com.tarena.demo.luban.all.main.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockMapper stockMapper;
    @Override public void reduceCommodityCount(StockReduceCountParam param) {
        //执行减库存 在当前库存大于减库存的数量时，才允许减
        StockDO stockDO=new StockDO();
        stockDO.setProductCode(param.getProductCode());
        stockDO.setStock(param.getReduceCount());
        stockMapper.updateStockCount(stockDO);
        //记录减库存日志 方便后续扩展换库存--订单超时未支付，取消
        StockLogDO stockLogDO=new StockLogDO();
        stockLogDO.setOrderSn(param.getOrderSn());
        stockLogDO.setReduceCount(param.getReduceCount());
        stockLogDO.setProductCode(param.getProductCode());
        stockMapper.insertStockLog(stockLogDO);
    }
}
