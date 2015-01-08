package gcode.commands;

import gcode.Commands;

public class G90Command extends Commands {

	public G90Command() {
		command = "G";
		commandnumber = 90;
	}

	public String getDescription() {
		return "Set to Absolute Positioning. Example: G90. All coordinates from now on are absolute relative to the origin of the machine.";
	}
}
