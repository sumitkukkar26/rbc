package com.rbc.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rbc.assignment.model.Stock;
import com.rbc.assignment.service.StockService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/stock")
@Slf4j
public class StockController {
	
	@Autowired
	private StockService stockService; 

	@GetMapping("/{ticker}")
	@ApiOperation(value = "Get the Entries")
	public ResponseEntity<List<Stock>> getStock(@PathVariable String ticker) {
		List<Stock> list = stockService.getStock(ticker);
		
		if(list==null) {
			log.info("Ticker is not present");
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	@ApiOperation(value = "Add a new Entry")
	public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
		Stock s = stockService.addStock(stock);
		if(s!=null) {
			log.info("Entry has been added");
			return ResponseEntity.ok(s);
		}
		log.error("Failed to add entry");
		return ResponseEntity.internalServerError().body(null);
	}
	
	@PostMapping("/bulk")
	@ApiOperation(value = "Buld Upload the data")
	public ResponseEntity<String> bulkUpload(MultipartFile file) {
		stockService.bulkUpload(file);
		return ResponseEntity.ok("File has been uploaded");
	}
}
