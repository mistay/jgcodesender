package jgcodesender;

import java.io.File;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import communication.Test;
import millingmachines.Shapeoko2;
import gui.Mainform;
import gui.Settings;

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

	private Main() {

		_mainform = new Mainform();

		_test = new Test();
		_test.tryToConnect();
	}

	

	public static void main(String[] args) {
		Main.getInstance();
	}

}
