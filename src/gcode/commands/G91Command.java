package gcode.commands;

import gcode.Commands;

public class G91Command extends Commands {

	public G91Command() {
		command = "G";
		commandnumber = 91;
	}

	public String getDescription() {
		return "Set to Relative Positioning. Example: G91. All coordinates from now on are relative to the last position.";
	}
}
