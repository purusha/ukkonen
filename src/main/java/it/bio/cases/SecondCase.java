package it.bio.cases;

import it.bio.tree.node.EdgeNode;
import it.bio.tree.node.LeafNode;
import it.bio.tree.node.Node;

import static it.bio.builder.Builder.*;

public class SecondCase {
	private LeafNode leaf;
	
	public SecondCase(final Node toAttach, final String nodeLabel, final char edgeLabel) {
		final EdgeNode edge = buildEdge(String.valueOf(edgeLabel));		
		toAttach.addChildNode(edge);
		edge.setPreviousNode(toAttach);
		
		leaf = buildLeaf(nodeLabel);
		edge.addChildNode(leaf);
		leaf.setPreviousNode(edge);		
	}

	public LeafNode getLeaf() {
		return leaf;
	}
}
