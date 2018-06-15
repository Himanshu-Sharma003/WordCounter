package com.assignment.reader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.assignment.model.Criteria;

public interface IFileReader {

	public Map<Criteria, List<String>> processFile(String filePath, List<Criteria> criterias) throws IOException;
}
