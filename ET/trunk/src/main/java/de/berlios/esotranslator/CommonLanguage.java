package de.berlios.esotranslator;

public enum CommonLanguage {
	Java("java", "class"),
	Cpp("cc", ""),
	C("c", "");
	
	private String sourceExt;
	private String binExt;
	
	CommonLanguage(String sourceExt, String binExt) {
		this.sourceExt = sourceExt;
		this.binExt = binExt;
	}

	public String getSourceExt() {
		return sourceExt;
	}

	public String getBinExt() {
		return binExt;
	}
	
	public static String getList() {
		StringBuilder elems = new StringBuilder();
		CommonLanguage[] vals = CommonLanguage.values();
		int i=0;
		for (CommonLanguage e : vals) {
			elems.append(e.name());
			if (++i < vals.length)
				elems.append(", ");
		}
		return elems.toString();
	}
}
