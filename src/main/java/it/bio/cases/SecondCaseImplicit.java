package it.bio.cases;

import static it.bio.builder.Builder.buildEdge;
import static it.bio.builder.Builder.buildExplicit;
import it.bio.tree.node.EdgeNode;
import it.bio.tree.node.ExplicitNode;
import it.bio.tree.node.LeafNode;
import it.bio.tree.node.Node;

import org.apache.commons.lang3.StringUtils;

public class SecondCaseImplicit {
	private LeafNode leaf;
	
	public SecondCaseImplicit(final EdgeNode edgeNode, final String suffix, final String nodeLabel, final char edgeLabel) {		
		final ExplicitNode explicitNode = buildExplicit();
		
		leaf = new SecondCase(explicitNode, nodeLabel, edgeLabel).getLeaf();
				
		final String difference = StringUtils.difference(suffix, edgeNode.getFullLabel().toString());
		final EdgeNode edge = buildEdge(difference);		
		explicitNode.addChildNode(edge);
		edge.setPreviousNode(explicitNode);
		
		final Node node = edgeNode.getChilds().get(0);		
		edge.addChildNode(node);
		node.setPreviousNode(edge);
		
		final String newNodeLabel = StringUtils.substringBefore(edgeNode.getLabel(), edge.getLabel());		
		edgeNode.resetLabel(newNodeLabel);

		edgeNode.addChildNode(explicitNode);
		explicitNode.setPreviousNode(edgeNode);
	}

	public LeafNode getLeaf() {
		return leaf;
	}	
}
