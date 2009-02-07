package de.berlios.esotranslator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHelper {

	public static String fileToString(File inFile) throws IOException{
		// read file
		FileReader fr = new FileReader(inFile);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		return sb.toString();
	}
}
