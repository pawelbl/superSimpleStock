package com.example.supersimplestockmarket.service.stockmarket;

public interface StockMarketService {
    public double calculateDividentYield(double inputPrice);

    public double calculatePeRatio(double inputPrice);

    // Value Weighted Stock Price
    public double calculateVWSP(int lastMinutes);

    // Geometric Mean
    public double calculateGBCE();
}
