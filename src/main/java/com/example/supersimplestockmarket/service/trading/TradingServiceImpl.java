package com.example.supersimplestockmarket.service.trading;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.example.supersimplestockmarket.model.Trade;
import com.example.supersimplestockmarket.service.dao.InMemoryTradesDao.InMemoryTradesDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TradingServiceImpl implements TradingService {

    @Autowired
    InMemoryTradesDaoImpl inMemoryDaoImpl;

    @Override
    public int saveTrade(Trade newTrade) {

        if (newTrade.getPrice() <= 0.0) {
            throw new RuntimeException("Price cannot be 0 or negative");
        }

        if (newTrade.getQuantity() <= 0.0) {
            throw new RuntimeException("Quantity cannot be 0 or negative");
        }

        if (!StringUtils.hasText(newTrade.getStockSymbol())) {
            throw new RuntimeException("Stock symbol missing");
        }

        if (newTrade.getTimestamp() == null) {
            throw new RuntimeException("Trade Date missing");
        }

        if (newTrade.getTradeType() == null) {
            throw new RuntimeException("Trade type missing");
        }
        return inMemoryDaoImpl.saveTrade(newTrade);
    }

    @Override
    public Trade getTrade(int id) {
        return inMemoryDaoImpl.getTrade(id);
    }

    @Override
    public List<Trade> getAllTrades(String stockSymbol, int minutes) {
        Instant to = Instant.now();
        Instant from = to.minus(15, ChronoUnit.MINUTES);
        return inMemoryDaoImpl.getAllTrades(stockSymbol, from, to);
    }

    @Override
    public List<Trade> getAllTrades() {
        return inMemoryDaoImpl.getAllTrades();
    }

}
