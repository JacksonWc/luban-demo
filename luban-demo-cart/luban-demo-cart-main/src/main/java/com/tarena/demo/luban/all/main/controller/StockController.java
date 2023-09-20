package com.tarena.demo.luban.all.main.controller;


import com.tarena.demo.luban.protocol.stock.param.StockReduceCountParam;
import commons.restful.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.tarena.demo.luban.all.main.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/stock")
@Api(tags = "库存")
public class StockController {
    @Autowired
    private StockService stockService;
    @GetMapping("/reduce/count")
    @ApiOperation("减少指定商品的库存数")
    public JsonResult reduceCommodityCount(StockReduceCountParam stockReduceCountParam){
        stockService.reduceCommodityCount(stockReduceCountParam);
        return JsonResult.ok("库存减少完成!");
    }
}
