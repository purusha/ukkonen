package it.bio;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;

public class Snippet {
	
	private final String string = "ciao mamma";
	
	@Test
	public void basicTestUsage() {
		String first = "adaa";
		String second = "a";
		
		String difference = StringUtils.difference(second, first);
				
		Assert.assertEquals("daa", difference);
		Assert.assertEquals("a", second);
	}
	
	@Test
	public void basicTestUsage2() {
		String first = "addaa";
		String second = "daa";
		
		
		String substringBefore = StringUtils.substringBefore(first, second);
		
		Assert.assertEquals("ad", substringBefore);
	}
	
	@Test
	public void sbInsert() {
		final StringBuilder sb = new StringBuilder();

		sb.insert(0, "2");
		sb.insert(0, "1");
		sb.insert(0, "0");
		
		Assert.assertEquals("012", sb.toString());
	}
	
	@Test
	public void testPair() {
		final MutablePair<String, Integer> of = 
			MutablePair.of(string, string.length());
		
		System.out.println(of);
		
		of.setLeft(string + string);
		of.setRight(of.getLeft().length());
		
		System.out.println(of);
	}
	
	@Test
	public void testNullPair() {
		final MutablePair<String, Integer> of = 
			MutablePair.of(null, 1);
		
		System.out.println(of);
		
		of.setLeft(string + string);
		of.setRight(of.getLeft().length());
		
		System.out.println(of);
	}	
}
