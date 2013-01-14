package it.bio;

import it.bio.command.CommandExecutor;
import it.bio.command.CommandType;
import it.bio.command.ExitCommandExecutor;
import it.bio.command.SearchCommandExecutor;
import it.bio.tree.AbstractTree;
import it.bio.tree.ImprovedTree;

import java.util.Arrays;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Starter {
	private static final String SPACE = " ";
	private static final String a = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	private final static String t = "$";
	
	public static void main(String[] args) {
		final Scanner sc = new Scanner(System.in);
	
		String s = null;
		Boolean containsOnly = null;
		
		while(containsOnly == null || !containsOnly){
			if (containsOnly != null) {
				System.out.println("the string must contain only the following characters: [a-z, A-Z]");
			}
			
			System.out.println("insert a string: ");
			s = sc.nextLine();
			containsOnly = StringUtils.containsOnly(s, a);
		}
		
		final AbstractTree tree = new ImprovedTree(s + t);
		CommandExecutor current = null;
		
		while(current == null || !(current instanceof ExitCommandExecutor)) {			
			System.out.println("");
			System.out.println("> ");
			
			final String line = sc.nextLine();
			
			s = getCommandParameter(line, 0);		
			current = CommandType.build(s);
			
			if (current == null) {
				System.out.println("command not found: '" + s + "' ... type 'help'");
			} else{	
				if (current instanceof SearchCommandExecutor) {
					final String[] patterns = getParameters(line);
					
					((SearchCommandExecutor) current).setSearch(patterns);
				}
				
				current.execute(tree);				
			}			
		}		
	}

	private static String[] getParameters(final String line) {
		final String[] split = line.trim().split(SPACE);
		
		return Arrays.copyOfRange(split, 1, split.length);
	}

	private static String getCommandParameter(final String line, final int numberOf) {
		return line.trim().split(SPACE)[numberOf];
	}
}
