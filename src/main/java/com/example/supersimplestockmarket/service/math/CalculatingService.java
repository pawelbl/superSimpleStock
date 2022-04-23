package com.example.supersimplestockmarket.service.math;

import java.util.List;
import org.javatuples.Pair;

public interface CalculatingService {
    public static double calculateCommonDividentYield(double price, double lastDividend){
        double dividend = 1.0;
        return dividend;
    };

    public static double calculatePrefferedDividentYield(double price, double parValue, double fixedDividend){
        double dividend = 1.0;
        return dividend;
    };


    public static double calculatePeRatio(double price, double lastDividend){
        double peRatio = 1.0;
        return peRatio;
    };


    // Value Weighted Stock Price
    public static double calculateVWSP(List<Double> prices){
        double vwsp = 1.0;
        return vwsp;
    };


    // Geometric Mean
    public static double calculateGBCE(List<Pair<Double, Integer>> inputTrades){
        double gbce = 1.0;
        return gbce;
    };

}
