package de.berlios.esotranslator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.sun.xml.internal.fastinfoset.util.StringArray;

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
	
	public static StringArray fileToStringArray(File inFile) throws IOException {
		// read file
		FileReader fr = new FileReader(inFile);
		BufferedReader br = new BufferedReader(fr);
		
		StringArray sa = new StringArray();
		String line = br.readLine();
		while (line != null) {
			sa.add(line);
			line = br.readLine();
		}
		return sa;
	}
}
