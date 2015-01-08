package gcode.commands;

import gcode.Commands;

public class M18Command extends Commands {

	public M18Command() {
		command = "M";
		commandnumber = 18;
	}

	public String getDescription() {
		return "M18. Motors Off.";
	}
}
