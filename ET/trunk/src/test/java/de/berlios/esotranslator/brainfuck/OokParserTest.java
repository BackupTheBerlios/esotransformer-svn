package de.berlios.esotranslator.brainfuck;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;

import de.berlios.esotranslator.BuilderException;
import de.berlios.esotranslator.CodeContainer;
import de.berlios.esotranslator.CommonLanguage;
import de.berlios.esotranslator.EsoLanguage;
import de.berlios.esotranslator.Parser;
import de.berlios.esotranslator.ParserFactory;

public class OokParserTest {

	@Test
	public void testParse() {

		PrintWriter writer = new PrintWriter(System.out);
		EsoLanguage inLang = EsoLanguage.Ook;
		CodeContainer container;
		Parser parser;
		try {
			container = inLang.getContainerFactory().getBuilder(
					CommonLanguage.Java);
			container.setName("TestClass");
			
			parser = ParserFactory.get(inLang);
			parser.setContainer(container);
			parser.setWriter(writer);
			File inFile = new File("/home/cb/jee-workspace/esotranslator/src/test/resources/Hello.ook");
			parser.parse(inFile );
			
		} catch (BuilderException e) {
			e.printStackTrace();
			fail("bad");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
