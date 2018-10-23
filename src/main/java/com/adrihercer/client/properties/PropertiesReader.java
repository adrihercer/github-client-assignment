package com.adrihercer.client.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	
	private String fileName;
	private Properties properties;
	
	public PropertiesReader(String fileName) {
		this.fileName = fileName;
	}

	public void readProperties() throws IOException {
		properties = new Properties();
		
		InputStream propertiesInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		
		if (propertiesInputStream != null) {
			try {
				properties.load(propertiesInputStream);
			} finally {
				propertiesInputStream.close();
			}
		} else {
			System.out.println("File '" + fileName + "' was not found in the jar file!");
		}
	}
	
	public Properties getProperties() {
		return properties;
	}
}
