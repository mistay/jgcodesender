package gcode.commands;

import gcode.Commands;

public class G1Command extends Commands {
	public G1Command() {
		command = "G";
		commandnumber = 1;
	}

	public String getDescription() {
		return "Moves slowly to given coordinate. Example: G1 X0 or G1 X0 Y0";
	}

}
