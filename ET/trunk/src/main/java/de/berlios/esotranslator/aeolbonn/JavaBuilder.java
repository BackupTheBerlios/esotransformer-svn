package de.berlios.esotranslator.aeolbonn;

import java.io.PrintWriter;
import java.io.StringWriter;

import de.berlios.esotranslator.CodeContainer;

public class JavaBuilder extends CodeContainer implements AeolbonnBuilder {
	String language = "Java";

	@Override
	public String getFileName() {
		return className + ".java";
	}

	public String getBinaryFileName() {
		return className + ".class";
	}

	@Override
	public String getEndCode() {
		return "   System.out.println(); " + linefeed + "}" + linefeed + "}";
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
		pw.println("	void flipField(int n) {");
		pw.println("		memory[n] = ! memory[n];");
		pw.println("		flip = memory[n];");
		pw.println("	}");	
		pw.println();
		pw.println("	void handleDigit(int i) {");	
		pw.println("		if (i % 2 == 0 && flip) {");	
		pw.println("			programPointer = i - 1; // jump");	
		pw.println("		} else {");	
		pw.println("			flipField(i);");	
		pw.println("		}");	
		pw.println("	}");
		pw.println();	
		pw.println("// main action goes here");
		pw.println("public void doIt() {");

		return pw.toString();
	}

	
	
	@Override
	public void decAsterisk() {
		add("asterisk--;");
	}

	@Override
	public void flipField(int field) {
		add("flipField(" + field +")");
	}

	@Override
	public void flipFlipRandomly() {
		add("flip = (Math.random() >= .5);");
	}

	@Override
	public void incAsterisk() {
		add("asterisk++;");
		
	}

	@Override
	public void print(String line) {
		// TODO Auto-generated method stub
		add("System.out.println(" + line + ");");
	}

	@Override
	public void handleDigit(int digit) {
		add("handleDigit(" + digit + ")");		
	}

}
