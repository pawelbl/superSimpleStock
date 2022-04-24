package com.example.supersimplestockmarket.service.stockmarket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.example.supersimplestockmarket.model.StockInfo;
import com.example.supersimplestockmarket.model.StockType;
import com.example.supersimplestockmarket.model.Trade;
import com.example.supersimplestockmarket.model.TradeType;
import com.example.supersimplestockmarket.service.exchangeservice.GlobalExchangeServiceImpl;
import com.example.supersimplestockmarket.service.math.CalculatingService;
import com.example.supersimplestockmarket.service.trading.TradingServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StockMarketServiceTests {

    @Mock
    GlobalExchangeServiceImpl globalExchangeServiceImpl;

    @Mock
    TradingServiceImpl tradingServiceImpl;

    @InjectMocks
    StockMarketServiceImpl stockMarketServiceImpl;

    private List<Trade> allTrades;
    private StockInfo stockInfoCommon;
    private StockInfo stockInfoPreferred;
    private int minutes;

    @BeforeEach
    void init() {
        allTrades = new ArrayList<Trade>();
        allTrades.add(new Trade("TEA", 7.4, 50, TradeType.PURCHASE, Instant.now()));
        allTrades.add(new Trade("POP", 3.1, 40, TradeType.PURCHASE, Instant.now()));
        allTrades.add(new Trade("ALE", 1.3, 170, TradeType.SALE, Instant.now()));
        allTrades.add(new Trade("GIN", 1.2, 100, TradeType.PURCHASE, Instant.now()));
        allTrades.add(new Trade("JOE", 1.7, 200, TradeType.PURCHASE, Instant.now()));
        stockInfoCommon = new StockInfo("POP", StockType.COMMON, 8, 0.0, 100);
        stockInfoPreferred = new StockInfo("GIN", StockType.PREFERRED, 8, 0.02, 100);
        minutes = 15;
    }

    @Test
    void calculateDividentYieldCommon() {
        when(globalExchangeServiceImpl.getStockInfo(anyString())).thenReturn(stockInfoCommon);

        try (MockedStatic<CalculatingService> calculatingService = Mockito.mockStatic(CalculatingService.class)) {
            calculatingService.when(() -> CalculatingService.calculateCommonDividentYield(anyDouble(), anyDouble()))
                    .thenReturn(1.4);
            double result = stockMarketServiceImpl.calculateDividentYield(stockInfoCommon.getSymbol(), minutes);
            assertEquals(1.4, result);
        }
    }

    @Test
    void calculateDividentYieldPreferred() {
        when(globalExchangeServiceImpl.getStockInfo(anyString())).thenReturn(stockInfoPreferred);

        try (MockedStatic<CalculatingService> calculatingService = Mockito.mockStatic(CalculatingService.class)) {
            calculatingService.when(
                    () -> CalculatingService.calculatePreferredDividentYield(anyDouble(), anyDouble(), anyDouble()))
                    .thenReturn(1.4);
            double result = stockMarketServiceImpl.calculateDividentYield(stockInfoPreferred.getSymbol(), minutes);
            assertEquals(1.4, result);
        }
    }

    @Test
    void calculateDividentYieldIncorrectStock() {
        assertThrows(RuntimeException.class, () -> {
            double result = stockMarketServiceImpl.calculatePeRatio("", 1);
        });
    }

    @Test
    void calculateDividentYieldIncorrectPrice() {
        assertThrows(RuntimeException.class, () -> {
            double result = stockMarketServiceImpl.calculatePeRatio("POP", -1);
        });
    }

    @Test
    void calculatePeRatio() {
        when(globalExchangeServiceImpl.getStockInfo(anyString())).thenReturn(stockInfoCommon);

        try (MockedStatic<CalculatingService> calculatingService = Mockito.mockStatic(CalculatingService.class)) {
            calculatingService.when(() -> CalculatingService.calculatePeRatio(anyDouble(), anyDouble()))
                    .thenReturn(1.4);
            double result = stockMarketServiceImpl.calculatePeRatio(stockInfoCommon.getSymbol(), minutes);
            assertEquals(1.4, result);
        }
    }

    @Test
    void calculatePeRatioIncorrectPrice() {
        assertThrows(RuntimeException.class, () -> {
            double result = stockMarketServiceImpl.calculatePeRatio("", -1);
        });
    }

    @Test
    void calculateVWSP() {
        when(tradingServiceImpl.getAllTrades(anyString(), anyInt())).thenReturn(allTrades);
        try (MockedStatic<CalculatingService> calculatingService = Mockito.mockStatic(CalculatingService.class)) {
            calculatingService.when(() -> CalculatingService.calculateVWSP(any()))
                    .thenReturn(1.4);
            double result = stockMarketServiceImpl.calculateVWSP(stockInfoCommon.getSymbol(), minutes);
            assertEquals(1.4, result);
        }
    }

    @Test
    void calculateVWSPIncorrectStock() {
        assertThrows(RuntimeException.class, () -> {
            double result = stockMarketServiceImpl.calculateVWSP("", minutes);
        });
    }

    @Test
    void calculateVWSPIncorrectMinutes() {
        assertThrows(RuntimeException.class, () -> {
            double result = stockMarketServiceImpl.calculateVWSP(stockInfoCommon.getSymbol(), 0);
        });
    }

    @Test
    void calculateGBCE() {
        when(tradingServiceImpl.getAllTrades()).thenReturn(allTrades);
        try (MockedStatic<CalculatingService> calculatingService = Mockito.mockStatic(CalculatingService.class)) {
            calculatingService.when(() -> CalculatingService.calculateGBCE(any()))
                    .thenReturn(1.4);
            double result = stockMarketServiceImpl.calculateGBCE();
            assertEquals(1.4, result);
        }
    }

}
