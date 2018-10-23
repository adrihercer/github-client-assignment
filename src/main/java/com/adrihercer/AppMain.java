package com.adrihercer;

public class AppMain {
	
	public static void main(String args[]) {
		
		final String gitHubOauthToken = System.getenv("GITHUB_OAUTH_TOKEN");
		
		if (gitHubOauthToken == null || gitHubOauthToken.isEmpty()) {
			System.out.println("No environment variable 'GITHUB_OAUTH_TOKEN' has been set!");
			return;
		}
		
		String username = "";
		
		if (args.length == 0) {
			System.out.println("No username parameter has been provided!");
			return;
		} else {
			username = args[0];
		}
		
		GitHubAPIClient gitHubAPIClient = new GitHubAPIClient(gitHubOauthToken, username);
		gitHubAPIClient.execute();
	}
}
