package it.bio.tree.node;

import java.util.Collections;
import java.util.List;

public class LeafNode extends Node {
	
	public LeafNode(String label) {
		super(label);
	}

	@Override
	public void addChildNode(Node node) {
		throw new RuntimeException("Can't add node to Leaf");
	}

	@Override
	public List<Node> getChilds() {
		return Collections.emptyList();
	}
	
	@Override
	public Node existPath(String path) {
		return null; //XXX leaf node cannot have path beginning from
	}

	@Override
	protected Node innerExistPath(String path) {
		return null; //XXX leaf node cannot have path beginning from
	}
}
