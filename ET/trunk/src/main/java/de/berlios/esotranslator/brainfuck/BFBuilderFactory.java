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
