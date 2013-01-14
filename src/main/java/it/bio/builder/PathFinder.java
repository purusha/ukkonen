package it.bio.builder;

import it.bio.tree.node.ExplicitNode;
import it.bio.tree.node.Node;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class PathFinder implements PathChecker {
	
	private List<Node> nodes;

	public PathFinder(List<Node> nodes) {
		this.nodes = nodes;
	}

	@Override
	public Node existPath(String path) {
		final List<Node> partialNodes = new ArrayList<Node>();
		Node minorEdgeLabel = null;
		Node result = null;
		
		for (Node n : nodes) {
			final Node existPath = n.existPath(path);
			
			if (existPath != null) {
				result = existPath;
				break;
			} else {
				final Node explicit = n.getChilds().get(0);
				
				if (path.startsWith(n.getLabel()) && explicit.getClass().equals(ExplicitNode.class)) {
					partialNodes.addAll(explicit.getChilds());
					
					if (minorEdgeLabel == null || minorEdgeLabel.getLabel().length() > n.getLabel().length()) {
						minorEdgeLabel = n;
					}
				}							
			}
		}
		
		return result == null ? deepSearch(partialNodes, minorEdgeLabel, path) : result;
	}

	private Node deepSearch(final List<Node> partialNodes, final Node minorEdgeLabel, final String path) {
		if (minorEdgeLabel == null || partialNodes.isEmpty()) {
			return null;
		} else {
			final String newPath = StringUtils.difference(minorEdgeLabel.getLabel(), path);
			
			return new PathFinder(partialNodes).existPath(newPath);
		}
	}
	
	/*
	@Override
	public Node existPath(String path) {
		Node result = null;
		
		for (Node n : nodes) {
			final Node existPath = n.existPath(path);
			
			if (existPath != null) {
				result = existPath;
				break;
			}
		}
		
		return result == null ? deepSearch(path) : result;
	}

	private Node deepSearch(String path) {
		final List<Node> partialNodes = new ArrayList<Node>();		
		Node minorEdgeLabel = null;
		
		for (Node n : nodes) {
			final Node explicit = n.getChilds().get(0);
			
			if (path.startsWith(n.getLabel()) && explicit.getClass().equals(ExplicitNode.class)) {
				partialNodes.addAll(explicit.getChilds());
				
				if (minorEdgeLabel == null || minorEdgeLabel.getLabel().length() > n.getLabel().length()) {
					minorEdgeLabel = n;
				}
			}			
		}
		
		if (minorEdgeLabel == null || partialNodes.isEmpty()) {
			return null;
		} else {
			String newPath = StringUtils.difference(minorEdgeLabel.getLabel(), path);
			
			return new PathFinder(partialNodes).existPath(newPath);
		}
	} 
	*/

}
