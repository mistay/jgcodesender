package gcode;

import gcode.commands.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import jgcodesender.Main;

public class Gcodereader {

	public boolean load(String filename) {
		boolean ret = false;
		try {
			InputStream ips = new FileInputStream(filename);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			while ((line = br.readLine()) != null) {
				parseLine(line);
			}
			br.close();
			ret = true;
		} catch (FileNotFoundException fnfe) {
			return false;
		} catch (Exception e) {
			System.out.println("error loading gcode: " + e.toString());
			e.getStackTrace();
		}

		parseResult = g0 + " G0 commands found." + "\n";
		parseResult += g1 + " G1 commands found." + "\n";
		parseResult += g20 + " G20 commands found." + "\n";
		parseResult += g21 + " G21 commands found." + "\n";
		parseResult += g90 + " G90 commands found." + "\n";
		parseResult += g91 + " G91 commands found." + "\n";
		parseResult += m2 + " M2 commands found." + "\n";
		parseResult += m3 + " M3 commands found." + "\n";
		parseResult += m5 + " M5 commands found." + "\n";
		parseResult += comment + " lines with comment found." + "\n";
		parseResult += blank + " blank lines found." + "\n";
		parseResult += unknonwn + " unknwon g commands found." + "\n";

		System.out.println(parseResult);

		Main.getInstance()._mainform.updateGCode();

		return ret;
	}

	public ArrayList<Commands> gCodeCommands = new ArrayList<Commands>();

	String parseResult = "";
	int g0 = 0;
	int g1 = 0;
	int g20 = 0;
	int g21 = 0;
	int g90 = 0;
	int g91 = 0;
	int m5 = 0;
	int m2 = 0;
	int m3 = 0;
	int comment = 0;
	int blank = 0;
	int unknonwn = 0;

	private void parseLine(String line) {
		Commands tmp;

		if (line.trim().toUpperCase().startsWith("G20")) {
			tmp = new G20Command();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				g20++;
			}
		} else if (line.trim().toUpperCase().startsWith("G21")) {
			tmp = new G21Command();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				g21++;
			}
		} else if (line.trim().toUpperCase().startsWith("G90")) {
			tmp = new G90Command();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				g90++;
			}
		} else if (line.trim().toUpperCase().startsWith("G91")) {
			tmp = new G91Command();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				g91++;
			}
		} else if (line.trim().toUpperCase().startsWith("G00")) {
			tmp = new G0Command();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				g0++;
			}
		} else if (line.trim().toUpperCase().startsWith("G01")) {
			tmp = new G1Command();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				g1++;
			}
		} else if (line.trim().toUpperCase().startsWith("M05")) {
			tmp = new M5Command();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				m5++;
			}
		} else if (line.trim().toUpperCase().startsWith("M02")) {
			tmp = new M2Command();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				m5++;
			}
		} else if (line.trim().toUpperCase().startsWith("M03")) {
			tmp = new M3Command();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				m3++;
			}
		} else if (line.trim().toUpperCase().startsWith("G0")) {
			tmp = new G0Command();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				g0++;
			}
		} else if (line.trim().toUpperCase().startsWith("G1")) {
			tmp = new G1Command();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				g1++;
			}
		} else if (line.trim().toUpperCase().startsWith("M5")) {
			tmp = new M5Command();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				m5++;
			}
		} else if (line.trim().toUpperCase().startsWith("M2")) {
			tmp = new M2Command();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				m2++;
			}
		} else if (line.trim().toUpperCase().startsWith("M3")) {
			tmp = new M3Command();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				m3++;
			}
		} else if (line.trim().toUpperCase().startsWith("(")
				&& line.trim().toUpperCase().endsWith(")")) {
			comment++;
		} else if (line.trim().equals("")) {
			blank++;
		} else {
			tmp = new unknownCommand();
			if (tmp.parse(line)) {
				gCodeCommands.add(tmp);
				unknonwn++;
			}
		}

	}
}
