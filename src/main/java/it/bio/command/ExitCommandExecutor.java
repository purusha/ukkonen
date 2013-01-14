package it.bio.command;

import it.bio.tree.AbstractTree;

public class ExitCommandExecutor implements CommandExecutor {

	@Override
	public void execute(AbstractTree tree) {
		System.out.println("bye");	
		System.exit(0);
	}

}
