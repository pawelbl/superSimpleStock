package com.example.supersimplestockmarket.service.trading;

import java.util.List;

import com.example.supersimplestockmarket.model.Trade;

public interface TradingService {

    public int saveTrade(Trade newTrade);

    public Trade getTrade(int id);

    public  List<Trade> getAllTrades(int minutes);

}
