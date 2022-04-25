package com.example.supersimplestockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.websocket.server.PathParam;

import com.example.supersimplestockmarket.model.Trade;
import com.example.supersimplestockmarket.service.trading.TradingServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class TradesController {
    Logger LOGGER = LoggerFactory.getLogger(SuperSimpleStockMarketController.class);

    @Autowired
    private TradingServiceImpl tradingServiceImpl;

    @GetMapping("/trades")
    public List<Trade> getTrades(@PathParam(value = "minutes") int minutes, @PathParam(value = "stock") String stock) {
        return tradingServiceImpl.getAllTrades(stock, minutes);

    }

    @GetMapping("/trades/{id}")
    public Trade getTrade(@PathVariable(value = "id") int id) {
        return tradingServiceImpl.getTrade(id);
    }

    @PutMapping("/trades")
    public int saveTrade(@RequestBody Trade newTrade) {
        return tradingServiceImpl.saveTrade(newTrade);
    }
}
