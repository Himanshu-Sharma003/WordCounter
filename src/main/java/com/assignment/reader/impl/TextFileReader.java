package com.assignment.reader.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.assignment.model.Criteria;
import com.assignment.reader.IFileReader;
import com.assignment.utils.CounterUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * File Reader implementation for plain text file.
 *
 */
@Slf4j
public class TextFileReader implements IFileReader {
	/**
	 * Process the input file and returns result Map containing search criteria
	 * object as Key and list of matched words as value.
	 */
	public Map<Criteria, List<String>> processFile(String filePath, List<Criteria> criterias) throws IOException {
		log.debug("Search file {} for word count", filePath);
		// Result Map.
		Map<Criteria, List<String>> result = new HashMap<>();
		// Try-with-resource
		try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = in.readLine()) != null) {
				// process line here.
				CounterUtils.searchWords(line, criterias, result);
			}
		}
		return result;
	}

}
