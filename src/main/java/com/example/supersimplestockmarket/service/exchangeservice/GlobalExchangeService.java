package com.example.supersimplestockmarket.service.exchangeservice;

import com.example.supersimplestockmarket.model.StockInfo;

public interface GlobalExchangeService {
    StockInfo getStockInfo(String stockSymbol);

    boolean setStockInfo(StockInfo stock);
}
