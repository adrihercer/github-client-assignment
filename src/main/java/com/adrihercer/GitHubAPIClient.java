package com.adrihercer;

import java.io.IOException;

import com.adrihercer.client.GitContributorsClient;
import com.adrihercer.client.GitReposClient;
import com.adrihercer.client.beans.Contributor;
import com.adrihercer.client.beans.Repository;
import com.adrihercer.client.print.ContributorsCommandLinePrinter;
import com.adrihercer.client.print.RepositoryCommandLinePrinter;
import com.adrihercer.client.properties.PropertiesReader;

public class GitHubAPIClient {
	
	private String repositoriesApiUrl;
	private String contributorsApiUrl;
	private String token;
	private String username;
	
	public GitHubAPIClient(String token, String username) {
		PropertiesReader propertiesReader = new PropertiesReader("client.properties");
		try {
			propertiesReader.readProperties();
		} catch (IOException e) {
			System.out.println("Error reading client properties!");
		}
		
		repositoriesApiUrl = propertiesReader.getProperties().getProperty("github.apiurl.repositories", "");
		contributorsApiUrl = propertiesReader.getProperties().getProperty("github.apiurl.contributors", "");
		this.token = token;
		this.username = username;
		
	}
	
	public void execute() {
		GitReposClient reposClient = getGitReposClient();
		GitContributorsClient contributorsClient = getGitContributorsClient();
		
		reposClient.setOAuthToken(token);
		contributorsClient.setOAuthToken(token);
		Repository[] repos = reposClient.request(repositoriesApiUrl.replace(":username", username));
		
		RepositoryCommandLinePrinter rclp;
		ContributorsCommandLinePrinter cclp;
		
		for (Repository repo : repos) {
			rclp = new RepositoryCommandLinePrinter(repo);
			rclp.print();
			
			Contributor[] contributors = contributorsClient.request(contributorsApiUrl.replace(":owner", username).replace(":repo", repo.getName()));
			cclp = new ContributorsCommandLinePrinter(contributors);
			cclp.print();
		}
	}
	
	GitReposClient getGitReposClient() {
		return new GitReposClient();
	}
	
	GitContributorsClient getGitContributorsClient() {
		return new GitContributorsClient();
	}
	
}
