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
package de.berlios.esotranslator.compiler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import de.berlios.esotranslator.Compiler;

public class GeneralGCCCompiler extends Compiler {
	protected String compilerCmdLine;
	
	public void setSourceFile(String filename) {
		this.filename = filename; 
		binFilename = filename.substring(0, filename.lastIndexOf('.'));
	}
	
	@Override
	public boolean compile() {
		String cmd = compilerCmdLine + filename + " -o " + binFilename;
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
