package com.example.supersimplestockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import com.example.supersimplestockmarket.model.StockInfo;
import com.example.supersimplestockmarket.model.StockType;
import com.example.supersimplestockmarket.model.Trade;
import com.example.supersimplestockmarket.model.TradeType;
import com.example.supersimplestockmarket.service.stockmarket.StockMarketServiceImpl;
import com.example.supersimplestockmarket.service.trading.TradingServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class SuperSimpleStockMarketController {
    Logger LOGGER = LoggerFactory.getLogger(SuperSimpleStockMarketController.class);

    @Autowired
    StockMarketServiceImpl stockMarketServiceImpl;

    @Autowired
    TradingServiceImpl tradingServiceImpl;

    @GetMapping("/")
    public String index() {
        return "Landing page";
    }

    @GetMapping("/dividend-yield")
    public double getDividentYield(@PathParam(value = "value") double value) {
        LOGGER.info("Calling getDivident called {}", value);
        return stockMarketServiceImpl.calculateDividentYield(value);
    }

    @GetMapping("/pe-ratio")
    public String getPeRatio(@PathParam(value = "value") double value) {
        LOGGER.info("getPeRatio called {}", value);
        return "pe-ratio";
    }

    @GetMapping("/trades")
    public List<Trade> getTrades(@PathParam(value = "minutes") int minutes) {
        return tradingServiceImpl.getAllTrades(minutes);

    }

    @GetMapping("/trades/{id}")
    public Trade getTrade(@PathVariable(value = "id") int id) {
        return tradingServiceImpl.retrieveTrade(id);
    }

    @PutMapping("/trades")
    public int saveTrade(@PathVariable(value = "id") int id, @RequestBody Trade newTrade) {
        return tradingServiceImpl.saveTrade(newTrade);
    }

    @GetMapping(value = "/stock-info/{symbol}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StockInfo getStockInfo(@PathVariable(value = "symbol") String symbol) {
        StockInfo stock = new StockInfo("TEA", StockType.COMMON, 0, 2.0, 123);
        LOGGER.info("getStockInfo with symbol {} stock {}", symbol, stock);
        return stock;
    }

    @PutMapping(value = "/stock-info")
    public StockInfo setStockInfo(@RequestBody StockInfo stockSymbol) {
        LOGGER.info("set StockInfo with stock {}", stockSymbol);

        return stockSymbol;
    }
}
