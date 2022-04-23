package com.example.supersimplestockmarket.service.math;

import java.util.List;
import org.javatuples.Pair;

public interface CalculatingService {
    public static double calculateCommonDividentYield(double price, double lastDividend) {
        return lastDividend / price;
    };

    public static double calculatePrefferedDividentYield(double price, double parValue, double fixedDividend) {
        double dividend = 1.0;
        dividend = (fixedDividend * parValue) / price;
        return dividend;
    };

    public static double calculatePeRatio(double price, double lastDividend) {
        if (lastDividend == 0) {
            throw new RuntimeException("Cannot calculate Pe Ratio: Last dividend equals 0");
        }
        return price / lastDividend;
    };

    // Value Weighted Stock Price
    public static double calculateVWSP(List<Pair<Double, Integer>> inputTrades) {
        int quantity = 0;
        double sum = 0;

        if (inputTrades.size() == 0) {
            throw new RuntimeException("Cannot calculate GBCE: Price list cannot be empty");
        }

        for (Pair<Double, Integer> trade : inputTrades) {
            double price = trade.getValue0();
            int q = trade.getValue1();

            sum += price * q;
            quantity += q;
        }

        return sum / quantity;
    };

    // Geometric Mean
    public static double calculateGBCE(List<Double> prices) {

        double stockPrices = 1;
        double power;

        if (prices.size() == 0) {
            throw new RuntimeException("Cannot calculate VWSP: Price list cannot be empty");
        }

        for (Double price : prices) {
            stockPrices *= price;
        }

        power = 1.0 / prices.size();
        return Math.pow(stockPrices, power);
    };

}
