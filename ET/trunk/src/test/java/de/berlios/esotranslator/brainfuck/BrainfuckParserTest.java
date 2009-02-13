package de.berlios.esotranslator.brainfuck;

import static org.junit.Assert.*;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.junit.Test;

import de.berlios.esotranslator.CodeContainer;
import de.berlios.esotranslator.ParserException;

public class BrainfuckParserTest {
	private BrainfuckParser bp = new BrainfuckParser();;
	static {
		Logger logger = Logger.getRootLogger();
		logger.addAppender(new ConsoleAppender(new SimpleLayout()));
	}

	@Test
	public void testDoLoop() throws Exception {
		CodeContainerImpl container = new CodeContainerImpl();
		bp.setContainer(container);
		try {
			bp.parseString("[..");
			assertNull("parser should throw an exception");
		} catch (ParserException e) {
			assertTrue(true);
		}
		assertEquals(1, container.openloops);
		assertEquals(0, container.loopcount);

		container.openloops = 0;
		container.loopcount = 0;
		bp.parseString("[..][..]");
		assertEquals(0, container.openloops);
		assertEquals(2, container.loopcount);
	}

	@Test
	public void testDoLoopComplex() throws Exception {
		CodeContainerImpl container = new CodeContainerImpl();
		bp.setContainer(container);
		bp.parseString("[..][[..][..]]");
		assertEquals(2, container.loopcount); // the both two inner loops
		// won't start
		assertEquals(0, container.openloops);
	}

	@Test
	public void testDoLoopComplex2() throws Exception {
		CodeContainerImpl container = new CodeContainerImpl();
		bp.setContainer(container);
		bp.parseString("[..]+[[..-]+[..-]]");
		assertEquals(4, container.loopcount); // the both two inner loops
		// won't start
		assertEquals(0, container.openloops);
	}

	@Test
	public void testFindCorrespondingBracket() throws Exception {
		String bf = "[..]";
		int pos = 0;
		assertEquals(3, bp.findCorrespondingBracket(bf, pos));
	}

	@Test
	public void testFindCorrespondingBracketRecurse() throws Exception {
		String bf = "[..[..]..]";
		int pos = 0;
		assertEquals(9, bp.findCorrespondingBracket(bf, pos));
	}

	@Test
	public void testFindCorrespondingBracketSequentiell() throws Exception {
		String bf = "[..]..[..]";
		int pos = 0;
		assertEquals(3, bp.findCorrespondingBracket(bf, pos));
	}

	@Test
	public void testFindCorrespondingBracketComplex() throws Exception {
		String bf = "[[..].[..[]].[..]]";
		int pos = 0;
		assertEquals(bf.lastIndexOf(']'), bp.findCorrespondingBracket(bf, pos));
	}
}

class CodeContainerImpl extends CodeContainer implements BFBuilder {
	int openloops;
	int loopcount;

	@Override
	public String getBinaryFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEndCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStartCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void decField() {
		// TODO Auto-generated method stub

	}

	@Override
	public void decPointer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endLoop() {
		// TODO Auto-generated method stub
		openloops--;
		loopcount++;
	}

	@Override
	public void incField() {
		// TODO Auto-generated method stub

	}

	@Override
	public void incPointer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printField() {
		// TODO Auto-generated method stub

	}

	@Override
	public void readField() {
		// TODO Auto-generated method stub

	}

	@Override
	public void startLoop() {
		// TODO Auto-generated method stub
		openloops++;
	}

}