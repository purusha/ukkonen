package it.bio.log;

import it.bio.cases.AlgorithmCases;
import it.bio.tree.AbstractTree;

import java.util.ArrayList;
import java.util.List;

public class MyBuffer {
	
	private final StringBuffer sb;
	private final StringBuffer allSb;
	
	public MyBuffer() {
		sb = new StringBuffer();
		allSb = new StringBuffer();
	}

	public void append(String s) {
		sb.append(s);
		allSb.append(s);
	}

	public int indexOf(String s) {
		return sb.indexOf(s);
	}

	public int length() {
		return sb.length();
	}

	public void delete(int start, int end) {
		sb.delete(start, end);
	}
	
	@Override
	public String toString() {
		return sb.toString();
	}
	
	public String printStatisticsInformation() {
		final String all = allSb.toString();
		final StringBuilder stringBuilder = new StringBuilder("\n\nBuilding Statistics Information\n\n");
		
		final List<Integer> u = findAllOccurrence(all, "(1)");
		final List<Integer> uf = findAllOccurrence(all, "(1f)");
		final List<Integer> up = findAllOccurrence(all, "(1p)");
		stringBuilder.append("Append to leaf" + "[(1), (1f), (1p)]" + ": " + (u.size() + uf.size() + up.size()) + "\n");
		
		for (AlgorithmCases a : AlgorithmCases.values()) {
			final String guess = AbstractTree.O + a.getNumber() + AbstractTree.C;
			final List<Integer> occurrences = findAllOccurrence(all, guess);
			
			stringBuilder.append(
				a.getLabel() + guess + ": " + occurrences.size() + "\n"
			);
		}
		
		return stringBuilder.toString();
	}
	
	private List<Integer> findAllOccurrence(String word, String guess) {
		final List<Integer> result = new ArrayList<Integer>();
		int index = word.indexOf(guess);
		
		while(index >= 0) {
		   result.add(index);
		   index = word.indexOf(guess, index+1);
		}		
		
		return result;
	}

}
