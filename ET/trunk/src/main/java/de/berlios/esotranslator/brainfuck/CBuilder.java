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

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class CBuilder extends CommonBuilder implements BFBuilder {

	public String getBinaryFileName() {
		return className;
	}

	public String getEndCode() {
		return "  printf(\"\\n\"); " + linefeed + "}" + linefeed;
	}

	public String getFileName() {
		return className + ".c";
	}

	@Override
	public String getStartCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("#include <stdio.h>" + linefeed);
		sb.append("int mem[100];" + linefeed);
		sb.append("int ptr = 0;" + linefeed);

		sb.append("void printField() {" + linefeed);
		sb.append("  if (mem[ptr] < 32 || mem[ptr] > 126)  {" + linefeed);
		sb.append("    printf(\"%dd \",mem[ptr]);"
				+ linefeed);
		sb.append("} else {" + linefeed);
		sb.append("    printf(\"%c\", (char) mem[ptr]);" + linefeed);
		sb.append("}" + linefeed);
		sb.append("}" + linefeed);
		sb.append("int main() {" + linefeed);
		return sb.toString();
	}

	

	public boolean compile() {
		String cmd = "/usr/bin/gcc " + getFileName() + " -o "
				+ getBinaryFileName();
		Runtime run = Runtime.getRuntime();
		Process pr;
		int exitCode = 1;
		try {
			pr = run.exec(cmd);
			pr.waitFor();
			exitCode = pr.exitValue();
			if (exitCode > 0) {
				System.out.printf("Error running '%s':\n", cmd);
				BufferedReader buf = new BufferedReader(new InputStreamReader(
						pr.getErrorStream()));
				String line = "";
				while ((line = buf.readLine()) != null) {
					System.err.println(line);
				}
			}
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
		return exitCode == 0;
	}
}
