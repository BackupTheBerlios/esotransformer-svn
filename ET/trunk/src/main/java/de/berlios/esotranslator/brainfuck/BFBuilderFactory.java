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

import de.berlios.esotranslator.BuilderException;

public class BFBuilderFactory {
	public BFBuilder getBuilder(String outLang) throws BuilderException {
		if (outLang.equals("Java")) {
			return new de.berlios.esotranslator.brainfuck.JavaBuilder(); 
		}
		else if (outLang.equals("C++")) {
			return new de.berlios.esotranslator.brainfuck.CppBuilder();
		}
		else if (outLang.equals("C")) {
			return new de.berlios.esotranslator.brainfuck.CBuilder();
		}
		throw new BuilderException("No Brainfuck builder available for destination language " + outLang);
	}
}
