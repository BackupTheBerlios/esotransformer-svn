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
