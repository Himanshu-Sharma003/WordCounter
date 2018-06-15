package com.assignment;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.assignment.model.Criteria;
import com.assignment.reader.impl.TextFileReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WordCounterTest {

	/**
	 * This scenario check word count when Search Key and Search pattern both are
	 * given.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSearchCriteriaWithSearchConditionAndPattern() throws IOException {

		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("CriteriaWithSearchString&Expression.json");
		// Reading Criteria file
		byte[] jsonData = IOUtils.toByteArray(inputStream);

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// convert json string to object
		List<Criteria> criterias = objectMapper.readValue(jsonData, new TypeReference<List<Criteria>>() {
		});

		URL inputURL = Thread.currentThread().getContextClassLoader().getResource("input.txt");

		TextFileReader fileReader = new TextFileReader();
		Map<Criteria, List<String>> result = fileReader.processFile(inputURL.getPath(), criterias);
		int resultSize = result.get(criterias.get(0)).size();
		assertEquals(resultSize, 3);
	}

	/**
	 * This scenario check word count when Search Key
	 * given.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSearchCriteriaWithSearchConditionAndIgnoreCase() throws IOException {

		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("CriteriaWithSearchString.json");
		// Reading Criteria file
		byte[] jsonData = IOUtils.toByteArray(inputStream);

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// convert json string to object
		List<Criteria> criterias = objectMapper.readValue(jsonData, new TypeReference<List<Criteria>>() {
		});

		URL inputURL = Thread.currentThread().getContextClassLoader().getResource("input.txt");

		TextFileReader fileReader = new TextFileReader();
		Map<Criteria, List<String>> result = fileReader.processFile(inputURL.getPath(), criterias);
		int resultSize = result.get(criterias.get(0)).size();
		assertEquals(resultSize, 8);
	}
}
