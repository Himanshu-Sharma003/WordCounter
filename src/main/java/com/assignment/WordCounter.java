package com.assignment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.assignment.model.Criteria;
import com.assignment.reader.FileReaderFactory;
import com.assignment.reader.IFileReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Himanshu Sharma
 *
 */
@Slf4j
public class WordCounter {

	public static void main(String args[]) throws IOException {
		// Reading Criteria file
		byte[] jsonData = Files.readAllBytes(Paths.get("C:\\Users\\Pummy\\Desktop\\criteria.json"));

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// convert json string to object
		List<Criteria> criteria = objectMapper.readValue(jsonData, new TypeReference<List<Criteria>>() {
		});

		String filePath = "C:\\Users\\Pummy\\Desktop\\Meera-Parents-Visa-TODO.txt";
		String fileType = identifyFileType(filePath);
		FileReaderFactory factory = new FileReaderFactory();
		IFileReader fileReader = factory.getReader(fileType);
		Map<Criteria, List<String>> result = fileReader.processFile(filePath, criteria);
		result.forEach((key, value) -> log.info(" {} \n Word Count : {} \n List of words : {}", key.toString(), value.size(),value.toString()));
	}

	/**
	 * Derives the file type for input file.
	 * 
	 * @param fileName
	 *            : Absolute file name.
	 * @return: file type i.e text/plain or application/pdf.
	 */
	private static String identifyFileType(final String fileName) {
		String fileType = "";
		final File file = new File(fileName);
		try {
			fileType = Files.probeContentType(file.toPath());
		} catch (IOException ioException) {
			log.error(ioException.getLocalizedMessage());
		}
		return fileType;
	}

}