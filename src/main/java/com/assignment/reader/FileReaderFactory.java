package com.assignment.reader;

import com.assignment.reader.impl.PDFFileReader;
import com.assignment.reader.impl.TextFileReader;

/**
 * Factory Class, returns the instance of FileReader based on the file type.
 */
/**
 * 
 * @author Himanshu Sharma
 *
 */
public class FileReaderFactory {

	/**
	 * Return instance of FileReader based on fileType.
	 * 
	 * @param fileType
	 * @return FileReader Instance.
	 */
	public IFileReader getReader(String fileType) {
		// If file type is txt , return TextFileReader.
		if (fileType.equalsIgnoreCase("text/plain")) {
			return new TextFileReader();
		}// For file type PDF return PDFFileReader. 
		else if (fileType.equalsIgnoreCase("application/pdf")) {
			return new PDFFileReader();
		}
		return null;
	}
}
