package com.adrihercer.client.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class PropertiesReaderTest {

	@Test
	public void test_PropertiesFile_NotFound() throws IOException {
		
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		PropertiesReader mockPropertiesReader = new PropertiesReader("non-existent-file.properties");
		mockPropertiesReader.readProperties();
		
		assertEquals("", "File 'non-existent-file.properties' was not found in the jar file!" + System.getProperty("line.separator"), os.toString());
		
		System.setOut(System.out);
	}
	
	@Test
	public void test_PropertiesFile_Found() throws IOException {
		
		PropertiesReader mockPropertiesReader = new PropertiesReader("client.properties");
		mockPropertiesReader.readProperties();
		
		assertFalse("", mockPropertiesReader.getProperties().isEmpty());
	}
	
	
	
}
