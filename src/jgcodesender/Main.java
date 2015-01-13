package jgcodesender;

import gcode.Gcodereader;
import gcode.Gcodesender;
import gcode.commands.GCodeStyle;
import gui.Mainform;

import communication.Test;

public class Main {

	private static Main _instance = null;

	public static boolean isInitialized() {
		return _instance != null;
	}

	public static Main getInstance() {
		if (_instance == null) {
			_instance = new Main();
		}
		return _instance;
	}

	public Mainform _mainform = null;
	public Test _test = null;
	public Gcodereader _gcodereader = null;
	public GCodeStyle _gcodestyle = null;
	public Gcodesender _gcodesender = null;

	private Main() {

		_mainform = new Mainform();

		_test = new Test();
		_test.tryToConnect();
		_gcodestyle = new GCodeStyle() {

			@Override
			public boolean trailingZero() {
				return true;
			}
		};
		_gcodereader = new Gcodereader();
		_gcodesender = new Gcodesender();
		System.out.println("starting gcodesender");

		_gcodesender.start();
	}

	public static void main(String[] args) {
		Main.getInstance();
	}

}
