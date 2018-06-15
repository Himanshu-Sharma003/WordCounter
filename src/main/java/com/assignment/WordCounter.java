package com.assignment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.assignment.model.Criteria;
import com.assignment.reader.FileReaderFactory;
import com.assignment.reader.IFileReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Initiator class , counts the number of words based on serchCriteria and prints the match words on console.
 */
/**
 * 
 * @author Himanshu Sharma
 *
 */
@Slf4j
public class WordCounter {

	public static void main(String args[]) throws IOException {
		byte[] jsonData;
		String filePath;
		if (args == null || args.length < 2) {
			// User the default input file and Criteria file
			InputStream inputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("Criteria.json");
			// Reading Criteria file
			jsonData = IOUtils.toByteArray(inputStream);
			filePath = Thread.currentThread().getContextClassLoader().getResource("input.txt").getPath();
		} else {
			// Reading Criteria file
			jsonData = Files.readAllBytes(Paths.get(args[0]));
			// Input File Path.
			filePath = args[1];
		}

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		// convert json string to object
		List<Criteria> criteria = objectMapper.readValue(jsonData, new TypeReference<List<Criteria>>() {
		});

		String fileType = identifyFileType(filePath);
		// Getting instance of FileReader based on file type using factory
		// implementation.
		FileReaderFactory factory = new FileReaderFactory();
		IFileReader fileReader = factory.getReader(fileType);
		// Processing input file
		Map<Criteria, List<String>> result = fileReader.processFile(filePath, criteria);
		// Displaying result on Console.
		result.forEach((key, value) -> log.info(" {} \n Word Count : {} \n List of words : {}", key.toString(),
				value.size(), value.toString()));
	}

	/**
	 * Derives the file type for the input file.
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