package com.example.supersimplestockmarket.service.trading;

import java.util.List;

import com.example.supersimplestockmarket.model.Trade;
import com.example.supersimplestockmarket.service.dao.InMemoryDao.InMemoryDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TradingServiceImpl implements TradingService {

    @Autowired
    InMemoryDaoImpl inMemoryDaoImpl;

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
    public Trade retrieveTrade(int id) {
        return inMemoryDaoImpl.getTrade(id);
    }

    @Override
    public List<Trade> getAllTrades(int minutes) {
        return inMemoryDaoImpl.getAllTrades(minutes);
    }

}
