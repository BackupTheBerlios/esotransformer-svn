package de.berlios.esotranslator.aeolbonn;

import de.berlios.esotranslator.BuilderException;
import de.berlios.esotranslator.CodeContainer;
import de.berlios.esotranslator.CodeContainerFactory;
import de.berlios.esotranslator.CommonLanguage;

public class AoelbonnBuilderFactory implements CodeContainerFactory {

	@Override
	public CodeContainer getBuilder(CommonLanguage outLang)
			throws BuilderException {
		CodeContainer container;
		switch (outLang) {
		case Java:
			container = new JavaBuilder();
			break;
//		case Cpp:
//			container = new CppBuilder();
//			break;
//		case C:
//			container = new CBuilder();
//			break;
		default:
			throw new BuilderException(
					"No Aeolbonn builder available for destination language "
							+ outLang);
		}
		return container;
	}

}
