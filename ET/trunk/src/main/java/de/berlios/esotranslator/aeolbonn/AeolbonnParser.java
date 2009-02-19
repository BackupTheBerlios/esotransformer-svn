/**
 * EsoTranslator - esoteric to common programmin languages translator
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
package de.berlios.esotranslator.aeolbonn;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.sun.xml.internal.fastinfoset.util.StringArray;

import de.berlios.esotranslator.CodeContainer;
import de.berlios.esotranslator.FileHelper;
import de.berlios.esotranslator.Parser;

/**
 * @author cbecker
 * 
 */
public class AeolbonnParser implements Parser {
	boolean[] memory;
	int asterisk;
	boolean flip;
	int programPointer; // line pointer ;)
	
	private PrintWriter writer;
	
	public AeolbonnParser() {
		memory = new boolean[1000];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.berlios.esotranslator.Parser#parse(java.io.File)
	 */
	@Override
	public void parse(File sourceFile) throws IOException {
		parseStringArray(FileHelper.fileToStringArray(sourceFile));
	}

	void parseStringArray(StringArray program) {

		while (programPointer < program.getSize()) {
			String line = program.get(programPointer);

			// handle non digit values
			char startChar = line.charAt(0);
			switch (startChar) {
			case '<':
				asterisk--;
				break;
			case '>':
				asterisk++;
				break;
			case ':':
				print(line);
				break;
			case '?':
				flipFlipRandomly();
				break;
			case '*':
				handleDigit(asterisk);
				break;

			// assume digit
			default:
				int i = Integer.parseInt(line);
				handleDigit(i);
				break;
			}
			programPointer++;
		}
	}

	void print(String line) {
		if (line.length() == 1) {
			System.out.println("[NL]");
			writer.println();
		} else {
			System.out.print(line.substring(1));
			writer.print(line.substring(1));
		}
	}

	void handleDigit(int i) {
		if (i % 2 == 0 && flip) {
			programPointer = i - 1; // jump
		} else {
			flipField(i);
		}
	}

	void flipField(int n) {
		memory[n] = ! memory[n];
		flip = memory[n];
	}
	
	void flipFlipRandomly() {
		flip = (Math.random() >= .5); // flip rand
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.berlios.esotranslator.Parser#setContainer(de.berlios.esotranslator.CodeContainer)
	 */
	@Override
	public void setContainer(CodeContainer container) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.berlios.esotranslator.Parser#setWriter(java.io.PrintWriter)
	 */
	@Override
	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}

}
