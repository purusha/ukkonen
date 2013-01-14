package it.bio.tree.node;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExplicitNodeTest {
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void retriveFullPath() {
		//instrument
		final RootNode r = new RootNode();
		final EdgeNode en1 = new EdgeNode("abc");
		
		r.addChildNode(en1);
		en1.setPreviousNode(r);
		
		final ExplicitNode exn1 = new ExplicitNode();
		final EdgeNode en2 = new EdgeNode("def");
		exn1.addChildNode(en2);
		en2.setPreviousNode(en1);
		
		final ExplicitNode exn2 = new ExplicitNode();
		en2.addChildNode(exn2);
		exn2.setPreviousNode(en2);
		
		//run
		Assert.assertEquals("abcdef", exn2.getFullLabel().toString());
	}

}
