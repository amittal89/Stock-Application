package com.jpmorgan.assignment.stockapp.service;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jpmorgan.assignment.stockapp.Util.StockType;
import com.jpmorgan.assignment.stockapp.Util.TradeType;
import com.jpmorgan.assignment.stockapp.entity.Stock;
import com.jpmorgan.assignment.stockapp.entity.Trade;

@Configuration
public class StockService {

	@Bean
	public StockService sc() {
		return new StockService();
	}

	/**
	 * Returns the Dividend yield based on given stock price
	 * 
	 * @param price
	 * @param st
	 * @return
	 */
	public double getDividendYield(Double price, Stock st) {
		double dividendYield = 0.0;
		if (price > 0.0) {
			if (st.getType() == StockType.COMMON) {
				dividendYield = st.getLastDividend() / price;
			} else {
				dividendYield = (st.getFixedDividend() * st.getParValue()) / price;
			}
		}
		return dividendYield;
	}

	/**
	 * Returns P/E ratio based on given stock price
	 * 
	 * @param price
	 * @param st
	 * @return
	 */
	public double getPeRatio(Double price, Stock st) {

		return price / getDividendYield(price, st);

	}

	/**
	 * Records a buy trade, add the quantity & price of trade to the stock
	 * 
	 * @param quantity
	 * @param price
	 * @param st
	 */
	public void recordBuyTrade(int quantity, double price, Stock st) {
		Trade trade = new Trade(TradeType.BUY, quantity, price);
		st.getTrades().put(new Date(), trade);
	}

	/**
	 * Records a sell trade, add the quantity & price of trade to the stock
	 * 
	 * @param quantity
	 * @param price
	 * @param st
	 */
	public void recordSellTrade(int quantity, double price, Stock st) {
		Trade trade = new Trade(TradeType.SELL, quantity, price);
		st.getTrades().put(new Date(), trade);
	}

	/**
	 * Returns Volume Weighed Stock Price based on trades in last 15 minutes
	 * 
	 * @param st
	 * @return
	 */
	public double getVolumeWeighedStockPrice(Stock st) {

		Date now = new Date();
		Date startTime = new Date(now.getTime() - (15 * 60 * 1000));
		SortedMap<Date, Trade> trades = st.getTrades().tailMap(startTime);
		Double volumeWeigthedStockPrice = 0.0;
		Integer totalQuantity = 0;
		for (Trade trade : trades.values()) {
			totalQuantity += trade.getQuantity();
			volumeWeigthedStockPrice += trade.getPrice() * trade.getQuantity();
		}
		return volumeWeigthedStockPrice / totalQuantity;
	}

	/**
	 * Returns the GBCE All Share index of all the stocks
	 * 
	 * @param stocks
	 * @return
	 */
	public Double getGBCEIndex(Map<String, Stock> stocks) {
		Double allShareIndex = 0.0;
		for (Stock st : stocks.values()) {
			allShareIndex += st.getPrice();
		}
		return Math.pow(allShareIndex, 1.0 / stocks.size());
	}
}
