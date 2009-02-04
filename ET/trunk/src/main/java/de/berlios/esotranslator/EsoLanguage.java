package de.berlios.esotranslator;

public enum EsoLanguage {
	BrainFuck("bf");

	private String extension;
		
	EsoLanguage(String extension) {
		this.extension = extension;
	}
	
	public String getExtention() {
		return extension;
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
