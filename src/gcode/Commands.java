package gcode;

import jgcodesender.Main;
import gcode.commands.G1Command;
import gcode.commands.GCodeStyle;

public abstract class Commands {

	protected String args = "";
	protected String command = "";
	protected int commandnumber = -1;
	protected String rawcommand = "";
	public boolean executedSuccessfully = false;

	public String getCommand() {
		if (Main.getInstance()._gcodestyle.trailingZero() && commandnumber < 10)
			return command + "0" + commandnumber;
		else
			return command + commandnumber;
	}

	public boolean parse(String command) {
		this.rawcommand = command;
		// System.out.println("command " + this.command + " commandnumber: "
		// + this.commandnumber + " line: " + command + " " + this.toString());
		// System.out.println("replacing" + (this.command +
		// this.commandnumber));

		args = command.trim().toUpperCase()
				.replace(this.command + this.commandnumber, "");
		// System.out.println("replacing"
		// + (this.command + "0" + this.commandnumber));
		args = args.trim().toUpperCase()
				.replace(this.command + "0" + this.commandnumber, "");

		args = args.trim();
		// System.out.println("args: " + args);

		return true;
	}

	public String getArgs() {
		return args;
	}

	public String getFullCommand() {
		return getCommand() + " " + getArgs();
	}

	public abstract String getDescription();

	public void updateUI() {
	}
}
