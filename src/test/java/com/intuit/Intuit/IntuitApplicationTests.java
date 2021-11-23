package com.intuit.Intuit;

import com.intuit.Intuit.dao.StatisticsRepository;
import com.intuit.Intuit.model.StatisticReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

@SpringBootTest
class IntuitApplicationTests {

	@Autowired
	StatisticsRepository statisticsRepository;

	@Test
	public void shouldTestExcelFileWithFirstNameNulls_statisticReportWithFirstNameCounterGreaterThenOne() throws IOException {

		String filePath = "./src/test/resources/test1.csv";

		ReaderService readerService = new ReaderService(filePath,statisticsRepository);

		StatisticReport statisticReport = statisticsRepository.findFirstByOrderByIdAsc();
		assertEquals("Should have only 1 null value inside firstName column",statisticReport.getFirstNameNullCounts(),(Integer)1);
		assertEquals("Should not have null values inside lastName column",statisticReport.getLastNameNullCounts(),(Integer)0);
		assertEquals("Should not have null values inside age column",statisticReport.getAgeNullCounts(),(Integer)0);

		statisticsRepository.delete(statisticReport);

	}

	@Test
	public void shouldTestExcelFileWithFirstNameNulls_statisticReportWithLastNameCounterGreaterThenOne() throws IOException {
		String filePath = "./src/test/resources/test2.csv";

		ReaderService readerService = new ReaderService(filePath,statisticsRepository);

		StatisticReport statisticReport = statisticsRepository.findFirstByOrderByIdAsc();
		assertEquals("Should have only 1 null value inside firstName column",statisticReport.getFirstNameNullCounts(),(Integer)0);
		assertEquals("Should not have null values inside lastName column",statisticReport.getLastNameNullCounts(),(Integer)1);
		assertEquals("Should not have null values inside age column",statisticReport.getAgeNullCounts(),(Integer)0);

		statisticsRepository.delete(statisticReport);
	}

	@Test
	public void shouldTestExcelFileWithFirstNameNulls_statisticReportWithAgeCounterGreaterThenOne() throws IOException {
		String filePath = "./src/test/resources/test3.csv";

		ReaderService readerService = new ReaderService(filePath,statisticsRepository);

		StatisticReport statisticReport = statisticsRepository.findFirstByOrderByIdAsc();
		assertEquals("Should have only 1 null value inside firstName column",statisticReport.getFirstNameNullCounts(),(Integer)0);
		assertEquals("Should not have null values inside lastName column",statisticReport.getLastNameNullCounts(),(Integer)0);
		assertEquals("Should not have null values inside age column",statisticReport.getAgeNullCounts(),(Integer)2);

		statisticsRepository.delete(statisticReport);
	}

	@Test
	public void shouldTestAverageAgeWhenOneRowIsNull_correctAverageValue() throws IOException {
		String filePath = "./src/test/resources/test4.csv";

		ReaderService readerService = new ReaderService(filePath,statisticsRepository);

		StatisticReport statisticReport = statisticsRepository.findFirstByOrderByIdAsc();
		assertEquals("Should have correct average",(double)statisticReport.getAverageAge(),10,0);

		statisticsRepository.delete(statisticReport);
	}

	@Test
	public void shouldTestAverageAgeWhenAllRowsCorrect_correctAverageValue() throws IOException {
		String filePath = "./src/test/resources/test5.csv";

		ReaderService readerService = new ReaderService(filePath,statisticsRepository);

		StatisticReport statisticReport = statisticsRepository.findFirstByOrderByIdAsc();
		assertEquals("Should have correct average",(double)statisticReport.getAverageAge(),15,0);

		statisticsRepository.delete(statisticReport);
	}

	@Test
	public void shouldTestAllCorrectValues_correctAverageValueAndCorrectNullValues() throws IOException {
		String filePath = "./src/test/resources/test6.csv";

		ReaderService readerService = new ReaderService(filePath,statisticsRepository);

		StatisticReport statisticReport = statisticsRepository.findFirstByOrderByIdAsc();
		assertEquals("Should have correct average",(double)statisticReport.getAverageAge(),20,0);
		assertEquals("Should have 0 nulls of first name",statisticReport.getFirstNameNullCounts(),(Integer)0);
		assertEquals("Should have 0 nulls of last name",statisticReport.getLastNameNullCounts(),(Integer)0);
		assertEquals("Should have 0 nulls of age",statisticReport.getAgeNullCounts(),(Integer)0);

		statisticsRepository.delete(statisticReport);
	}

	@Test
	public void shouldTestWrongFilePath_invalidPathThrowException() throws IOException {
		String filePath = "./src/test/resources/test7.csv";

		Exception exception = assertThrows(FileNotFoundException.class, () -> {
			ReaderService readerService = new ReaderService(filePath,statisticsRepository);
		});

		String expectedMessage = ".\\src\\test\\resources\\test7.csv (The system cannot find the file specified)";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}


}
