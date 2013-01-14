package it.bio.builder;

import it.bio.tree.node.EdgeNode;
import it.bio.tree.node.ExplicitNode;
import it.bio.tree.node.LeafNode;
import it.bio.tree.node.Node;
import it.bio.tree.node.RootNode;
import junit.framework.Assert;

import org.junit.Test;

public class PathFinderTest {
	
	@Test
	public void findLeafThroughWholeTree1() {
		final RootNode root = buildTree();
		
		//find
		Node existPath = root.existPath("a");
		
		Assert.assertNotNull(existPath);
		Assert.assertEquals(ExplicitNode.class, existPath.getClass());
		Assert.assertEquals("E", existPath.getLabel());
	}

	@Test
	public void findLeafThroughWholeTree1_2() {
		final RootNode root = buildTree();
		
		//find
		Node existPath = root.existPath("d");
		
		Assert.assertNotNull(existPath);
		Assert.assertEquals(EdgeNode.class, existPath.getClass());
		Assert.assertEquals("daa", existPath.getLabel());
	}

	@Test
	public void findLeafThroughWholeTree1_3() {
		final RootNode root = buildTree();
		
		//find
		Node existPath = root.existPath("da");
		
		Assert.assertNotNull(existPath);
		Assert.assertEquals(EdgeNode.class, existPath.getClass());
		Assert.assertEquals("daa", existPath.getLabel());
	}

	@Test
	public void findLeafThroughWholeTree1_4() {
		final RootNode root = buildTree();
		
		//find
		Node existPath = root.existPath("daa");
		
		Assert.assertNotNull(existPath);
		Assert.assertEquals(LeafNode.class, existPath.getClass());
		Assert.assertEquals("2", existPath.getLabel());
	}

	@Test
	public void findLeafThroughWholeTree1_5() {
		final RootNode root = buildTree();
		
		//find
		Node existPath = root.existPath("daaa");
		
		Assert.assertNull(existPath);
	}

	@Test
	public void findLeafThroughWholeTree1_noexist_1() {
		final RootNode root = buildTree();
		
		//find
		Node existPath = root.existPath("ab");
		
		Assert.assertNull(existPath);
	}

	@Test
	public void findLeafThroughWholeTree1_noexist_2() {
		final RootNode root = buildTree();
		
		//find
		Node existPath = root.existPath("c");
		
		Assert.assertNull(existPath);
	}
	
	@Test
	public void findLeafThroughWholeTree1_noexist_3() {
		final RootNode root = buildTree();
		
		//find
		Node existPath = root.existPath("ac");
		
		Assert.assertNull(existPath);
	}	

	@Test
	public void findLeafThroughWholeTree1_noexist_4() {
		final RootNode root = buildTree();
		
		//find
		Node existPath = root.existPath("aac");
		
		Assert.assertNull(existPath);
	}	
	
	@Test
	public void findLeafThroughWholeTree1_noexist_5() {
		final RootNode root = buildTree();
		
		//find
		Node existPath = root.existPath("adaac");
		
		Assert.assertNull(existPath);
	}	

	@Test
	public void findLeafThroughWholeTree2() {
		final RootNode root = buildTree();
		
		//find
		Node existPath = root.existPath("ad");
		
		Assert.assertNotNull(existPath);
		Assert.assertEquals(EdgeNode.class, existPath.getClass());
		Assert.assertEquals("daa", existPath.getLabel());
	}

	@Test
	public void findLeafThroughWholeTree3() {
		final RootNode root = buildTree();
		
		//find
		Node existPath = root.existPath("ada");
		
		Assert.assertNotNull(existPath);
		Assert.assertEquals(EdgeNode.class, existPath.getClass());
		Assert.assertEquals("daa", existPath.getLabel());
	}

	@Test
	public void findLeafThroughWholeTree4() {
		final RootNode root = buildTree();
		
		//find
		Node existPath = root.existPath("adaa");
		
		Assert.assertNotNull(existPath);
		Assert.assertEquals(LeafNode.class, existPath.getClass());
		Assert.assertEquals("1", existPath.getLabel());
	}
	
	@Test
	public void findLeafThroughWholeTree5() {
		final RootNode root = buildTree();
		
		//find
		Node existPath = root.existPath("aa");
		
		Assert.assertNotNull(existPath);
		Assert.assertEquals(LeafNode.class, existPath.getClass());
		Assert.assertEquals("3", existPath.getLabel());
	}

	/*
	 * no test method under
	 */
	
	public static RootNode buildTree() {
		//1
		final RootNode root = new RootNode();
		
		//2
		EdgeNode a = attachEdgenode(root, "a");
		EdgeNode daa = attachEdgenode(root, "daa");
		
		//3
		ExplicitNode e = Builder.buildExplicit();
		e.setPreviousNode(a);
		a.addChildNode(e);
		
		attachLeafNode(daa, "2");
		
		//4
		EdgeNode a_e = attachEdgenode(e, "a");
		EdgeNode daa_e = attachEdgenode(e, "daa");		
		
		//5
		attachLeafNode(a_e, "3");
		attachLeafNode(daa_e, "1");
		return root;
	}

	public static void attachLeafNode(EdgeNode daa, final String label) {
		LeafNode due = Builder.buildLeaf(label);
		
		due.setPreviousNode(daa);
		daa.addChildNode(due);
	}

	public static EdgeNode attachEdgenode(final Node root, final String label) {
		final EdgeNode edge = Builder.buildEdge(label);
		
		edge.setPreviousNode(root);
		root.addChildNode(edge);
		
		return edge;
	}
	
}
