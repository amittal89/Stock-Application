package com.jpmorgan.assignment.stockapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.jpmorgan.assignment.stockapp.Util.StockType;
import com.jpmorgan.assignment.stockapp.entity.Stock;
import com.jpmorgan.assignment.stockapp.service.StockService;

/**
 * Hello world!
 *
 */
public class App {

	@Bean
	Map<String, Stock> stdata() {
		HashMap<String, Stock> db = new HashMap<String, Stock>();
		db.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
		db.put("POP", new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0));
		db.put("ALE", new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
		db.put("GIN", new Stock("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
		db.put("JOE", new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
		return db;
	}

	private static Log log = LogFactory.getLog(App.class);

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		((AnnotationConfigApplicationContext) context).register(StockService.class);
		StockService sc = context.getBean(StockService.class);

		Map<String, Stock> stdata = context.getBean("stdata", Map.class);
		for (Stock st : stdata.values()) {
			log.debug("****************" + st.getSymbol() + "'s Sock details satrt here."+"****************");
			log.debug(st.getSymbol() + " divident is : " + sc.getDividendYield(100.0, st));
			log.debug(st.getSymbol() + " P/E ratio is : " + sc.getDividendYield(100.0, st));
			for (int i = 0; i < 5; i++) {
				double price = 0.0;
				price = getRandomNumberInRange();
				sc.recordBuyTrade(i, price, st);
				log.debug(st.getSymbol() + " buys " + i + " shares with price " + price);
				price = getRandomNumberInRange();
				sc.recordSellTrade(i, price, st);
				log.debug(st.getSymbol() + " sells " + i + " shares with price " + price);
			}
			log.debug(st.getSymbol() + " Volume Weighted Stock Price is " + sc.getVolumeWeighedStockPrice(st));
		}
		log.debug("***************Now the Big one**********************");
		log.debug("GBCE All Share Index is  " + sc.getGBCEIndex(stdata));
	}

	private static double getRandomNumberInRange() {

		String[] i = new String[50];
		Random rand = new Random();
		int index = 0;

		index = rand.nextInt(i.length);
		return index;

	}
}
