package gcode.commands;

import gcode.Commands;

public class unknownCommand extends Commands {

	@Override
	public String getCommand() {
		return rawcommand;
	};

	public String getDescription() {
		return "unknown command";
	}
}
