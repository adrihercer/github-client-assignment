package com.adrihercer.client.print;

import com.adrihercer.client.beans.Repository;

public class RepositoryCommandLinePrinter extends BasePrinter<Repository> {
	
	public RepositoryCommandLinePrinter(Repository repository) {
		setElements(repository);
	}
	
	public void print() {
		System.out.println("--------------------------------------");
		System.out.println(getElements().getName());
		System.out.println("--------------------------------------");
	}

}
