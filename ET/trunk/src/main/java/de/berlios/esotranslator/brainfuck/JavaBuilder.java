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
		sb
				.append("         BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));"
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
		sb.append(" 		if (c < 33|| c > 126) {" + linefeed);
		sb.append(" 			System.out.print((int) c);" + linefeed);
		sb.append(" 		} else {" + linefeed);
		sb.append(" 			System.out.print(c + \"d \");" + linefeed);
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
