package com.adrihercer.client.beans;

import java.util.Map;

public class Contributor {
	
	private Map<String, String> author;
	private Integer total;
	
	public Map<String, String> getAuthor() {
		return author;
	}
	
	public void setAuthor(Map<String, String> author) {
		this.author = author;
	}
	
	public Integer getTotal() {
		return total;
	}
	
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public String toString() {
		return total + "\t" + author.getOrDefault("login", "");
	}
}
