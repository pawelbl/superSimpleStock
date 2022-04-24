package com.example.supersimplestockmarket.service.exchangeservice;

import java.util.Map;

import com.example.supersimplestockmarket.model.StockInfo;
import com.example.supersimplestockmarket.service.dao.InMemoryGlobalExchangeDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GlobalExchangeServiceImpl implements GlobalExchangeService {

    @Autowired
    InMemoryGlobalExchangeDao inMemoryGlobalExchangeDao;

    GlobalExchangeServiceImpl() {

    }

    @Override
    public StockInfo getStockInfo(String stockSymbol) {
        return inMemoryGlobalExchangeDao.getStock(stockSymbol);
    }

    @Override
    public void setStockInfo(StockInfo stock) {
        inMemoryGlobalExchangeDao.setStock(stock);
    }

}
