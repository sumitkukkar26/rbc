package com.rbc.assignment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rbc.assignment.model.Stock;

@Service
public class StockService {
		
	private Map<String,ArrayList<Stock>> mapOfStocks = new HashMap<String,ArrayList<Stock>>();
	
	public void init() {
		
	}
	
	public void addStock(Stock stock) {
		ArrayList<Stock> stockList = mapOfStocks.get(stock.stockTicker);
		if (stockList==null) {
			stockList = new ArrayList<Stock>();
		}
		System.out.println("Stock : "+stock.toString());
		stockList.add(stock);
		mapOfStocks.put(stock.stockTicker, stockList);
	}

}
