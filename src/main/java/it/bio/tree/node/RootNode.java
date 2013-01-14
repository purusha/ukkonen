package it.bio.tree.node;

import it.bio.builder.PathFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RootNode extends Node {
	private static final String R = "R";
	
	private final List<Node> childs;

	public RootNode() {
		super(R);
		
		childs = new ArrayList<Node>();
	}

	@Override
	public void addChildNode(Node child) {
		childs.add(child);
	}

	@Override
	public void setPreviousNode(Node node) {
		throw new RuntimeException("Can't add previous node to Root");		
	}

	@Override
	public List<Node> getChilds() {
		return Collections.unmodifiableList(childs);
	}

	@Override
	protected Node innerExistPath(String path) {
		return new PathFinder(childs).existPath(path);
	}
}
