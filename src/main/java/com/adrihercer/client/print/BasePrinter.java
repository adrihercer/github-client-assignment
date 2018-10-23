package com.adrihercer.client.print;

public abstract class BasePrinter<T> {

	private T elements;

	public void setElements(T elements) {
		this.elements = elements;
	}
	
	public T getElements() {
		return elements;
	}
	
	public abstract void print();
}
