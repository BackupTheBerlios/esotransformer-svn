package de.berlios.esotranslator.aeolbonn;

import static org.easymock.classextension.EasyMock.*;

import java.io.PrintWriter;

import org.junit.Test;

import com.sun.xml.internal.fastinfoset.util.StringArray;

import de.berlios.esotranslator.CodeContainer;

public class AeolbonnParserTest {

	@Test
	public void testAeolbonnParser() {
		AeolbonnParser ap = new AeolbonnParser();
		StringArray sa = new StringArray();
		ap.parseStringArray(sa);
		
		// nothing will happen
	}

	@Test
	public void testParseStringArray() {
		// Mockup
		PrintWriter writerMock = createMock(PrintWriter.class);
		writerMock.println();
		writerMock.print("Hello World!");
		replay(writerMock);

		CodeContainer containerMock = createMock(JavaBuilder.class);
		
		AeolbonnParser ap = new AeolbonnParser();
		ap.setContainer(containerMock);
		ap.setWriter(writerMock);
		StringArray program = new StringArray();
		program.add(":Hello World!");
		program.add(":");
		
		ap.parseStringArray(program);
		
		verify(writerMock);
	}
	
}
