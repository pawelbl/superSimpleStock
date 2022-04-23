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
        assertEquals(0.533, result);
    }

    @Test
    void calculateDividentYieldCommonInvalidInputPrice() {
        assertThrows(RuntimeException.class, () -> {
            double result = CalculatingService.calculateCommonDividentYield(-1.5, 0.08);
        });
    }

    @Test
    void calculateDividentYieldCommonInvalidInputPriceZero() {
        assertThrows(RuntimeException.class, () -> {
            double result = CalculatingService.calculateCommonDividentYield(0, 0.08);
        });
    }

    @Test
    void calculateDividentYieldCommonInvalidInputDividend() {
        assertThrows(RuntimeException.class, () -> {
            double result = CalculatingService.calculateCommonDividentYield(-1.5, 0.08);
        });
    }

    @Test
    void calculateDividentYieldPreffered() {
        double result = CalculatingService.calculatePrefferedDividentYield(1.5, 1.00, 0.02);
        assertEquals(0.0133, result);
    }

    @Test
    void calculateDividentYieldPrefferedInvalidInputPrice() {
        assertThrows(RuntimeException.class, () -> {
            double result = CalculatingService.calculatePrefferedDividentYield(-1.5, 1.0, 0.02);
        });
    }

    @Test
    void calculateDividentYieldPrefferedInvalidInputPriceZero() {
        assertThrows(RuntimeException.class, () -> {
            double result = CalculatingService.calculatePrefferedDividentYield(0, 1.0, 0.02);
        });
    }

    @Test
    void calculateDividentYieldPrefferedInvalidInputDividend() {
        assertThrows(RuntimeException.class, () -> {
            double result = CalculatingService.calculatePrefferedDividentYield(1.5, 1.0, -0.02);
        });
    }

    @Test
    void calculateDividentYieldPrefferedInvalidInputDividendZero() {
        assertThrows(RuntimeException.class, () -> {
            double result = CalculatingService.calculatePrefferedDividentYield(1.5, 1.0, 0);
        });
    }

    @Test
    void calculateDividentYieldPrefferedInvalidInputParValue() {
        assertThrows(RuntimeException.class, () -> {
            double result = CalculatingService.calculatePrefferedDividentYield(1.5, -1.0, 0.02);
        });
    }


    // PE RATIO
    @Test
    void calculatePeRatio() {
        double result = CalculatingService.calculatePeRatio(1.5, 0.08);
        assertEquals(18.75, result);
    }

    @Test
    void calculatePeRatioInvalidInputPrice() {
        assertThrows(RuntimeException.class, () -> {
            double result = CalculatingService.calculatePeRatio(-1.5, 0.08);
        });
    }

    @Test
    void calculatePeRatioInvalidInputDividend() {
        assertThrows(RuntimeException.class, () -> {
            double result = CalculatingService.calculatePeRatio(1.5, -0.08);
        });
    }

    @Test
    void calculatePeRatioInvalidInputDividendZero() {
        assertThrows(RuntimeException.class, () -> {
            double result = CalculatingService.calculatePeRatio(1.5, 0);
        });
    }
    // GBCE

    @Test
    void calculateGBCE() {
        List<Pair<Double, Integer>> prices = new ArrayList<Pair<Double,Integer>>();
        prices.add(Pair.with(1.5,100));
        prices.add(Pair.with(0.5,50));
        prices.add(Pair.with(3.5,20));
        prices.add(Pair.with(0.5,40));

        double result = CalculatingService.calculateGBCE(prices);
        assertEquals(2.119, result);
    }

    // VWSP

    void calculateVWSP() {
        List<Double> prices = new ArrayList<Double>();
        prices.add(1.5);
        prices.add(1.1);
        prices.add(0.5);
        prices.add(2.7);
        prices.add(2.4);
        prices.add(0.9);
        double result = CalculatingService.calculateVWSP(prices);
        // 1.481274486482636
        assertEquals(1.481, result);
    }
}
