package de.berlios.esotranslator;

import java.io.File;

public class LanguageDetector {

	/**
	 * Simple file extension test to detect source language
	 * 
	 * @param inFile
	 *            source file
	 * @return detected language
	 * @throws IllegalArgumentException
	 */
	public static EsoLanguage detect(File inFile) {
		String name = inFile.getName();

		for (EsoLanguage lang : EsoLanguage.values()) {
			if (name.endsWith("." + lang.getExtention())) {
				return lang;
			}
		}

		throw new IllegalArgumentException(
				"Cannot detect source files language.");
	}

	public static CommonLanguage detectDestination(String name) {
		for (CommonLanguage lang : CommonLanguage.values()) {
			if (name.equalsIgnoreCase(lang.toString())) {
				return lang;
			}
		}

		throw new IllegalArgumentException(
				"Cannot detect destination language!");
		
	}
}
