package com.jpmorgan.assignment.stockapp;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import com.jpmorgan.assignment.stockapp.Util.StockType;
import com.jpmorgan.assignment.stockapp.entity.Stock;
import com.jpmorgan.assignment.stockapp.service.StockService;

public class StockServiceTest {

	StockService sc = new StockService();

	@Test
	public void TestDividendYield() {
		Stock stCommmon = new Stock("POP", StockType.COMMON, 11.0, 0.0, 25.0);
		Stock stPreffered = new Stock("GIN", StockType.PREFERRED, 5.0, 0.5, 50.0);
		double div = sc.getDividendYield(10.0, stCommmon);
		assertEquals(div, stCommmon.getLastDividend() / 10.0, 0.0);
		div = sc.getDividendYield(10.0, stPreffered);
		assertEquals(div, stPreffered.getFixedDividend() * stPreffered.getParValue() / 10.0, 0.0);
	}

	@Test
	public void TestPeRatio() {
		Stock st = new Stock("POP", StockType.COMMON, 11.0, 0.0, 25.0);
		double peRation = sc.getPeRatio(10.0, st);
		assertEquals(peRation, 10 / sc.getDividendYield(10.0, st), 0.0);
	}

	@Test
	public void TestVolumeWeighedStockPrice() {
		Stock st = new Stock("POP", StockType.COMMON, 11.0, 0.0, 25.0);
		sc.recordBuyTrade(2, 10.0, st);
		sc.recordSellTrade(3, 20.0, st);
		double volWeighedStPrice = sc.getVolumeWeighedStockPrice(st);
		assertEquals(volWeighedStPrice, 20, 0.0);
	}

	@Test
	public void testGPCE() {
		HashMap<String, Stock> stocks = new HashMap<String, Stock>();
		stocks.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
		stocks.put("POP", new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0));
		stocks.put("ALE", new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
		stocks.put("GIN", new Stock("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
		stocks.put("JOE", new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
		for (Stock st : stocks.values()) {
			for (int i = 1; i <= 10; i++) {
				sc.recordBuyTrade(3, 10.0, st);
				sc.recordSellTrade(6, 50, st);
				;
			}
		}
		Double GBCEIndex = sc.getGBCEIndex(stocks);
		assertEquals(GBCEIndex, 3.017088168272582, 0.0);
	}

}
