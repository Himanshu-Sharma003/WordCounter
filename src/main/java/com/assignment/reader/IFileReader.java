package com.assignment.reader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.assignment.model.Criteria;

/**
 * 
 * Interface implemented by all File readers.
 *
 */
public interface IFileReader {

	/**
	 * Process input file for given search criteria and returns map of criteria and
	 * list of matched words
	 * 
	 * @param filePath
	 *            : Input file path
	 * @param criterias
	 *            : List of Search Criteria
	 * @return
	 * @throws IOException
	 */
	public Map<Criteria, List<String>> processFile(String filePath, List<Criteria> criterias) throws IOException;
}
