package com.example.supersimplestockmarket.service.trading;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

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

    @BeforeEach
    void init() {
        trade = new Trade("ALE", 1.5, 100, TradeType.SALE, new Date());
    }

    @Test
    void saveTrade() {
        when(inMemoryDaoImpl.saveTrade(any(Trade.class))).thenReturn(1);
        int id = tradingServiceImpl.saveTrade(trade);
        assertEquals(1, id);
    }

    @Test
    void saveTradeIncorrectTradeSymbol() {
        trade.setStockSymbol("");
        assertThrows(RuntimeException.class, () -> {
            int id = tradingServiceImpl.saveTrade(trade);
        });

        trade.setStockSymbol(null);
        assertThrows(RuntimeException.class, () -> {
            int id = tradingServiceImpl.saveTrade(trade);
        });
    }
}
