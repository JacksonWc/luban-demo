package com.tarena.demo.luban.all.main.rpc;

import com.tarena.demo.luban.all.main.controller.StockController;
import com.tarena.demo.luban.protocol.stock.param.StockReduceCountParam;
import luban.demo.stock.api.StockApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockApiImpl implements StockApi {
    @Autowired
    private StockController stockController;

    @Override
    public void reduceCommodityCount(StockReduceCountParam param) {
        stockController.reduceCommodityCount(param);
    }
}
