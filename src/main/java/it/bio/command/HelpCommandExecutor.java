package it.bio.command;

import it.bio.tree.AbstractTree;

import java.util.Arrays;
import java.util.List;

public class HelpCommandExecutor implements CommandExecutor {

	@Override
	public void execute(AbstractTree tree) {
		System.out.println("available commands and it's alias:");	
		for (CommandType commandType : CommandType.values()) {
			List<String> asList = Arrays.asList(commandType.getAliases());
			
			System.out.println(commandType.ordinal() + ") " +  asList.toString());
		}		
	}

}
