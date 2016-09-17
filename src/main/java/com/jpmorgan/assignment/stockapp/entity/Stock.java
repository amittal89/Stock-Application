package com.jpmorgan.assignment.stockapp.entity;

import java.util.Date;
import java.util.TreeMap;

import com.jpmorgan.assignment.stockapp.Util.StockType;

public class Stock {

	private String symbol;
	private StockType type;
	private double lastDividend;
	private double fixedDividend;
	private double parValue;
	private TreeMap<Date, Trade> trades;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public StockType getType() {
		return type;
	}

	public void setType(StockType type) {
		this.type = type;
	}

	public double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public double getParValue() {
		return parValue;
	}

	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	public TreeMap<Date, Trade> getTrades() {
		return trades;
	}

	public void setTrades(TreeMap<Date, Trade> trades) {
		this.trades = trades;
	}

	public Stock(String symbol, StockType type, Double lastDividend, Double fixedDividend, Double parValue) {
		this.setSymbol(symbol);
		this.setType(type);
		this.setLastDividend(lastDividend);
		this.setFixedDividend(fixedDividend);
		this.setParValue(parValue);
		this.trades = new TreeMap<Date, Trade>();
	}

	public Double getPrice() {
		if (this.trades.size() > 0) {
			return this.trades.lastEntry().getValue().getPrice();
		} else {
			return 0.0;
		}
	}

}
