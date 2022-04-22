package com.example.supersimplestockmarket.controller;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class SuperSimpleStockMarketController {
    Logger LOGGER = LoggerFactory.getLogger(SuperSimpleStockMarketController.class);

    @GetMapping("/") 
    public String index(){
        return "Landing page";
    }

    @GetMapping("/dividend-yield") 
    public String getDividentYield(@PathParam(value = "value") double value){
        LOGGER.info("getDivident called {}", value);
        return "dividend-yield";
    }

    @GetMapping("/pe-ratio") 
    public String getPeRatio(@PathParam(value = "value") double value){
        LOGGER.info("getPeRatio called {}", value);
        return "pe-ratio";
    }

    @GetMapping("/trades") 
    public List<Trade> getTrades(@PathParam(value = "minutes") int minutes){
        LOGGER.info("getTrades for last {} minutes", minutes);
        List<Trade> allTrades = new ArrayList<>();
        allTrades.add(new Trade(1.4, 10, TradeType.PURCHASE, new Date()));
        allTrades.add(new Trade(1.6, 3, TradeType.SALE, new Date()));
        allTrades.add(new Trade(1.0, 15, TradeType.PURCHASE, new Date()));
        return allTrades;
    }

    @GetMapping("/trades/{id}") 
    public Trade getTrade(@PathVariable(value = "id") int id){
        Trade trade = new Trade(1.2, 123, TradeType.PURCHASE, new Date());
        LOGGER.info("getTrade with id {}", id);
        return trade;
    }

    @PutMapping("/trades/{id}") 
    public String saveTrade(@PathVariable(value = "id") int id){
        LOGGER.info("saveTrade with id {}", id);
        return "savetrade";
    }

    @GetMapping(value = "/stock-info/{id}", produces = MediaType.APPLICATION_JSON_VALUE) 
    public StockInfo getStockInfo(@PathVariable(value = "id") String id){
        StockInfo stock = new StockInfo("TEA", StockType.COMMON, 0, 2.0, 123);
        LOGGER.info("getStockInfo with symbol {} stock {}", id, stock);
        return stock;
    }
    
    @PutMapping(value = "/stock-info") 
    public StockInfo setStockInfo(@RequestBody StockInfo stockSymbol){
        LOGGER.info("set StockInfo with stock {}", stockSymbol);

        return stockSymbol;
    }
}
