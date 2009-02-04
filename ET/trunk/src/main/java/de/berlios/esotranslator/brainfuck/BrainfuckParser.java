package de.berlios.esotranslator.brainfuck;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import de.berlios.esotranslator.CodeContainer;
import de.berlios.esotranslator.Parser;

public class BrainfuckParser implements Parser {

	int ptr;
	int[] mem;
	BFBuilder builder;


	public BrainfuckParser(String className, CodeContainer container) {
		mem = new int[1000]; // could become a problem.
		this.builder = (BFBuilder) container; // each BF Container implements BFBuilder ;)	
	}

	void dumpMem() {
		for (int i = 0; i < mem.length; i++) {
			System.out.printf("%03d ", mem[i]);
		}
		System.out.print("\n");
	}

	public int[] getMem() {
		return mem; // for testing
	}

	public void parse(String bf) {
		char[] code = bf.toCharArray();
		int pos = 0;

		while (pos < code.length) {
			switch (code[pos]) {
			case '>':
				incPointer();
				break;
			case '<':
				decPointer();
				break;
			case '+':
				incField();
				break;
			case '-':
				decField();
				break;
			case ',':
				readField();
				break;
			case '.':
				printField();
				break;
			case '[':
				pos = doLoop(bf, pos);
				break;
			}
			pos++;
		}
	}

	void incPointer() {
		++ptr;
		builder.incPointer();
	}

	void decPointer() {
		--ptr;
		builder.decPointer();
	}

	void incField() {
		++(mem[ptr]);
		builder.incField();
	}

	void decField() {
		--(mem[ptr]);
		builder.decField();
	}

	void printField() {
		char c = (char) mem[ptr];
		if (c < 33|| c > 126) {
			System.out.print((int) c);
		} else {
			System.out.print("(" + c + ")");
		}
		builder.printField();
	}

	void readField() {
		try {
			BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Input: ");
			mem[ptr] = Integer.parseInt(bin.readLine());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
		builder.readField();
	}

	int doLoop(String bf, int pos) {
		builder.startLoop();

		int endPos = bf.lastIndexOf(']');
		String loopCode = bf.substring(pos + 1, endPos);
		
		while (mem[ptr] != 0) {
			parse(loopCode);
		}
		builder.endLoop();
		return endPos;
	}
}
