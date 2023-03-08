package com.example.supersimplestockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import com.example.supersimplestockmarket.service.stockmarket.StockMarketServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class SuperSimpleStockMarketController {
    Logger LOGGER = LoggerFactory.getLogger(SuperSimpleStockMarketController.class);

    @Autowired
    private StockMarketServiceImpl stockMarketServiceImpl;

    @GetMapping("/dividend-yield")
    public double getDividentYield(@PathParam(value = "price") double price, @PathParam(value = "stock") String stock) {
        LOGGER.info("Calling getDivident called with price {} stock {}", price, stock);
        return stockMarketServiceImpl.calculateDividentYield(stock, price);
    }

    @GetMapping("/pe-ratio")
    public double getPeRatio(@PathParam(value = "price") double price, @PathParam(value = "stock") String stock) {
        return stockMarketServiceImpl.calculatePeRatio(stock, price);
    }

    @GetMapping("/VWSP")
    public double getVWSP(@PathParam(value = "minutes") int minutes, @PathParam(value = "stock") String stock) {
        return stockMarketServiceImpl.calculateVWSP(stock, minutes);
    }

    @GetMapping("/GBCE")
    public double getGBCE() {
        return stockMarketServiceImpl.calculateGBCE();
    }
}
