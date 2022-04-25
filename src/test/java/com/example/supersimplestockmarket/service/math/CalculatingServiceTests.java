package com.example.supersimplestockmarket.service.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculatingServiceTests {

    // DIVIDEND YIELD
    @Test
    void calculateDividentYieldCommon() {
        double result = CalculatingService.calculateCommonDividentYield(1.5, 0.08);
        assertEquals(0.05333333333333334, result);
    }

    @Test
    void calculateDividentYieldPreferred() {
        double result = CalculatingService.calculatePreferredDividentYield(1.5, 1.00, 0.02);
        assertEquals(0.013333333333333334, result);
    }

    // PE RATIO
    @Test
    void calculatePeRatio() {
        double result = CalculatingService.calculatePeRatio(1.5, 0.08);
        assertEquals(18.75, result);
    }

    @Test
    void calculatePeRatioInvalidInputDividendZero() {
        assertThrows(RuntimeException.class, () -> {
            CalculatingService.calculatePeRatio(1.5, 0);
        });
    }
    // GBCE

    @Test
    void calculateGBCE() {
        List<Double> prices = new ArrayList<Double>();
        prices.add(1.5);
        prices.add(1.1);
        prices.add(0.5);
        prices.add(2.7);
        prices.add(2.4);
        prices.add(0.9);

        double result = CalculatingService.calculateGBCE(prices);
        assertEquals(1.299307, result, 0.001);
    }

    // VWSP

    @Test
    void calculateVWSP() {
        List<Pair<Double, Integer>> trades = new ArrayList<Pair<Double, Integer>>();
        trades.add(Pair.with(1.5, 100));
        trades.add(Pair.with(0.5, 50));
        trades.add(Pair.with(3.5, 20));
        trades.add(Pair.with(0.5, 40));

        double result = CalculatingService.calculateVWSP(trades);

        assertEquals(1.2619047619047619, result, 0.001);
    }
}
