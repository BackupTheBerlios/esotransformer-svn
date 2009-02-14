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

public class CppBuilder extends CommonBuilder implements BFBuilder {

	public String getBinaryFileName() {
		return className;
	}

	public String getEndCode() {
		return "  printf(\"\\n\"); " + linefeed + "}" + linefeed;
	}

	public String getFileName() {
		return className + ".cc";
	}

	@Override
	public String getStartCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("#include <iostream>" + linefeed);
		sb.append("int mem[100];" + linefeed);
		sb.append("int ptr = 0;" + linefeed);

		sb.append("void printField() {" + linefeed);
		sb.append("if (mem[ptr] < 32 || mem[ptr] > 126)  {" + linefeed);
		sb.append("std::cout << (int) mem[ptr] << \"d \" << std::flush; "
				+ linefeed);
		sb.append("} else {" + linefeed);
		sb.append("    std::cout << (char) mem[ptr] << std::flush;" + linefeed);
		sb.append("}" + linefeed);
		sb.append("}" + linefeed);
		sb.append("int main() {" + linefeed);
		return sb.toString();
	}
}
