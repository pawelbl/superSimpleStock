package com.example.supersimplestockmarket.service.stockmarket;

import java.util.List;
import java.util.stream.Collectors;

import com.example.supersimplestockmarket.model.StockInfo;
import com.example.supersimplestockmarket.model.StockType;
import com.example.supersimplestockmarket.model.Trade;
import com.example.supersimplestockmarket.service.exchangeservice.GlobalExchangeServiceImpl;
import com.example.supersimplestockmarket.service.math.CalculatingService;
import com.example.supersimplestockmarket.service.trading.TradingServiceImpl;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockMarketServiceImpl implements StockMarketService {

    @Autowired
    GlobalExchangeServiceImpl globalExchangeServiceImpl;

    @Autowired
    TradingServiceImpl tradingServiceImpl;

    @Override
    public double calculateDividentYield(String stockSymbol, double inputPrice) {
        if (inputPrice <= 0.0) {
            throw new RuntimeException("Price cannot be 0 or negative value");
        }

        if (stockSymbol.isEmpty()) {
            throw new RuntimeException("Stock symbol cannot be empty");
        }

        double result;
        StockInfo stockInfo = globalExchangeServiceImpl.getStockInfo(stockSymbol);
        double lastDividend;
        double fixedDividend;
        double parValue;

        if (stockInfo.getType().equals(StockType.COMMON)) {
            lastDividend = stockInfo.getLastDividend();
            lastDividend = lastDividend / 100;
            result = CalculatingService.calculateCommonDividentYield(inputPrice, lastDividend);
        } else {
            fixedDividend = stockInfo.getFixedDividend();
            parValue = stockInfo.getParValue();
            parValue = parValue / 100;
            result = CalculatingService.calculatePreferredDividentYield(inputPrice, parValue, fixedDividend);
        }

        return result;
    };

    @Override
    public double calculatePeRatio(String stockSymbol, double inputPrice) {
        if (inputPrice <= 0.0) {
            throw new RuntimeException("Price cannot be 0 or negative value");
        }

        StockInfo stockInfo = globalExchangeServiceImpl.getStockInfo(stockSymbol);
        double lastDividend = stockInfo.getLastDividend();

        return CalculatingService.calculatePeRatio(inputPrice, lastDividend);

    };

    // Value Weighted Stock Price
    @Override
    public double calculateVWSP(String stockSymbol, int minutes) {
        if (stockSymbol.isEmpty()) {
            throw new RuntimeException("Stock symbol cannot be empty");
        }

        if (minutes <= 0) {
            throw new RuntimeException("Time span cannot be 0 or negative value");
        }

        List<Trade> trades = tradingServiceImpl.getAllTrades(stockSymbol, minutes);
        List<Pair<Double, Integer>> inputTrades = trades.stream().map(t -> Pair.with(t.getPrice(), t.getQuantity()))
                .collect(Collectors.toList());
        return CalculatingService.calculateVWSP(inputTrades);
    };

    // Geometric Mean
    @Override
    public double calculateGBCE() {
        List<Trade> trades = tradingServiceImpl.getAllTrades();
        List<Double> inputTrades = trades.stream().map(t -> t.getPrice())
                .collect(Collectors.toList());
        return CalculatingService.calculateGBCE(inputTrades);
    };
}
