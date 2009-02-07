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

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class EsoTranslator {
	/**
	 * @param args
	 * @throws IOException
	 * @throws BuilderException 
	 */
	public static void main(String[] args) throws IOException, BuilderException {
		Logger logger = Logger.getRootLogger();
		logger.addAppender(new ConsoleAppender(new SimpleLayout()));
		
		if (args.length != 2) {	
			logger.info("Usage: EsoTranslator <SourceFile> <DestinationLanguage>\n");
			logger.info("    Supported source language is: " + EsoLanguage.getList());
			logger.info("    Supported destination languages are: "+ CommonLanguage.getList() + "\n");
			logger.info("Example: EsoTranslator testMe.bf Java");
			logger.info("    will create testMe.java and testMe.class");
			System.exit(1);
		}
		
		final String filename = args[0];
		File inFile = new File(filename);
		if (!inFile.exists()) {
			logger.error(filename + " does not exist!");
			System.exit(1);
		}

		Translator trans = new Translator(inFile, args[1]);
		trans.translate();
	}
}
