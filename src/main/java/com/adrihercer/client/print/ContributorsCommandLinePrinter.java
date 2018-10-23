package com.adrihercer.client.print;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.adrihercer.client.beans.Contributor;

public class ContributorsCommandLinePrinter extends BasePrinter<Contributor[]> {
	
	public ContributorsCommandLinePrinter(Contributor[] contributors) {
		setElements(contributors);
	}

	@Override
	public void print() {
		List<Contributor> contributors = Arrays.asList(getElements());
		
		Comparator<Contributor> comparator = (c1, c2) -> c1.getTotal().compareTo(c2.getTotal());
	     
		contributors.sort(comparator.reversed());
		
		contributors.stream().forEach(contributor->System.out.println(contributor.toString()));
	}

}
