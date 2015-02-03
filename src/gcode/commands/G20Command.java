package gcode.commands;

import main.Main;
import gcode.Commands;

public class G20Command extends Commands {

	public G20Command() {
		command = "G";
		commandnumber = 20;
	}

	public String getDescription() {
		return "Set Units to Inches. Example: G20 .. Units from now on are in inch.";
	}

	@Override
	public void updateUI() {
		Main.getInstance()._mainform.setMachineUnits(false);
	}
}
