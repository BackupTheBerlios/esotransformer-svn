package de.berlios.esotranslator.brainfuck;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

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
		Logger logger = Logger.getLogger("OokParser");
		String sb = FileHelper.fileToString(sourceFile);
		sb = sb.replaceAll(" ", "").toLowerCase().replaceAll("ook", "");
		
		char[] strings = sb.toCharArray();
		StringBuilder brainfuck = new StringBuilder();
		for (int i=0; i < strings.length; i++) {
			String ookCode = String.valueOf(strings[i]) + String.valueOf(strings[++i]);
			logger.info(ookCode);
			char bfcode = ookToBf.get(ookCode);
			logger.info(ookCode + " -> " + bfcode);
			brainfuck.append(bfcode);
			
		}
		System.out.println(brainfuck.toString());
		parseString(brainfuck.toString());
	}

}
