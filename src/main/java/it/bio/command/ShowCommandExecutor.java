package it.bio.command;

import it.bio.tree.AbstractTree;

public class ShowCommandExecutor implements CommandExecutor {

	@Override
	public void execute(AbstractTree tree) {
	    final String deepPrint = tree.deepPrint().toString();	    
	    
	    System.out.println("");
	    System.out.println(deepPrint);
	}

}
