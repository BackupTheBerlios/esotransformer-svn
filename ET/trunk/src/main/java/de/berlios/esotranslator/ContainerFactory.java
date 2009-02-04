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

public class ContainerFactory {
	public static CodeContainer get(EsoLanguage inLang, CommonLanguage outLang)
			throws BuilderException {
		if (inLang == EsoLanguage.BrainFuck) {
			return getBFContainer(outLang);
		}
		throw new BuilderException("No Builder available for " + inLang
				+ " to " + outLang);
	}

	private static CodeContainer getBFContainer(CommonLanguage outLang)
			throws BuilderException {
		CodeContainer container;
		switch (outLang) {
		case Java:
			container = new de.berlios.esotranslator.brainfuck.JavaBuilder();
			break;
		case Cpp:
			container = new de.berlios.esotranslator.brainfuck.CppBuilder();
			break;
		case C:
			container = new de.berlios.esotranslator.brainfuck.CBuilder();
			break;
		default:
			throw new BuilderException(
					"No Brainfuck builder available for destination language "
							+ outLang);
		}
		return container;
	}
}
