package com.rbc.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.assignment.model.Stock;
import com.rbc.assignment.service.StockService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	private StockService stockService; 

	@GetMapping
	public Stock getStock(@RequestParam String ticker) {
		return null;
	}
	
	@PostMapping
	@ApiOperation(value = "Add a new Entry")
	public ResponseEntity<String> addStock(@RequestBody Stock stock) {
		stockService.addStock(stock);
		return ResponseEntity.ok("New Entry is added");
	}
}
