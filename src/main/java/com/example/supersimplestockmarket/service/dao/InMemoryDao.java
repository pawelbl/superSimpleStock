package com.example.supersimplestockmarket.service.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.supersimplestockmarket.model.Trade;
import com.example.supersimplestockmarket.model.TradeType;

import org.springframework.stereotype.Component;

public interface InMemoryDao {
    public int saveTrade(Trade newTrade);

    public Trade getTrade(int id);

    public List<Trade> getAllTrades(int minutes);

    @Component
    public class InMemoryDaoImpl implements InMemoryDao {
        List<Trade> allTrades;

        InMemoryDaoImpl() {
            allTrades = new ArrayList<Trade>();

            allTrades.add(new Trade("TEA", 1.4, 100, TradeType.PURCHASE, new Date()));
            allTrades.add(new Trade("TEA", 0.4, 50, TradeType.SALE, new Date()));
            allTrades.add(new Trade("POP", 3.1, 40, TradeType.SALE, new Date()));
            allTrades.add(new Trade("ALE", 1.3, 170, TradeType.PURCHASE, new Date()));
            allTrades.add(new Trade("GIN", 5.4, 340, TradeType.PURCHASE, new Date()));
            allTrades.add(new Trade("JOE", 1.7, 200, TradeType.PURCHASE, new Date()));
            allTrades.add(new Trade("JOE", 1.9, 120, TradeType.SALE, new Date()));
            allTrades.add(new Trade("ALE", 1.2, 100, TradeType.SALE, new Date()));
            allTrades.add(new Trade("GIN", 1.2, 100, TradeType.PURCHASE, new Date()));
        }

        @Override
        public int saveTrade(Trade newTrade) {
            allTrades.add(newTrade);
            return allTrades.size() - 1;
        }

        @Override
        public Trade getTrade(int id) {
            return allTrades.get(id);
        }

        @Override
        public List<Trade> getAllTrades(int minutes) {
            //TBD filter by time
            return allTrades;
        }

    }
}
