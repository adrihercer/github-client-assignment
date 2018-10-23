package com.adrihercer.client;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;

import com.adrihercer.client.beans.Contributor;

public class GitContributorsClient extends BaseClient {

	@Override
	public Contributor[] request(String url) {
		
		Contributor[] response = new Contributor[0];
		
		ResponseHandler<Contributor[]> handler = new ResponseHandler<Contributor[]>() {

		    public Contributor[] handleResponse(
		            final HttpResponse response) throws IOException {
		        return generateResponse(response, Contributor[].class);
		    }
		};
		
		try {
			response = executeRequest(url, handler);
		} catch (IOException e) {
			if (e instanceof HttpResponseException) {
				HttpResponseException ex = (HttpResponseException) e;
				if (ex.getStatusCode() == 202) {
					response = request(url);
				} else {
					System.out.println("An error ocurred when gathering the contributors data from GitHub: " + ex.getStatusCode());
				}
			} else {
				System.out.println("An error ocurred when gathering the contributors data from GitHub!");
			}
		}
		
		return response;
	}
}
