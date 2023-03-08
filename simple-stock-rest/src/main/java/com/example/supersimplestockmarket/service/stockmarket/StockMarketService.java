package com.example.supersimplestockmarket.service.stockmarket;

public interface StockMarketService {
    public double calculateDividentYield (String stockSymbol, double inputPrice);

    public double calculatePeRatio(String stockSymbol, double inputPrice);

    // Value Weighted Stock Price
    public double calculateVWSP(String stockSymbol, int minutes);

    // Geometric Mean
    public double calculateGBCE();
}
