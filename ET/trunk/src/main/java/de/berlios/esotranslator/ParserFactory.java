package de.berlios.esotranslator;

import de.berlios.esotranslator.brainfuck.BrainfuckParser;
import de.berlios.esotranslator.brainfuck.OokParser;

public class ParserFactory {
	public static Parser get(EsoLanguage inLang) {
		Parser parser;
		switch (inLang) {
			case BrainFuck:
				parser = new BrainfuckParser();
				break;
			case Ook:
				parser = new OokParser();
				break;
			default:
				throw new IllegalArgumentException("Cannot find parser for "
						+ inLang);
		}
		return parser;
	}
}
