package com.adrihercer.client.print;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import org.junit.Test;

import com.adrihercer.client.beans.Contributor;

public class ContributorsCommandLinePrinterTest {

	@Test
	public void test_PrintContributors() {
		Contributor c1 = new Contributor();
		c1.setTotal(3);
		c1.setAuthor(new HashMap<>());
		c1.getAuthor().put("login", "contributor1");
		
		Contributor c2 = new Contributor();
		c2.setTotal(10);
		c2.setAuthor(new HashMap<>());
		c2.getAuthor().put("login", "contributor2");
		
		Contributor[] contributors = new Contributor[] {c1, c2};
		
		ContributorsCommandLinePrinter cclp = new ContributorsCommandLinePrinter(contributors);
		
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		cclp.print();
		
		StringBuilder sb = new StringBuilder("");
		sb.append(c2.toString());
		sb.append(System.getProperty("line.separator"));
		sb.append(c1.toString());
		sb.append(System.getProperty("line.separator"));

		assertEquals("", sb.toString(), os.toString());
		
		System.setOut(System.out);
	}
}
