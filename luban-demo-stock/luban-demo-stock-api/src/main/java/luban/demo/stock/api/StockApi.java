package luban.demo.stock.api;

import com.tarena.demo.luban.protocol.stock.param.StockReduceCountParam;

public interface StockApi {
    void reduceCommodityCount(StockReduceCountParam param);
}
