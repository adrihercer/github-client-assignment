package com.adrihercer.client.beans;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class ContributorTest {

	@Test
	public void test_NewInstance() {
		Contributor contributor = new Contributor();
		contributor.setTotal(1);
		contributor.setAuthor(new HashMap<>());
		contributor.getAuthor().put("login", "testLogin");
		
		assertEquals("Test the new instance total", new Integer(1), contributor.getTotal());
		assertEquals("Test the new instance author", "testLogin", contributor.getAuthor().get("login"));
		assertEquals("Test Contributor toString() method", "1\ttestLogin", contributor.toString());
		
	}
}
