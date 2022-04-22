package com.example.supersimplestockmarket.service;

import com.example.supersimplestockmarket.model.StockInfo;

public interface GlobalExchangeService {
    StockInfo getStockInfo(String stockSymbol);
    void setStockInfo(StockInfo stock);
}
