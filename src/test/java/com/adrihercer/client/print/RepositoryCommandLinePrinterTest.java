package com.adrihercer.client.print;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.adrihercer.client.beans.Repository;

public class RepositoryCommandLinePrinterTest {

	@Test
	public void test_PrintRepository() {
		Repository repository = new Repository();
		
		RepositoryCommandLinePrinter rclp = new RepositoryCommandLinePrinter(repository);
		
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		rclp.print();
		
		StringBuilder sb = new StringBuilder("");
		sb.append("--------------------------------------");
		sb.append(System.getProperty("line.separator"));
		sb.append(repository.getName());
		sb.append(System.getProperty("line.separator"));
		sb.append("--------------------------------------");
		sb.append(System.getProperty("line.separator"));

		assertEquals("", sb.toString(), os.toString());
		
		System.setOut(System.out);
	}
}
