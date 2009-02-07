package de.berlios.esotranslator;

public interface CodeContainerFactory {
	public CodeContainer getBuilder(CommonLanguage outLang) throws BuilderException;
}
