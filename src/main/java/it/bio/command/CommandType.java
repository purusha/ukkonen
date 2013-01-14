package it.bio.command;

import org.apache.commons.lang3.StringUtils;

public enum CommandType {
	HELP(HelpCommandExecutor.class, "help"),
	SEARCH(SearchCommandExecutor.class, "search", "find"),
	COMPARE(CompareCommandExecutor.class, "compare"),
	SHOW(ShowCommandExecutor.class, "show"),
	EXIT(ExitCommandExecutor.class, "exit", "bye", "end");
	
	private final String[] alias;
	private final Class<? extends CommandExecutor> commandExecutor;

	private CommandType(Class<? extends CommandExecutor> commandExecutor, String ... alias) {
		this.alias = alias;
		this.commandExecutor = commandExecutor;		
	}
	
	private CommandExecutor getAlias(String command) {
		if(StringUtils.join(alias).contains(command)) {
			try {
				return commandExecutor.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public String[] getAliases() {
		return alias;
	}

	public static CommandExecutor build(String c) {
		final String command = c.toLowerCase();
		
		for (CommandType commandType : CommandType.values()) {
			final CommandExecutor ce = commandType.getAlias(command);
			
			if (ce != null) {
				return ce;
			}
		}
		
		return null;
	}

}
