package it.bio.tree.node;

import it.bio.builder.PathFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExplicitNode extends Node {
	private static final String E = "E";
	
	private final List<Node> childs;
	private Node sl = null;

	public ExplicitNode() {
		super(E);
		
		childs = new ArrayList<Node>();
	}
	
	public boolean hasSL() {
		return sl != null;
	}
	
	public Node getSL() {
		return sl;
	}

	public void setSL(Node sl) {
		this.sl = sl;
	}

	@Override
	public void addChildNode(Node node) {
		childs.add(node);
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
