package de.berlios.esotranslator;

import de.berlios.esotranslator.brainfuck.BFBuilderFactory;

public enum EsoLanguage {
	BrainFuck("bf", new BFBuilderFactory()),
	Ook("ook", new BFBuilderFactory());

	
	private String extension;
	private CodeContainerFactory factory;
		
	EsoLanguage(String extension, CodeContainerFactory factory) {
		this.extension = extension;
		this.factory = factory;
	}
	
	public String getExtention() {
		return extension;
	}
	
	public CodeContainerFactory getContainerFactory() {
		return factory;
	}
	
	public static String getList() {
		StringBuilder elems = new StringBuilder();
		EsoLanguage[] vals = EsoLanguage.values();
		int i=0;
		for (EsoLanguage e : vals) {
			elems.append(e.name());
			if (++i < vals.length)
				elems.append(", ");
		}
		return elems.toString();
	}
}
