package com.example.supersimplestockmarket.service.trading;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.example.supersimplestockmarket.model.Trade;
import com.example.supersimplestockmarket.model.TradeType;
import com.example.supersimplestockmarket.service.dao.InMemoryDao.InMemoryDaoImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TradingServiceImplTests {

    @Mock
    private InMemoryDaoImpl inMemoryDaoImpl;

    @InjectMocks
    private TradingServiceImpl tradingServiceImpl;

    private Trade trade;
    private List<Trade> allTrades;

    @BeforeEach
    void init() {
        trade = new Trade("ALE", 1.5, 100, TradeType.SALE, Instant.now());

        allTrades = new ArrayList<>();
        allTrades.add(new Trade("ALE", 1.5, 100, TradeType.SALE, Instant.now()));
        allTrades.add(new Trade("POP", 1.0, 100, TradeType.PURCHASE, Instant.now()));
        allTrades.add(new Trade("TEA", 1.0, 100, TradeType.PURCHASE, Instant.now()));
        allTrades.add(new Trade("GIN", 1.5, 100, TradeType.SALE, Instant.now()));
    }

    @Test
    void saveTrade() {
        when(inMemoryDaoImpl.saveTrade(any(Trade.class))).thenReturn(1);
        int id = tradingServiceImpl.saveTrade(trade);
        assertEquals(1, id);
        verify(inMemoryDaoImpl, times(1)).saveTrade(trade);
    }

    @Test
    void saveTradeIncorrectTradeSymbol() {
        trade.setStockSymbol("");
        assertThrows(RuntimeException.class, () -> {
            int id = tradingServiceImpl.saveTrade(trade);
        });
    }

    @Test
    void saveTradeIncorrectPriceZero() {
        trade.setPrice(0.0);
        assertThrows(RuntimeException.class, () -> {
            int id = tradingServiceImpl.saveTrade(trade);
        });
    }

    @Test
    void saveTradeIncorrectPriceNegative() {
        trade.setPrice(-1.0);
        assertThrows(RuntimeException.class, () -> {
            int id = tradingServiceImpl.saveTrade(trade);
        });
    }

    @Test
    void saveTradeIncorrectQuantityZero() {
        trade.setQuantity(0);
        assertThrows(RuntimeException.class, () -> {
            int id = tradingServiceImpl.saveTrade(trade);
        });
    }

    @Test
    void saveTradeIncorrectQuantityNegative() {
        trade.setQuantity(-10);
        assertThrows(RuntimeException.class, () -> {
            int id = tradingServiceImpl.saveTrade(trade);
        });
    }

    @Test
    void saveTradeIncorrectTimeStamp() {
        trade.setTimestamp(null);
        assertThrows(RuntimeException.class, () -> {
            int id = tradingServiceImpl.saveTrade(trade);
        });
    }

    @Test
    void saveTradeIncorrectTradeType() {
        trade.setTradeType(null);
        assertThrows(RuntimeException.class, () -> {
            int id = tradingServiceImpl.saveTrade(trade);
        });
    }

    @Test
    void getTrade() {
        when(inMemoryDaoImpl.getTrade(1)).thenReturn(trade);
        Trade result = tradingServiceImpl.getTrade(1);
        assertEquals(trade, result);
        verify(inMemoryDaoImpl, times(1)).getTrade(1);
    }

    @Test
    void getTradeIncorrectId() {
        when(inMemoryDaoImpl.getTrade(22)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> {
            Trade result = tradingServiceImpl.getTrade(22);
        });
    }

    @Test
    void getAllTrades() {
        when(inMemoryDaoImpl.getAllTrades(any(Instant.class), any(Instant.class))).thenReturn(allTrades);
        List<Trade> result = tradingServiceImpl.getAllTrades(15);
        assertEquals(allTrades, result);
        verify(inMemoryDaoImpl, times(1)).getAllTrades(any(Instant.class), any(Instant.class));
    }

}
