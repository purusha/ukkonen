package it.bio.tree;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TreeTest {
	private AbstractTree tree;
	private String s;
	
	@Before
	public void setup() {
		s = "cbasdcbashdbclashdbclasdbcasbduqasdkasudvkauwsbdlasdblasbdlasbfiasdfieuyqweuiyashbczbcbsdbdfjhs";
//		s = "adaadac";
		
//		tree = new Tree(s + "$");		
		tree = new ImprovedTree(s + "$");
		
		System.out.println(tree.deepPrint().toString());
	}
	
	@Test
	public void simple() {
	    for (int i = 0; i < s.length(); i++) {
	    	List<Integer> result = tree.search(String.valueOf(s.charAt(i)));
	    	
	    	Assert.assertFalse(result.isEmpty());
	    }
	}
	
	@Test
	public void enhanced() {
		for (int i = 0; i < s.length(); i++) {
			for (int j = 1; j < i + 2; j++) {
				final String suffix = s.substring(j - 1, i);

				List<Integer> result = tree.search(suffix);
				Assert.assertFalse(result.isEmpty());
			}
		}
	}
	
}
