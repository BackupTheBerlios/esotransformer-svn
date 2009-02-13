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

import de.berlios.esotranslator.JCompiler;

public class JavaBuilder extends CommonBuilder implements BFBuilder {
	String language = "Java";

	@Override
	public String getEndCode() {
		return "   System.out.println(); " + linefeed + "}" + linefeed + "}";
	}

	@Override
	public String getStartCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("import java.io.BufferedReader;" + linefeed);
		sb.append("import java.io.InputStreamReader;" + linefeed);
		sb.append("public class " + className + "{" + linefeed);
		sb.append("	int ptr;" + linefeed);
		sb.append("	int[] mem = new int[1000];" + linefeed);

		sb.append("	public static void main(String[] args) {" + linefeed);
		sb.append("		" + className + " bf = new " + className + "();"
				+ linefeed);
		sb.append("		bf.doIt();" + linefeed);
		sb.append("	}" + linefeed);

		sb.append(" void readField() { " + linefeed);
		sb.append("     try {" + linefeed);
		sb.append("         BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));"
						+ linefeed);
		sb.append("         System.out.println(\"Input: \");" + linefeed);
		sb.append("         mem[ptr] = Integer.parseInt(bin.readLine());"
				+ linefeed);
		sb.append("     }" + linefeed);
		sb.append("   catch (Exception ex) {" + linefeed);
		sb.append("       ex.printStackTrace();" + linefeed);
		sb.append("       System.exit(1);" + linefeed);
		sb.append("   }" + linefeed);
		sb.append(" }" + linefeed);
		sb.append(" void printField() {" + linefeed);
		sb.append(" 	char c = (char) mem[ptr];" + linefeed);
		sb.append(" 		if (c < 32|| c > 126) {" + linefeed);
		sb.append(" 			System.out.print((int) c  + \"d \");" + linefeed);
		sb.append(" 		} else {" + linefeed);
		sb.append(" 			System.out.print(c);" + linefeed);
		sb.append(" 		}" + linefeed);
		sb.append(" 	}" + linefeed);
		sb.append(linefeed + "// main action goes here" + linefeed);
		sb.append("public void doIt() {" + linefeed);
		return sb.toString();
	}

	public boolean compile() {
		// Compile Java Code
		JCompiler compiler = new JCompiler(getFileName());
		return compiler.compile();
	}

	@Override
	public String getFileName() {
		return className + ".java";
	}

	public String getBinaryFileName() {
		return className + ".class";
	}
}
