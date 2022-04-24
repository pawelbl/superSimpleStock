package com.example.supersimplestockmarket.service.dao;

import java.util.HashMap;
import java.util.Map;

import com.example.supersimplestockmarket.model.StockInfo;
import com.example.supersimplestockmarket.model.StockType;

import org.springframework.stereotype.Component;

@Component
public class InMemoryGlobalExchangeDao {
    private Map<String, StockInfo> allStock;

    InMemoryGlobalExchangeDao() {
        allStock = new HashMap<>();
        allStock.put("TEA", new StockInfo("TEA", StockType.COMMON, 0, 0, 100));
        allStock.put("POP", new StockInfo("POP", StockType.COMMON, 8, 0, 100));
        allStock.put("ALE", new StockInfo("ALE", StockType.COMMON, 23, 0, 60));
        allStock.put("GIN", new StockInfo("GIN", StockType.PREFERRED, 8, 0.02, 100));
        allStock.put("JOE", new StockInfo("JOE", StockType.COMMON, 13, 0, 250));
    }

    public StockInfo getStock(String symbol) {
        return allStock.get(symbol);
    }

    public void setStock(StockInfo stockInfo) {
        allStock.put(stockInfo.getSymbol(), stockInfo);
    }
}
