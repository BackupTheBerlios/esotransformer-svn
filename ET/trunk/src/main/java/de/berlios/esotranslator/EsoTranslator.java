/**
 * EsoTranslator - esoteric to common programming languages translator
 *
 * Copyright (C) 2009 Christoph Becker, tuergeist@users.berlios.de
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or 
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, see <http://www.gnu.org/licenses/>.
 */
package de.berlios.esotranslator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EsoTranslator {
	static CodeContainer container;
	Parser parser;
	File inFile;
	
	/**
	 * @param args
	 * @throws IOException
	 * @throws BuilderException 
	 */
	public static void main(String[] args) throws IOException, BuilderException {
		if (args.length != 2) {	
			System.err.println("Usage: EsoTranslator <SourceFile> <DestinationLanguage>\n");
			System.err.println("    Supported source language is: " + EsoLanguage.getList());
			System.err.println("    Supported destination languages are: "+ CommonLanguage.getList() + "\n");
			System.err.println("Example: EsoTranslator testMe.bf Java");
			System.err.println("    will create testMe.java and testMe.class");
			System.exit(1);
		}
		
		final String filename = args[0];
		File inFile = new File(filename);
		if (!inFile.exists()) {
			System.err.println(filename + " does not exist!");
			System.exit(1);
		}

		EsoTranslator trans = new EsoTranslator(inFile, args[1]);
		trans.translate();
		
	}
	
	EsoTranslator(File sourceFile, String outLang) throws IOException, BuilderException {
		this.inFile = sourceFile;
		
		String tmp = inFile.getName();
		final String classname = tmp.substring(0, tmp.indexOf('.'));
		
		
		EsoLanguage inLang = LanguageDetector.detect(inFile); 
		CommonLanguage dstLang = LanguageDetector.detectDestination(outLang);
		
		container = ContainerFactory.get(inLang, dstLang);
		container.setName(classname);
		
		parser = ParserFactory.get(inLang);
		parser.setContainer(container);
	}
	
	public void translate() throws IOException {
		// Parse stuff
		parser.parse(readFile(inFile).toString());
		System.out.println();
		writeCode();
		final String binFile = compile();
		if (binFile != null) {
			System.out.println(binFile + " created.");
		} else {
			System.err.println("Can not compile :(");
		}		
	}

	static StringBuilder readFile(File inFile) throws IOException{
		// read file
		FileReader fr = new FileReader(inFile);
		BufferedReader br = new BufferedReader(fr);
	
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		return sb;
	}

	
	
	public String compile() {
		if (container.compile())
			return container.getBinaryFileName();
		else
			return null;
	}

	public void writeCode() {
		// Write new language source
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(container.getFileName()));
			bw.write(container.getCode());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
