package com.adrihercer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.adrihercer.client.GitContributorsClient;
import com.adrihercer.client.GitReposClient;
import com.adrihercer.client.beans.Contributor;
import com.adrihercer.client.beans.Repository;

public class GitHubAPIClientTest {

	@Test
	public void test_NewInstance() {
		GitReposClient gitReposClient = spy(new GitReposClient());
		GitContributorsClient gitContributorsClient = spy(new GitContributorsClient());
		
		GitHubAPIClient gitHubAPIClient = spy(new GitHubAPIClient("token1234567890", "adrihercer"));
		
		Repository repository = new Repository();
		repository.setName("repoName");
		
		Contributor contributor = new Contributor();
		contributor.setTotal(10);
		contributor.setAuthor(new HashMap<>());
		contributor.getAuthor().put("login", "contributor2");
		
		doReturn(gitReposClient).when(gitHubAPIClient).getGitReposClient();
		doReturn(gitContributorsClient).when(gitHubAPIClient).getGitContributorsClient();
		doReturn(new Repository[] {repository}).when(gitReposClient).request(any(String.class));
		doReturn(new Contributor[] {contributor}).when(gitContributorsClient).request(any(String.class));
		
		gitHubAPIClient.execute();
		
		ArgumentCaptor<String> reposUrl = ArgumentCaptor.forClass(String.class);
		verify(gitReposClient, times(1)).request(reposUrl.capture());
		
		ArgumentCaptor<String> contributorsUrl = ArgumentCaptor.forClass(String.class);
		verify(gitContributorsClient, times(1)).request(contributorsUrl.capture());
		
		assertEquals("", "https://api.github.com/users/adrihercer/repos", reposUrl.getValue());
		assertEquals("", "https://api.github.com/repos/adrihercer/repoName/stats/contributors", contributorsUrl.getValue());
		
		
		/*GitHubAPIClient c = new GitHubAPIClient();
		c.requestRepositories("adrihercer");*/
	}
	
	/*private class GitHubAPIClientWrapperTest extends GitHubAPIClient {
		
		public GitHubAPIClientWrapperTest(String token, String username, GitReposClient gitReposClient, GitContributorsClient gitContributorsClient) {
			super(token, username);
		}
	}*/
}
