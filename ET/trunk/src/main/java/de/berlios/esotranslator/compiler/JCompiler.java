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

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import de.berlios.esotranslator.Compiler;

public class JCompiler extends Compiler {

	public void setSourceFile(String filename) {
		this.filename = filename; 
		binFilename = filename.substring(0, filename.lastIndexOf('.')) + ".class";
	}
	
	public boolean compile() {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		int results = compiler.run(null, null, null, filename);
		return (results == 0);
	}
}
