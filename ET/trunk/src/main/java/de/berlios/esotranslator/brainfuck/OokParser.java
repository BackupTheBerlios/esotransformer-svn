package de.berlios.esotranslator.brainfuck;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import de.berlios.esotranslator.FileHelper;
import de.berlios.esotranslator.ParserException;


public class OokParser extends BrainfuckParser {
	Map<String, Character> ookToBf = new HashMap<String, Character>() {
		private static final long serialVersionUID = 1354L;
	{
		put(".?", '>');
		put("?.", '<');
		put("..", '+');
		put("!!", '-');
		put("!.", '.');
		put(".!", ',');
		put("!?", '[');
		put("?!", ']');
		
	}};

	public void parse(File sourceFile) throws IOException, ParserException {
		String sb = FileHelper.fileToString(sourceFile);
		parseString(ookToBrainfuck(sb));		
	}

	String ookToBrainfuck(String ook) throws ParserException {
		Logger logger = Logger.getLogger("OokParser");
		String sb = ook.replaceAll(" ", "").toLowerCase().replaceAll("ook", "");
		
		char[] strings = sb.toCharArray();
		StringBuilder brainfuck = new StringBuilder();
		for (int i=0; i < strings.length; i++) {
			String ookCode = String.valueOf(strings[i]) + String.valueOf(strings[++i]);
//			logger.info(ookCode);
			if (! ookToBf.containsKey(ookCode)) {
				throw new ParserException("OokCode: " + ookCode + " is unknown. (Position: " + (i-1) +")");
			}
			char bfcode = ookToBf.get(ookCode);
//			logger.info(ookCode + " -> " + bfcode);
			brainfuck.append(bfcode);
			
		}
//		System.out.println(brainfuck.toString());
		return brainfuck.toString();
	}
}
