package com.adrihercer.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class BaseClient {
	
	private String oAuthToken = "";
	
	public void setOAuthToken(String oAuthToken) {
		this.oAuthToken = oAuthToken;
	}
	
	public abstract <T> T request(String url);

	protected <T> T generateResponse(HttpResponse response, Class<T> classType) throws IOException {
		StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        
        int statusCode = statusLine.getStatusCode();
        
        if (statusCode >= 300 || statusCode == 202) {
            throw new HttpResponseException(
            		statusCode,
                    statusLine.getReasonPhrase());
        }
        
        if (entity == null) {
            throw new ClientProtocolException("Response contains no content");
        }
        
        Gson gson = new GsonBuilder().create();
        ContentType contentType = ContentType.getOrDefault(entity);
        Charset charset = contentType.getCharset();
        Reader reader = new InputStreamReader(entity.getContent(), charset);
        T responseObject = gson.fromJson(reader, classType);
        reader.close();
        
        return responseObject;
	}
	
	protected <T> T executeRequest(String url, ResponseHandler<T> handler) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = getHttpClient();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Authorization", "token " + oAuthToken);

		return httpclient.execute(httpGet, handler);
	}
	
	CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}
}
