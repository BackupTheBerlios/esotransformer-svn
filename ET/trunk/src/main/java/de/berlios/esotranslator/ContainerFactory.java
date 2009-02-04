package de.berlios.esotranslator;

public class ContainerFactory {
	public static CodeContainer get(String inLang, String outLang) throws BuilderException {
		if (inLang.equals("bf")) {
			return getBFContainer(outLang);
		}
		throw new BuilderException("No Builder available for " + inLang + " to " + outLang);
	}
	
	private static CodeContainer getBFContainer(String outLang) throws BuilderException {
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
