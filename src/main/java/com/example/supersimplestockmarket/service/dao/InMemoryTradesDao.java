package com.example.supersimplestockmarket.service.dao;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.supersimplestockmarket.model.Trade;
import com.example.supersimplestockmarket.model.TradeType;

import org.springframework.stereotype.Component;

public interface InMemoryTradesDao {
    public int saveTrade(Trade newTrade);

    public Trade getTrade(int id);

    public List<Trade> getAllTrades(String stockSymbol, Instant from, Instant to);

    public List<Trade> getAllTrades();

    @Component
    public class InMemoryTradesDaoImpl implements InMemoryTradesDao {
        List<Trade> allTrades;

        InMemoryTradesDaoImpl() {
            allTrades = new ArrayList<Trade>();

            allTrades.add(new Trade("TEA", 1.4, 100, TradeType.PURCHASE, Instant.now()));
            allTrades.add(new Trade("TEA", 2.4, 50, TradeType.SALE, Instant.now()));
            allTrades.add(new Trade("TEA", 0.6, 50, TradeType.PURCHASE, Instant.now()));
            allTrades.add(new Trade("TEA", 0.2, 50, TradeType.SALE, Instant.now()));
            allTrades.add(new Trade("TEA", 0.8, 50, TradeType.SALE, Instant.now()));
            allTrades.add(new Trade("TEA", 0.7, 50, TradeType.SALE, Instant.now()));
            allTrades.add(new Trade("TEA", 0.6, 50, TradeType.SALE, Instant.now()));
            allTrades.add(new Trade("TEA", 7.4, 50, TradeType.PURCHASE, Instant.now()));
            allTrades.add(new Trade("TEA", 8.4, 50, TradeType.PURCHASE, Instant.now().minus(2,ChronoUnit.HOURS)));
            allTrades.add(new Trade("TEA", 3.4, 50, TradeType.SALE, Instant.now().minus(1,ChronoUnit.HOURS)));
            allTrades.add(new Trade("POP", 3.1, 40, TradeType.PURCHASE, Instant.now()));
            allTrades.add(new Trade("POP", 3.1, 40, TradeType.SALE, Instant.now()));
            allTrades.add(new Trade("ALE", 1.3, 170, TradeType.PURCHASE, Instant.now()));
            allTrades.add(new Trade("ALE", 1.2, 100, TradeType.SALE, Instant.now()));
            allTrades.add(new Trade("ALE", 1.3, 170, TradeType.SALE, Instant.now()));
            allTrades.add(new Trade("GIN", 1.2, 100, TradeType.PURCHASE, Instant.now()));
            allTrades.add(new Trade("GIN", 5.4, 340, TradeType.PURCHASE, Instant.now()));
            allTrades.add(new Trade("JOE", 1.7, 200, TradeType.PURCHASE, Instant.now()));
            allTrades.add(new Trade("JOE", 1.9, 120, TradeType.SALE, Instant.now()));
        }

        @Override
        public int saveTrade(Trade newTrade) {
            allTrades.add(newTrade);
            return allTrades.size() - 1;
        }

        @Override
        public Trade getTrade(int id) {
            try {
                return allTrades.get(id);
            } catch (IndexOutOfBoundsException e) {
                throw new RuntimeException("Trade with id " + id + " not found");
            }
        }

        @Override
        public List<Trade> getAllTrades(String stockSymbol, Instant from, Instant to) {
            return allTrades.stream()
                    .filter(t -> stockSymbol.equals(t.getStockSymbol()) && from.isBefore(t.getTimestamp()) && to.isAfter(t.getTimestamp()))
                    .collect(Collectors.toList());
        }

        @Override
        public List<Trade> getAllTrades() {
            return allTrades;
        }
    }
}
