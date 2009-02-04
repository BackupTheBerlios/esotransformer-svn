package de.berlios.esotranslator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import de.berlios.esotranslator.brainfuck.BFBuilder;
import de.berlios.esotranslator.brainfuck.BrainfuckParser;

public class EsoTranslator {
	private static BFBuilder builder;
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
			System.err.println("    Supported source language is: Brainfuck");
			System.err.println("    Supported destination languages are: Java, C++, C\n");
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
		
		String inLang = "bf"; // hard coded for now

		container = ContainerFactory.get(inLang, outLang);
		container.setName(classname);
		
		parser = new BrainfuckParser(classname, container);
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
