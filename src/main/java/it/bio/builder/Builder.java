package it.bio.builder;

import it.bio.tree.node.EdgeNode;
import it.bio.tree.node.ExplicitNode;
import it.bio.tree.node.LeafNode;

public class Builder {
	
	public static EdgeNode buildEdge(String label) {
		return new EdgeNode(label);
	}
	
	public static LeafNode buildLeaf(String label) {
		return new LeafNode(label);
	}
	
	public static ExplicitNode buildExplicit() {
		return new ExplicitNode();
	}
	
}
