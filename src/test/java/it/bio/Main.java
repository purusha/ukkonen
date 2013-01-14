package it.bio;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import it.bio.tree.*;

public class Main {

	public static void main(String[] args) {
		
		/*
		 	0)
		 	add pom.xml and upload project to github
		 
		 	1) check
		 	's' deve essere composta solo da 'qwertyuiopasdfghjklzxcvbnm'
		 	'c' non deve essere presente in 's'
		 	
		 	2)
		 	usare commons-cli
		 	
		 	3)
		 	si può esterndere facilmente per il caso ad n stringhe ?
		 	
		 	4) fornire una bash da cui è possibile digitare
		 	
		 		> help
		 		> view string
		 		> preappend str (and re-init)
		 		> append str (and re-init)
		 		> delete first char
		 		> delete last char
		 		> 		 			 	
		 */
		
//		final String s = "adaadac";
//		final String s = "cbasdcbashdbclashdbclasdbcasbduqasdkasudvkauwsbdlasdblasbdlasbfiasdfieuyqweuiyashbczbcbsdbdfjhs";
		final String s = "ciaomammaguardacomemidiverto";
		final String c = "$";	 
		
		final String t = s + c;
		
		System.out.println("\n----------------------------------------------------------------\n");
	    final AbstractTree tree = new Tree(t);
	    System.out.println("\n----------------------------------------------------------------\n");
	    final AbstractTree itree = new ImprovedTree(t);	    
	    System.out.println("\n----------------------------------------------------------------\n");
	    
	    final String deepPrint_1 = tree.deepPrint().toString();	    
	    final String deepPrint_2 = itree.deepPrint().toString();
	    
	    if (!StringUtils.equals(deepPrint_1, deepPrint_2)) {
	    	System.out.println("deepPrint of tree's are different!\n\n" + deepPrint_1 + "\n\n" + deepPrint_2);
	    } else {
	    	System.out.println("deepPrint of tree's are equals: \n");
	    	System.out.println(deepPrint_1);
	    }
		
		System.out.println("\n----------------------------------------------------------------\n");

		for (String search : Arrays.asList("a", "c", "d", "r", "ac", "da", "ada", "ca", "ad", "dc", "aa", "dd", "cc", "aad", "daa", "cca")) {
			System.out.println("search '" + search + "'");
			System.out.println("tree)> " + tree.search(search) + "\titree)> " + itree.search(search));
			System.out.println("");
		}
		
		System.out.println("\n----------------------------------------------------------------\n");
				
	}

}
