package it.bio.command;

import it.bio.tree.AbstractTree;
import it.bio.tree.Tree;

import java.util.Arrays;

public class CompareCommandExecutor implements CommandExecutor {

	@Override
	public void execute(AbstractTree tree) {
		final String s = tree.getText();
		final StringBuilder sb = new StringBuilder("Compare of two tree of '" + s + "' as text has result: ");

		final AbstractTree _tree = new Tree(s);
		boolean same = true;
		
		for (int i = 0; i < s.length() && same; i++) {
			for (int j = 1; j < i + 2 && same; j++) {
				final String suffix = s.substring(j - 1, i);

				same = Arrays.equals(
					tree.search(suffix).toArray(new Integer[]{}), _tree.search(suffix).toArray(new Integer[]{})						
				);
			}
		}
		
		sb.append(same ? "'equal'" : "'not equal'");	
		
		System.out.println("\n" + sb.toString());	
	}
	
}
