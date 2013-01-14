package it.bio.tree;

import java.util.ArrayList;
import java.util.List;

import it.bio.tree.df.DepthFirstPrinter;
import it.bio.tree.df.DepthFirstSearch;
import it.bio.tree.node.Node;
import it.bio.tree.node.RootNode;

public class AbstractTree {
	
	public static final String O = "(";
	public static final String C = ")";	

	protected final RootNode root;
	protected final String text;

	public AbstractTree(String text) {
		this.root = new RootNode();
		this.text = text;
	}
	
	public List<Integer> search(String search) {
		final List<Integer> result = new ArrayList<Integer>();
		final Node n = this.root.existPath(search);
		
		if (n != null) {
			if (n.isLeaf()) {
				result.add(Integer.valueOf(n.getLabel()));
			} else {
				result.addAll(
					new DepthFirstSearch(n).findLeafs()
				);
			}
		}
		
		return result;
	}

	public StringBuilder deepPrint() {
		return new DepthFirstPrinter(this.root).print();
	}	
	
	public String getText() {
		return this.text;
	}
}
