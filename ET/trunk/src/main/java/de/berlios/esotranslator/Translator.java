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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

public class Translator {

	CodeContainer container;
	Parser parser;
	File inFile;
	Logger logger = Logger.getLogger("Translator");
	private PrintWriter writer;
	private Compiler compiler;

	public Translator(File sourceFile, String outLang) throws IOException, BuilderException {
		this.inFile = sourceFile;
		
		String tmp = inFile.getName();
		final String classname = tmp.substring(0, tmp.indexOf('.'));
		
		
		EsoLanguage inLang = LanguageDetector.detect(inFile); 
		CommonLanguage dstLang = LanguageDetector.detectDestination(outLang);
		
		writer = new PrintWriter(System.out);
		writer.append("Parsing result: ");
		
		container = inLang.getContainerFactory().getBuilder(dstLang);
		container.setName(classname);
		
		parser = ParserFactory.get(inLang);
		parser.setContainer(container);
		parser.setWriter(writer);
		
		try {
			compiler = CompilerFactory.getCompiler(dstLang);
		} catch (IllegalAccessException e) {
			throw new BuilderException(e.getMessage());
		}
	}

	public void translate() throws IOException {
		// Parse stuff
		parser.parse(inFile);
	
		// Print parsing result
		writer.append(System.getProperty("line.separator")).flush();
		
		container.writeCode();
				
		compile();
	}

	public void compile() {
		compiler.setSourceFile(container.getFileName());
		if (compiler.compile()) {
			logger.info(compiler.getBinaryFilename() + " created.");
		}
		else {
			logger.warn("Cannot compile :(");
		}			
	}

}
