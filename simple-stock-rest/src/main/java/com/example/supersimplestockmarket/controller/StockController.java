package com.example.supersimplestockmarket.controller;

import com.example.supersimplestockmarket.model.StockInfo;
import com.example.supersimplestockmarket.service.exchangeservice.GlobalExchangeServiceImpl;

import org.springframework.http.MediaType;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
    
    Logger LOGGER = LoggerFactory.getLogger(SuperSimpleStockMarketController.class);

    @Autowired
    GlobalExchangeServiceImpl globalExchangeServiceImpl;

    
    @GetMapping(value = "/stock-info/{symbol}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StockInfo getStockInfo(@PathVariable(value = "symbol") String symbol) {
        return globalExchangeServiceImpl.getStockInfo(symbol);
    }

    @PutMapping(value = "/stock-info")
    public boolean setStockInfo(@RequestBody StockInfo stock) {
        return globalExchangeServiceImpl.setStockInfo(stock);

    }
}
