package gcode.commands;

import gcode.Commands;

public class M3Command extends Commands {

	public M3Command() {
		command = "M";
		commandnumber = 3;
	}

	public String getDescription() {
		return "M3: Spindle on (clockwise rotation). Example: M3 S4000 The spindle is turned on with a speed of 4000 RPM.";
	}
}
