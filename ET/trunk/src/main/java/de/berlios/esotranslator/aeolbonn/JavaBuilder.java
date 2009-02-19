package de.berlios.esotranslator.aeolbonn;

import java.io.PrintWriter;
import java.io.StringWriter;

import de.berlios.esotranslator.CodeContainer;

public class JavaBuilder extends CodeContainer implements AeolbonnBuilder {

	@Override
	public String getBinaryFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEndCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStartCode() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		pw.println("import java.io.BufferedReader;");
		pw.println("import java.io.InputStreamReader;");
		pw.println("public class " + className + "{");
		pw.println("	int ptr;");
		pw.println("	int asterisk;");
		pw.println("	boolean flip;");
		pw.println("	boolean[] mem = new boolean[1000];");
		pw.println();
		pw.println("	public static void main(String[] args) {");
		pw.println("		" + className + " bf = new " + className + "();");
		pw.println("		bf.doIt();");
		pw.println("	}");
		pw.println();
		pw.println("	void print(String line) {");
		pw.println("		if (line.length() == 1) {");
		pw.println("			System.out.println();");
		pw.println("		} else {");
		pw.println("			System.out.print(line.substring(1));");
		pw.println("		}");
		pw.println("	}");
		pw.println();
		pw.println();
		pw.println("// main action goes here");
		pw.println("public void doIt() {");

		return pw.toString();
	}

	
	
	@Override
	public void decAsterisk() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flipField(int field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flipFlipRandomly() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incAsterisk() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void print(String line) {
		// TODO Auto-generated method stub
		
	}

}
