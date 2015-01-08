package gcode;

import gcode.commands.*;

public class GCodeFactory {

	int x = 10;

	public static Commands motorsoff() {
		return new M18Command();
	}

	public static Commands queryposition() {
		return new M114Command();
	}

	public static Commands movetoX(Float x) {
		Commands ret = new G0Command();
		ret.args = "X" + x;
		return ret;
	}

	public static Commands movetoY(Float y) {
		Commands ret = new G0Command();
		ret.args = "Y" + y;
		return ret;
	}

	public static Commands movetoZ(Float z) {
		Commands ret = new G0Command();
		ret.args = "Z" + z;
		return ret;
	}

	public static Commands movetoXY(Float x, Float y) {
		Commands ret = new G0Command();
		ret.args = "X " + x + " Y " + y;
		return ret;
	}

	public static Commands moveto(Float x2, Float y, Float z) {
		Commands ret = new G0Command();
		ret.args = "X" + x2 + " Y" + y + " Z" + z + "\n";
		return ret;

	}

}