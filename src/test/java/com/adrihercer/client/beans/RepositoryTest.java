package com.adrihercer.client.beans;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RepositoryTest {

	@Test
	public void test_NewInstance() {
		Repository repository = new Repository();
		repository.setName("testName");
		
		assertEquals("Test the new instance name", "testName", repository.getName());
	}
}
