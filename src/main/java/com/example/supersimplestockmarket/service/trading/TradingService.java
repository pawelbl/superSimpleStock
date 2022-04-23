package com.example.supersimplestockmarket.service.trading;

import com.example.supersimplestockmarket.model.Trade;

public interface TradingService {

    public void saveTrade(Trade trade);

    public Trade retrieveTrade(int id);

    public Trade[] getAllTrades(int minutes);

}
