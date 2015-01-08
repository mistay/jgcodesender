package gcode.commands;

import gcode.Commands;

public class M2Command extends Commands {

	public M2Command() {
		command = "M";
		commandnumber = 2;
	}

	public String getDescription() {
		return "M2: End of program. Example: M2. Program ends; execution may or may not return to program top.";
	}
}
