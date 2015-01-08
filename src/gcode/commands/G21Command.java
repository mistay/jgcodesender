package gcode.commands;

import jgcodesender.Main;
import gcode.Commands;

public class G21Command extends Commands {

	public G21Command() {
		command = "G";
		commandnumber = 21;
	}

	public String getDescription() {
		return "Set Units to Millimeters. Example: G21 .. Units from now on are in millimeters.";
	}

	@Override
	public void updateUI() {
		Main.getInstance()._mainform.setMachineUnits(true);
	}
}
