package com.adrihercer;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

public class AppMainTest {
	
	@Rule
	public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

	@Test
	public void test_GitHubOAuthToken_NotFound() {
		environmentVariables.set("GITHUB_OAUTH_TOKEN", null);
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		AppMain.main(new String[] {});

		assertEquals("Test output when no GITHUB_OAUTH_TOKEN has been set", "No environment variable 'GITHUB_OAUTH_TOKEN' has been set!" + System.getProperty("line.separator"), os.toString());
		
		System.setOut(System.out);
	}
	
	@Test
	public void test_GitHubOAuthToken_FoundButEmpty() {
		environmentVariables.set("GITHUB_OAUTH_TOKEN", "");
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		AppMain.main(new String[] {});

		assertEquals("Test output when GITHUB_OAUTH_TOKEN is found but empty", "No environment variable 'GITHUB_OAUTH_TOKEN' has been set!" + System.getProperty("line.separator"), os.toString());
		
		System.setOut(System.out);
	}
	
	@Test
	public void test_UsernameParam_NotFound() {
		environmentVariables.set("GITHUB_OAUTH_TOKEN", "1234567890");
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		AppMain.main(new String[] {});

		assertEquals("Test output when username parameter is not found", "No username parameter has been provided!" + System.getProperty("line.separator"), os.toString());
		
		System.setOut(System.out);
	}
	
	@Test
	public void test_UsernameParam_Found() {
		environmentVariables.set("GITHUB_OAUTH_TOKEN", "1234567890");
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		AppMain.main(new String[] {"adrihercer"});

		/*assertEquals("Test output when GITHUB_OAUTH_TOKEN is found but empty", "No username parameter has been provided!" + System.getProperty("line.separator"), os.toString());
		
		System.setOut(System.out);*/
	}
}
