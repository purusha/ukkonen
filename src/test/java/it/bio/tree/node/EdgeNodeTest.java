package it.bio.tree.node;

import it.bio.builder.PathFinderTest;
import junit.framework.Assert;

import org.junit.Test;

public class EdgeNodeTest {
	
	@Test
	public void simple() {
		final EdgeNode edgeNode = new EdgeNode("a");
		final LeafNode child = new LeafNode("X");
		edgeNode.addChildNode(child);
		
		Node existPath = edgeNode.existPath("");
		Assert.assertNotNull(existPath);
		Assert.assertEquals(edgeNode, existPath);
		
		existPath = edgeNode.existPath("a");
		Assert.assertNotNull(existPath);
		Assert.assertEquals(child, existPath);

		existPath = edgeNode.existPath("b");
		Assert.assertNull(existPath);		
	}
	
	@Test
	public void complex() {
		final EdgeNode  edgeNode = new EdgeNode("adaa");
		final LeafNode child = new LeafNode("X");
		edgeNode.addChildNode(child);
		
		Node existPath = edgeNode.existPath("");
		Assert.assertNotNull(existPath);
		Assert.assertEquals(edgeNode, existPath);
		
		existPath = edgeNode.existPath("a");
		Assert.assertNotNull(existPath);
		Assert.assertEquals(edgeNode, existPath);

		existPath = edgeNode.existPath("b");
		Assert.assertNull(existPath);
		
		existPath = edgeNode.existPath("ad");
		Assert.assertNotNull(existPath);
		Assert.assertEquals(edgeNode, existPath);
		
		existPath = edgeNode.existPath("ada");
		Assert.assertNotNull(existPath);
		Assert.assertEquals(edgeNode, existPath);
		
		existPath = edgeNode.existPath("adaa");
		Assert.assertNotNull(existPath);
		Assert.assertEquals(child, existPath);		

		//Assert.assertNotNull(edgeNode.existPath("ab"));
		existPath = edgeNode.existPath("ab");
		Assert.assertNull(existPath);
		
		existPath = edgeNode.existPath("bd");
		Assert.assertNull(existPath);		
	}	
	
	@Test
	public void existPathOverload() {		
		RootNode root = PathFinderTest.buildTree();
		
		Node edge = root.existPath("ad");
		
		Node existPath = ((EdgeNode)edge).existPath("ad", "a");
		
		Assert.assertEquals(edge, existPath);
		
	}
	
}
