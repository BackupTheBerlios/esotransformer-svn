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

import de.berlios.esotranslator.CodeContainer;

public abstract class CommonBuilder extends CodeContainer implements BFBuilder {
	public void decField() {
		add("--(mem[ptr]);");
	}

	public void decPointer() {
		add("--ptr;");

	}

	public void endLoop() {
		add("} // loop");
	}

	public void incField() {
		add("++(mem[ptr]);");
	}

	public void incPointer() {
		add("++ptr;");
	}

	public void printField() {
		add("printField();");
	}

	public void readField() {
		add("readField();");
	}

	public void startLoop() {
		add("while (mem[ptr] != 0) {");
	}
}
