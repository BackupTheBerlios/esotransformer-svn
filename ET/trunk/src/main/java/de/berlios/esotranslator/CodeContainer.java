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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public abstract class CodeContainer {
    StringBuilder code = new StringBuilder();
    protected static String linefeed = System.getProperty("line.separator");
    protected String language = "unknown";
    protected String className;

    abstract public String getStartCode();
    abstract public String getEndCode();
    abstract public String getFileName();
    abstract public String getBinaryFileName();

    public String getCode() {
        return getStartCode() + code.toString() + getEndCode();
    }

    protected void add(String codeline) {
        code.append(codeline + linefeed);
    }

    // child should implement this
    public boolean compile() {
        System.err.println("I currently cannot compile " + getFileName() + " in language '" + language +"'. ");
        return false;
    }
    
    public void setName(String name) {
    	className = name;
    }
    
	public void writeCode() {
		// Write new language source
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(getFileName()));
			bw.write(getCode());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
}
