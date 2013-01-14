package it.bio.tree.df;

import it.bio.tree.node.LeafNode;
import it.bio.tree.node.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DepthFirstSearch {
	private List<Node> nodes = new CopyOnWriteArrayList<Node>();

	public DepthFirstSearch(Node n) {
		for (Node child : n.getChilds()) {
			nodes.add(child);
		}
	}
	
	public List<Integer> findLeafs() {
		return real(nodes, new ArrayList<Integer>());
	}

	private List<Integer> real(List<Node> childs, ArrayList<Integer> r) {		
		for (Node n : childs) {
			childs.remove(n);
			
			if (n.getClass().equals(LeafNode.class)) {
				r.add(Integer.valueOf(n.getLabel()));
			} else {
				childs.addAll(n.getChilds());
			}			
		}		
		
		if (childs.isEmpty()) {
			return r;
		} else {
			return real(childs, r);
		}
	}
	
}
