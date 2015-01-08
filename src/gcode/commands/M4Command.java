package gcode.commands;

import gcode.Commands;

public class M4Command extends Commands {

	public M4Command() {
		command = "M";
		commandnumber = 4;
	}

	public String getDescription() {
		return "M4: Spindle on (counterclockwise rotation). Example: M4 S4000 The spindle is turned on with a speed of 4000 RPM.";
	}
}
