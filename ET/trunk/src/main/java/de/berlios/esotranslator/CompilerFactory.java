package de.berlios.esotranslator;

import de.berlios.esotranslator.compiler.CCompiler;
import de.berlios.esotranslator.compiler.CppCompiler;
import de.berlios.esotranslator.compiler.JCompiler;

public class CompilerFactory {
	public static Compiler getCompiler(CommonLanguage outLang) throws IllegalAccessException {
		Compiler compiler;
		switch (outLang) {
		case Java:
			compiler = new JCompiler();
			break;
		case Cpp:
			compiler = new CppCompiler();
			break;
		case C:
			compiler = new CCompiler();
		default:
			throw new IllegalAccessException("Unknown Language - no compiler found.");
		}
		return compiler;
	}
}
