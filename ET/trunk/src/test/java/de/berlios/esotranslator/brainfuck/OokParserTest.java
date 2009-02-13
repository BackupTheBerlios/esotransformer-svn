package de.berlios.esotranslator.brainfuck;

import static org.junit.Assert.assertEquals;
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
import de.berlios.esotranslator.ParserException;
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
			File inFile = new File("src/test/resources/Hello.ook");
			parser.parse(inFile );
			
		} catch (BuilderException e) {
			e.printStackTrace();
			fail("bad");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("bad");
		}

	}
	
	@Test
	public void testOokToBrainfuckSimple() throws ParserException{
		OokParser op = new OokParser();
		assertEquals("[", op.ookToBrainfuck("Ook! Ook?"));
		assertEquals("[-", op.ookToBrainfuck("Ook! Ook? Ook! ook!"));
		assertEquals("[-]", op.ookToBrainfuck("Ook! Ook? Ook! ook!Ook?ook!"));
	}
	
	@Test
	public void testOokToBrainfuckInvalidCombination() {
		OokParser op = new OokParser();
		
		try {
			op.ookToBrainfuck("Ook~ Ook?");
			fail("Should throw exception");
		} catch (ParserException e) {
			assertEquals("OokCode: ~? is unknown. (Position: 0)" , e.getMessage());
		}
		
		try {
			op.ookToBrainfuck("Ook! Ook? Ook* Ook§");
			fail("Should throw exception");
		} catch (ParserException e) {
			assertEquals("OokCode: *§ is unknown. (Position: 2)" , e.getMessage());
		}
	}
}
