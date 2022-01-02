package com.rbc.assignment.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbc.assignment.model.Stock;
import com.rbc.assignment.service.StockService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class StockControllerTest {

	@MockBean
	StockService service;

	@InjectMocks
	StockController stockController;

	@Autowired
	private MockMvc mockMvc;
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Test
	public void returnStatus200_whenGetStockIsCalled_givenTickerisPresent() throws Exception {
		Stock st = Stock.builder().stockTicker("AA").quarter(4).build();
		List<Stock> list = new ArrayList<Stock>();
		list.add(st);
		when(service.getStock(Mockito.any())).thenReturn(list);

		mockMvc.perform(get("/stock/AA")).andExpect(status().is(200));
	}

	@Test
	public void returnStatus204_whenGetStockIsCalled_givenTickerIsAbsent() throws Exception {
		when(service.getStock(Mockito.any())).thenReturn(null);
		mockMvc.perform(get("/stock/AA")).andExpect(status().is(204));
	}
	
	@Test
	public void returnStatus200_whenAddStockIsCalled_givenValidValues() throws Exception {
		Stock st = Stock.builder().stockTicker("AA").quarter(4).build();
		when(service.addStock(Mockito.any())).thenReturn(st);

		String json = OBJECT_MAPPER.writeValueAsString(st);
		mockMvc.perform(post("/stock").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().is(200));
	}
	
	@Test
	public void returnStatus200_whenBulkEndpointIsCalled_givenValidFile() throws Exception {
		File localFile = new File("text.txt");
		MockMultipartFile multipartFile = null;
		FileInputStream fis;
		try {
			fis = new FileInputStream(localFile);
			multipartFile = new MockMultipartFile("data.txt", fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mockMvc.perform(MockMvcRequestBuilders.multipart("/stock/bulk").file(multipartFile)).andExpect(status().is(200));
	}

	
	
	
	
	

}
