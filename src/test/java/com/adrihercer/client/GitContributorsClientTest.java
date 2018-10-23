package com.adrihercer.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeaderElement;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.junit.Test;

import com.adrihercer.client.beans.Contributor;

public class GitContributorsClientTest {

	@Test
	public void test_GitHubResponse_Successful() throws ClientProtocolException, IOException {
		
		CloseableHttpResponse httpResponse = spy(mock(CloseableHttpResponse.class));
		CloseableHttpClient httpClient = new CloseableHttpClientTest(httpResponse);
		StatusLine statusLine = mock(StatusLine.class);
		HttpEntity httpEntity = mock(HttpEntity.class);
		Header header = mock(Header.class);
		BasicNameValuePair nameValuePair = new BasicNameValuePair("charset", "utf-8");
		BasicHeaderElement headerElement = new BasicHeaderElement("application/json", "charset=utf-8", new NameValuePair[] {nameValuePair});
		InputStream reposInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("contributors-response.json");
		GitContributorsClient gitContributorsClient = spy(new GitContributorsClient());
		
		doReturn(httpClient).when(gitContributorsClient).getHttpClient();
		doReturn(statusLine).when(httpResponse).getStatusLine();
		doReturn(httpEntity).when(httpResponse).getEntity();
		doReturn(200).when(statusLine).getStatusCode();
		doReturn(header).when(httpEntity).getContentType();
		doReturn(new HeaderElement[] {headerElement}).when(header).getElements();
		doReturn(reposInputStream).when(httpEntity).getContent();
		
		gitContributorsClient.setOAuthToken("testtoken1234567890");
		Contributor[] contributors = gitContributorsClient.request("testurl");
		
		assertEquals("", "adrihercer", contributors[0].getAuthor().getOrDefault("login", ""));
		assertEquals("", new Integer(2), contributors[0].getTotal());
	}
	
	@Test
	public void test_GitHubResponse_SuccessfulWithCode202() throws ClientProtocolException, IOException {
		
		CloseableHttpResponse httpResponse = spy(mock(CloseableHttpResponse.class));
		CloseableHttpClient httpClient = new CloseableHttpClientTest(httpResponse);
		StatusLine statusLine = mock(StatusLine.class);
		HttpEntity httpEntity = mock(HttpEntity.class);
		Header header = mock(Header.class);
		BasicNameValuePair nameValuePair = new BasicNameValuePair("charset", "utf-8");
		BasicHeaderElement headerElement = new BasicHeaderElement("application/json", "charset=utf-8", new NameValuePair[] {nameValuePair});
		InputStream reposInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("contributors-response.json");
		GitContributorsClient gitContributorsClient = spy(new GitContributorsClient());
		
		doReturn(httpClient).when(gitContributorsClient).getHttpClient();
		doReturn(statusLine).when(httpResponse).getStatusLine();
		doReturn(httpEntity).when(httpResponse).getEntity();
		when(statusLine.getStatusCode()).thenReturn(202).thenReturn(200);
		doReturn(header).when(httpEntity).getContentType();
		doReturn(new HeaderElement[] {headerElement}).when(header).getElements();
		doReturn(reposInputStream).when(httpEntity).getContent();
		
		gitContributorsClient.setOAuthToken("testtoken1234567890");
		Contributor[] contributors = gitContributorsClient.request("testurl");
		
		assertEquals("", "adrihercer", contributors[0].getAuthor().getOrDefault("login", ""));
		assertEquals("", new Integer(2), contributors[0].getTotal());
	}
	
	@Test
	public void test_GitHubResponse_NoSuccessful() throws ClientProtocolException, IOException {
		
		CloseableHttpResponse httpResponse = spy(mock(CloseableHttpResponse.class));
		CloseableHttpClient httpClient = new CloseableHttpClientTest(httpResponse);
		StatusLine statusLine = mock(StatusLine.class);
		HttpEntity httpEntity = mock(HttpEntity.class);
		GitContributorsClient gitContributorsClient = spy(new GitContributorsClient());
		
		doReturn(httpClient).when(gitContributorsClient).getHttpClient();
		doReturn(statusLine).when(httpResponse).getStatusLine();
		doReturn(httpEntity).when(httpResponse).getEntity();
		doReturn(404).when(statusLine).getStatusCode();
		
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		gitContributorsClient.setOAuthToken("testtoken1234567890");
		Contributor[] contributors = gitContributorsClient.request("testurl");
		
		assertEquals("", "An error ocurred when gathering the contributors data from GitHub: 404" + System.getProperty("line.separator"), os.toString());
		
		System.setOut(System.out);
	}
	
	@Test
	public void test_GitHubResponse_NoContent() throws ClientProtocolException, IOException {
		
		CloseableHttpResponse httpResponse = spy(mock(CloseableHttpResponse.class));
		CloseableHttpClient httpClient = new CloseableHttpClientTest(httpResponse);
		StatusLine statusLine = mock(StatusLine.class);
		GitContributorsClient gitContributorsClient = spy(new GitContributorsClient());
		
		doReturn(httpClient).when(gitContributorsClient).getHttpClient();
		doReturn(statusLine).when(httpResponse).getStatusLine();
		doReturn(null).when(httpResponse).getEntity();
		doReturn(200).when(statusLine).getStatusCode();
		
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		gitContributorsClient.setOAuthToken("testtoken1234567890");
		Contributor[] contributors = gitContributorsClient.request("testurl");
		
		assertEquals("", "An error ocurred when gathering the contributors data from GitHub!" + System.getProperty("line.separator"), os.toString());
		
		System.setOut(System.out);
	}
	
	private class CloseableHttpClientTest extends CloseableHttpClient {
		
		private CloseableHttpResponse httpResponse;
		
		public CloseableHttpClientTest(CloseableHttpResponse httpResponse) {
			this.httpResponse = httpResponse;
		}

		@Override
		public HttpParams getParams() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ClientConnectionManager getConnectionManager() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void close() throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected CloseableHttpResponse doExecute(HttpHost target, HttpRequest request, HttpContext context)
				throws IOException, ClientProtocolException {
			// TODO Auto-generated method stub
			return httpResponse;
		}
		
	}
}
