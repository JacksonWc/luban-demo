package api;

import com.tarena.demo.luban.protocol.stock.param.StockReduceCountParam;

public interface StockApi {
    // 减少库存数的业务逻辑层方法
    void reduceCommodityCount(StockReduceCountParam stockReduceCountParam);
}

