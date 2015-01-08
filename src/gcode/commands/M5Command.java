package gcode.commands;

import gcode.Commands;

public class M5Command extends Commands {

	public M5Command() {
		command = "M";
		commandnumber = 5;
	}

	public String getDescription() {
		return "M5: Spindle Off (CNC specific). Example: M5. The spindle is turned off.";
	}
}
