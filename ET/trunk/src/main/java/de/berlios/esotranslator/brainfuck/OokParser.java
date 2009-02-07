package de.berlios.esotranslator.brainfuck;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.berlios.esotranslator.FileHelper;


public class OokParser extends BrainfuckParser {
	Map<String, Character> ookToBf = new HashMap<String, Character>() {{
		put(".?", '>');
		put("?.", '<');
		put("..", '+');
		put("!!", '-');
		put("!.", '.');
		put(".!", ',');
		put("!?", '[');
		put("?!", ']');
		
	}};

	public void parse(File sourceFile) throws IOException {
		String sb = FileHelper.fileToString(sourceFile);
		sb.replaceAll(" ", "").toLowerCase().replaceAll("ook", "");
		
		char[] strings = sb.toCharArray();
		StringBuilder brainfuck = new StringBuilder();
		for (int i=0; i < strings.length; i++) {
			brainfuck.append(ookToBf.get(strings[i] + strings[++i]));
			
		}
		parseString(brainfuck.toString());
	}

}
