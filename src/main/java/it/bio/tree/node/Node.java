package it.bio.tree.node;

import it.bio.builder.PathChecker;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public abstract class Node implements PathChecker {

	private static final String O = "(";
	private static final String C = ")";
	private static final String Oq = "[";
	private static final String Cq = "]";
	
	private String label;
	private Node previous;

	public Node(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setPreviousNode(Node node) {
		this.previous = node;
	}
	
	public Node getPreviousNode() {
		return this.previous;
	}
	
	public String getValue() {
		final StringBuilder sb = new StringBuilder();
		final Node pn = getPreviousNode();
		
		sb.append(getLabel());
		if (this.getClass().equals(ExplicitNode.class)) {
			sb.append(this.toString().split("@")[1]);
		}
		
		sb.append(O).append(
			StringUtils.splitByWholeSeparator(
				this.getClass().getSimpleName(), 
				this.getClass().getSuperclass().getSimpleName()
			)[0]
		);
		
		sb.append(C).append(Oq);
		
		sb.append(pn != null ? pn.getLabel() : "");
		if (this.getPreviousNode() != null && this.getPreviousNode().getClass().equals(ExplicitNode.class)) {
			sb.append(this.getPreviousNode().toString().split("@")[1]);
		}
		
		sb.append(Cq);
		
		return sb.toString();
	}	
	
	public abstract void addChildNode(Node node);
	public abstract List<Node> getChilds();
	
	public StringBuilder getFullLabel() {
		final StringBuilder sb = new StringBuilder();
		
		Node c = this;
		while(c != null) {
			if (!c.getClass().equals(ExplicitNode.class) && !c.getClass().equals(RootNode.class)) {
				sb.insert(0, c.getLabel());				
			}
			
			c = c.getPreviousNode();
		}
		
		return sb;
	}
	
	@Override
	public Node existPath(String path) {
		if ("".equals(path)) {
			return this;
		} else {
			return innerExistPath(path); 
		}
	}

	protected abstract Node innerExistPath(String path);
	
	protected void append(char edgeLabel) {
		label = label.concat(String.valueOf(edgeLabel));
	}
	
	protected void resetLabel(String newLabel) {
		label = newLabel;
	}
		
	public boolean isLeaf() {
		return this instanceof LeafNode;
	}
	
	public boolean isExplicit() {
		return this instanceof ExplicitNode;
	}
	
	public boolean isImplicit() {
		return this instanceof EdgeNode;
	}

	public boolean isRoot() {
		return this instanceof RootNode;
	}
		
	public boolean isInternal() {
		return (isImplicit() || isExplicit() || isRoot());
	}
}
