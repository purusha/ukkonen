package it.bio.builder;

import it.bio.tree.node.LeafNode;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.MutablePair;

public class PhasesCounter {
	
	private final Map<Integer, MutablePair<LeafNode, Integer>> counter;
	
	public PhasesCounter() {
		counter = new HashMap<Integer, MutablePair<LeafNode, Integer>>();
	}
	
	public void updateMax(int i, int j) {
		MutablePair<LeafNode, Integer> value = counter.get(i);
		
		if (value == null) {
			value = MutablePair.of(null, j);
			counter.put(i, value);
		} else {
			value.setRight(j);
		}
		
		updateMaxOfGraterThan(i, j);
	}

	private void updateMaxOfGraterThan(int i, int j) {
		for (Integer integer : counter.keySet()) {
			if (integer.intValue() > i) {
				counter.get(integer).setRight(j);
			}
		}		
	}
	
	public void addLeafNode(LeafNode ln) {
		final int i = Integer.valueOf(ln.getLabel());
		MutablePair<LeafNode, Integer> value = counter.get(i);
		
		if (value == null) {
			value = MutablePair.of(ln, null);
			counter.put(i, value);
		} else {
			value.setLeft(ln);
		}
	}	
	
	public LeafNode getLeaf(int i) {
		final MutablePair<LeafNode, Integer> value = counter.get(i);
		
		return value != null ? value.getLeft() : null;
	}

	public Integer getCount(int i) {
		final MutablePair<LeafNode, Integer> value = counter.get(i);
		
		Integer result = Integer.valueOf("-1");
		
		if (value != null) {
			Integer right = value.getRight();
			
			if (right != null) {
				result = right;
			}
		} 
		
		return result;
	}
}
