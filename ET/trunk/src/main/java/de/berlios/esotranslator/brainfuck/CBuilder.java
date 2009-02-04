package de.berlios.esotranslator.brainfuck;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class CBuilder extends CommonBuilder implements BFBuilder {

	public String getBinaryFileName() {
		return className;
	}

	public String getEndCode() {
		return "}" + linefeed;
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
		sb.append("  if (mem[ptr] < 33 || mem[ptr] > 126)  {" + linefeed);
		sb.append("    printf(\"%dd \",mem[ptr]);"
				+ linefeed);
		sb.append("} else {" + linefeed);
		sb.append("    printf(\"%sd \",mem[ptr]);" + linefeed);
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
