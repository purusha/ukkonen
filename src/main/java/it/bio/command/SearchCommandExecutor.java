package it.bio.command;

import it.bio.tree.AbstractTree;

import java.util.List;

public class SearchCommandExecutor implements CommandExecutor {
	
	private String[] search;

	@Override
	public void execute(AbstractTree tree) {
		for (String s : search) {
			final List<Integer> result = tree.search(s);
			
			System.out.println("searching '" + s + "' pattern ... result position: " + result);			
		}
	}
	
	public void setSearch(String ... s) {
		this.search = s;
	}

}
