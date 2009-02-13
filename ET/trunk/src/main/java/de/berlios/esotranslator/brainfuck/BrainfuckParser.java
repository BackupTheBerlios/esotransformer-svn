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
package de.berlios.esotranslator.brainfuck;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import de.berlios.esotranslator.CodeContainer;
import de.berlios.esotranslator.FileHelper;
import de.berlios.esotranslator.Parser;
import de.berlios.esotranslator.ParserException;

public class BrainfuckParser implements Parser {

	int ptr;
	int[] mem;
	BFBuilder builder;
	char endLoopChar = ']';
	char startLoopChar = '[';
	private PrintWriter writer;
	private Logger logger = Logger.getLogger("BFParser");

	public BrainfuckParser() {
		mem = new int[100]; // could become a problem.
	}

	@Override
	public void setContainer(CodeContainer container) {
		this.builder = (BFBuilder) container; // each BF Container implements BFBuilder ;)	
		
	}

//	void dumpMem() {
//		for (int i = 0; i < mem.length; i++) {
//			System.out.printf("%03d ", mem[i]);
//		}
//		System.out.print("\n");
//	}
//
//	public int[] getMem() {
//		return mem; // for testing
//	}

	public void parse(File sourceFile) throws IOException {
		try {
			parseString(FileHelper.fileToString(sourceFile));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void parseString(String bf) throws ParserException  {	
		char[] code = bf.toCharArray();
		int pos = 0;

		while (pos < code.length) {
			switch (code[pos]) {
			case '>':
				incPointer();
				break;
			case '<':
				decPointer();
				break;
			case '+':
				incField();
				break;
			case '-':
				decField();
				break;
			case ',':
				readField();
				break;
			case '.':
				printField();
				break;
				
			case '[':
//				try {
						pos = doLoop(bf, pos);
//					} catch (Exception e) {
//						//logger.error(e);
//						e.getStackTrace();
//						pos = code.length;
//					}
				break;
			}
			pos++;
		}
	}

	void incPointer() {
		++ptr;
		builder.incPointer();
	}

	void decPointer() {
		--ptr;
		builder.decPointer();
	}

	void incField() {
		++(mem[ptr]);
		builder.incField();
	}

	void decField() {
		--(mem[ptr]);
		builder.decField();
	}

	void printField() {
		if (writer != null) {
			char c = (char) mem[ptr];
			if (c>0 || c < 33|| c > 126) {
				// is not printable, so print out the integer value
				writer.write((int) c);
				writer.write("d ");
				
			} else {
				writer.write(c);
			}
		}
		builder.printField();
	}

	void readField() {
//		try {
//			BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
//			System.out.println("Input: ");
//			mem[ptr] = Integer.parseInt(bin.readLine());
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			System.exit(1);
//		}
		mem[ptr] = (int) Math.round(Math.random() * 100); 
		builder.readField();
	}

	int doLoop(String bf, int pos) throws ParserException {
		builder.startLoop();

		int endPos = findCorrespondingBracket(bf, pos);
		String loopCode = bf.substring(pos + 1, endPos);
		
		while (mem[ptr] != 0) {
			parseString(loopCode);
		}
		builder.endLoop();
		return endPos;
	}

	
	int findCorrespondingBracket(String bf, int pos) throws ParserException {
		int cpos = pos;
		int openBrackets = 0;
		while(cpos < bf.length()) {
			if (bf.charAt(cpos) == startLoopChar) {
				openBrackets++;
			} 
			else if (bf.charAt(cpos) == endLoopChar) {
				openBrackets--;
			}
			
			if (openBrackets == 0) {
				return cpos;
			}
			else if (openBrackets < 0) {
				throw new ParserException("Too many closing brackets found!");
			}
			cpos++;
		}
		throw new ParserException("Too less closing brackets found!");
	}
	
	@Override
	public void setWriter(PrintWriter writer) {
		// TODO Auto-generated method stub
		this.writer = writer;
	}
}
