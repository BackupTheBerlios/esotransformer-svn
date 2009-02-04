package de.berlios.esotranslator;

import de.berlios.esotranslator.brainfuck.BrainfuckParser;

public class ParserFactory {
	public static Parser get(EsoLanguage inLang) {
		Parser parser;
		switch (inLang) {
		case BrainFuck:
			parser = new BrainfuckParser();
			break;
		default:
			throw new IllegalArgumentException("Cannot find parser for "
					+ inLang);
		}
		return parser;
	}
}
