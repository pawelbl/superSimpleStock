package com.example.supersimplestockmarket.service.stockmarket;

import org.springframework.stereotype.Component;

@Component
public class StockMarketServiceImpl implements StockMarketService {

    @Override
    public double calculateDividentYield(double inputPrice) {
        double dividendYield = 0.7;
        return dividendYield;
    };

    @Override
    public double calculatePeRatio(double inputPrice) {
        double peRatio = 0.4;
        return peRatio;
    };

    // Value Weighted Stock Price
    @Override
    public double calculateVWSP(int lastMinutes) {
        double vwsp = 0.4;
        return vwsp;
    };

    // Geometric Mean
    @Override
    public double calculateGBCE() {
        double gbce = 0.4;
        return gbce;
    };
}
