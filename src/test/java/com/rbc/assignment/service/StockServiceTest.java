package com.rbc.assignment.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import com.rbc.assignment.model.Stock;
import com.rbc.assignment.service.exception.StockException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class StockServiceTest {

	@Autowired
	private StockService service;

	@Test
	@DisplayName("Return Stock Object when Add Stock is called successfully")
	public void returnStockObject_whenAddStockIsCalled_givenValidValues() {
		Stock st = Stock.builder().stockTicker("AA").quarter(4).build();

		Stock returnStock = service.addStock(st);

		assertNotNull(returnStock);
		assertEquals("AA", returnStock.getStockTicker());
	}

	@Test
	public void returnListOfEntries_whenGetStockIsCalled_givenTickerIsPresent() {
		Stock st = Stock.builder().stockTicker("AA").quarter(4).build();
		service.addStock(st);

		List<Stock> list = service.getStock("AA");

		assertNotNull(list);
	}

	@Test
	public void returnNull_whenGetStockIsCalled_givenTickerIsNotPresent() {
		List<Stock> list = service.getStock("AA");

		assertNull(list);
	}

	@Test
	public void throwException_whenBulkUploadIsCalled_givenFileIsEmpty() {
		byte[] content = null;
		MultipartFile file = new MockMultipartFile("data.txt", content);
		assertThatExceptionOfType(StockException.class).isThrownBy(() -> service.bulkUpload(file));
	}

	@Test
	public void returnSuccessString_whenBulkUploadIsCalled_givenValidFile() {
		File localFile = new File("text.txt");
		MultipartFile file = null;
		FileInputStream fis;
		try {
			fis = new FileInputStream(localFile);
			file = new MockMultipartFile("data.txt", fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String returnString = service.bulkUpload(file);
		assertEquals("File has been uploaded", returnString);
	}
}
