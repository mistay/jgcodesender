package gcode.commands;

import main.Main;
import gcode.Commands;

public class G1Command extends Commands {
	public G1Command() {
		command = "G";
		commandnumber = 1;
	}

	public String getDescription() {
		return "Moves slowly to given coordinate. Example: G1 X0 or G1 X0 Y0";
	}

	Float x = null;
	Float y = null;

	@Override
	public boolean parse(String command) {
		boolean ret = super.parse(command);

		int pos;
		if ((pos = args.indexOf("X")) > -1) {
			// found "X"
			int end = args.indexOf(" ", pos);
			if (end == -1)
				end = args.length();

			x = Float.parseFloat(args.substring(pos + 1, end));
		}

		if ((pos = args.indexOf("Y")) > -1) {
			// found "Y"
			int end = args.indexOf(" ", pos);
			if (end == -1)
				end = args.length();
			y = Float.parseFloat(args.substring(pos + 1, end));

		}

		return ret;

	}

	public void updateUI() {
		Main.getInstance()._mainform.setGcodetarget(x, y, null);
	}
}
