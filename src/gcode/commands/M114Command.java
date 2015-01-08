package gcode.commands;

import gcode.Commands;

public class M114Command extends Commands {

	public M114Command() {
		command = "M";
		commandnumber = 114;
	}

	public String getDescription() {
		return "M114. Query current position.";
	}
}
