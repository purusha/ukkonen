package it.bio.cases;

import it.bio.tree.node.EdgeNode;
import it.bio.tree.node.LeafNode;

public class FirstCase {
	public FirstCase(LeafNode find, final char edgeLabel) {
		final EdgeNode previousNode = (EdgeNode)find.getPreviousNode();
		
		previousNode.append(edgeLabel);
	}
}
