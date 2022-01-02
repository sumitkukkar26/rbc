package com.rbc.assignment.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rbc.assignment.model.Stock;
import com.rbc.assignment.service.exception.StockException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockService {
		
	private Map<String,ArrayList<Stock>> mapOfStocks = new HashMap<String,ArrayList<Stock>>();
	
	
	public Stock addStock(Stock stock) {
		ArrayList<Stock> stockList = mapOfStocks.get(stock.stockTicker);
		if (stockList==null) {
			stockList = new ArrayList<Stock>();
		}
		stockList.add(stock);
		mapOfStocks.put(stock.stockTicker, stockList);
		return stock;
	}

	public List<Stock> getStock(String ticker) {
		return mapOfStocks.get(ticker);
	}
	
	public String bulkUpload(MultipartFile file) {
		String str = "";
		if(file==null || file.isEmpty()) {
			throw new StockException("File is Empty");
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			log.info("Headers : "+reader.readLine());
			while((str = reader.readLine()) != null) {
				String ary[] = str.split(",");
				Stock stock = Stock.builder().quarter(Integer.parseInt(ary[0])).stockTicker(ary[1]).date(ary[2]).open(ary[3])
						.high(ary[4]).low(ary[5]).close(ary[6]).volume(Long.parseLong(ary[7]))
						.percenChangePrice(Float.parseFloat(ary[8]))
						.nextWeeksOpen(ary[11]).nextWeekClose(ary[12]).percentChangeNextWeeksPrice(Float.parseFloat(ary[13]))
						.daysToNextDividend(Integer.parseInt(ary[14])).percentReturnNextDividend(Float.parseFloat(ary[15])).build();
				if ("1".equals(ary[0]) && mapOfStocks.get(ary[1])==null) {
					stock.setPercentChangVolumeOverLastWeek(0.0f);
					stock.setPreviousWeeksVolume(0L);
				} else {
					stock.setPercentChangVolumeOverLastWeek(Float.parseFloat(ary[9]));
					stock.setPreviousWeeksVolume(Long.parseLong(ary[10]));
				}
				addStock(stock);
			}
		} catch (IOException e) {
			log.error("Not able to process file");
			e.printStackTrace();
		}
		
		return "File has been uploaded";
	}
}
