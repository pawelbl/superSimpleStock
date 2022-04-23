package com.example.supersimplestockmarket.model;

import java.util.Date;

public class Trade {
    private String stockSymbol;
    private double price;
    private int quantity;
    private TradeType tradeType;
    private Date timeStamp;

    public Trade(String stockSymbol, double price, int quantity, TradeType tradeType, Date timeStamp) {
        this.stockSymbol = stockSymbol;
        this.price = price;
        this.quantity = quantity;
        this.tradeType = tradeType;
        this.timeStamp = timeStamp;
    }

    public String getStockSymbol() {
        return this.stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TradeType getTradeType() {
        return this.tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public Date getTimestamp() {
        return this.timeStamp;
    }

    public void setTimestamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
