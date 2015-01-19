package gcode.commands;

import jgcodesender.Main;
import gcode.Commands;
import gui.Mainform;
import gui.Millingtable;

public class G0Command extends Commands {

	public G0Command() {
		command = "G";
		commandnumber = 0;
	}

	@Override
	public String getDescription() {
		return "Moves quickly to given coordinate. Example: G0 X0 or G0 X0 Y0";
	}

}
