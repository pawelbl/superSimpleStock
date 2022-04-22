package com.example.supersimplestockmarket.service;

public interface CalculatingService {
    public double calculateDividentYielt(double inputValue);
    public double calculatePeRatio(double inputValue);
    // Value Weighted Stock Price
    public double calculateVWSP(double inputValue);
    // Geometric Mean
    public double calculateGBCE(double inputValue);
}
