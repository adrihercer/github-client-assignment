package com.adrihercer.client;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;

import com.adrihercer.client.beans.Repository;

public class GitReposClient extends BaseClient {
	
	public Repository[] request(String url) {
		
		Repository[] response = new Repository[0];
		
		ResponseHandler<Repository[]> handler = new ResponseHandler<Repository[]>() {

		    public Repository[] handleResponse(
		            final HttpResponse response) throws IOException {
		        return generateResponse(response, Repository[].class);
		    }
		};
		
		try {
			response = executeRequest(url, handler);
		} catch (IOException e) {
			System.out.println("An error ocurred when gathering the repositories data from GitHub!");
		}

		return response;
	}
}
