package com.assignment.reader;

import com.assignment.reader.impl.PDFFileReader;
import com.assignment.reader.impl.TextFileReader;

public class FileReaderFactory {

	public IFileReader getReader(String fileType) {

		if (fileType.equalsIgnoreCase("text/plain")) {
			return new TextFileReader();

		} else if (fileType.equalsIgnoreCase("application/pdf")) {
			return new PDFFileReader();

		}

		return null;
	}
}
