package communication;

import gui.Mainform;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jgcodesender.Main;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Serial {

	private StringBuffer out = new StringBuffer();

	public void enqueue(String gcode) {
		out.append(gcode);

		Main.getInstance()._mainform.txt2
				.setText(Main.getInstance()._mainform.txt2.getText() + "\n"
						+ gcode);

	}
}
