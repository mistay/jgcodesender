package communication;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gui.Mainform;
import gui.Settings;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jgcodesender.Main;

public class Test {

	public SerialReader sr = null;
	public SerialWriter sw = null;

	private void cleanupVar() {

		File dir = new File("/var/lock/");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				if (child.toString().startsWith("/var/lock/LK")) {
					System.out
							.println("clean up lockfile: " + child.toString());
					child.delete();
				}
			}
		}

	}

	public void tryToConnect() {

		cleanupVar();

		Preferences prefs = Preferences
				.userNodeForPackage(jgcodesender.Main.class);

		String port = prefs.get(Settings.SETTING_PORT, "");
		Integer baudrate = null;
		try {
			baudrate = Integer.parseInt(prefs.get(Settings.SETTING_BAUDRATE,
					"9600"));
		} catch (Exception ex) {

		}
		if (port != "" && baudrate != null) {
			try {
				connect(port, baudrate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void connect(String portName, int baudrate) throws Exception {
		System.out
				.println("install http://blog.iharder.net/2009/08/18/rxtx-java-6-and-librxtxserial-jnilib-on-intel-mac-os-x/ ");
		System.out
				.println("for macos: http://blog.iharder.net/wp-content/uploads/2009/08/librxtxSerial.jnilib");

		System.out.println("trying to connect to " + portName + " with "
				+ baudrate + "bits/sec");
		CommPortIdentifier portIdentifier = CommPortIdentifier
				.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port is currently in use");
		} else {
			System.out
					.println("trying to open port. make sure exits /var/lock (mkdir /var/lock) and chmod go+rwx /var/lock");
			CommPort commPort = portIdentifier.open(this.getClass().getName(),
					2000);

			System.out.println("opened");
			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8,
						SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

				InputStream in = serialPort.getInputStream();
				OutputStream out = serialPort.getOutputStream();

				sr = new SerialReader(in);
				sw = new SerialWriter(out);

				(new Thread(sr)).start();
				(new Thread(sw)).start();

			} else {
				System.out
						.println("Error: Only serial ports are handled by this example.");
			}
		}
	}

	/** */
	public static class SerialReader implements Runnable {
		private InputStream in;

		StringBuffer _sb = new StringBuffer();

		public SerialReader(InputStream in) {
			this.in = in;
		}

		public void run() {
			byte[] buffer = new byte[1024];
			int len = -1;
			try {
				while ((len = this.in.read(buffer)) > -1) {
					String s = new String(buffer, 0, len);
					System.out.print("reci:" + s.replace("\n", "[\\n]") + "\n");
					_sb.append(s);
					try {
						parse();
					} catch (Exception e) {
						System.out.println("could not parse(): "
								+ e.getMessage());
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void cutoff(int end) {
			_sb.delete(0, end);
		}

		private void parse() {
			Pattern p = Pattern
					.compile("Count X: *([0-9.]*) Y:([0-9.]*) Z:([0-9.]*)\n");
			Matcher m = p.matcher(_sb);
			if (m.find()) {
				// System.out.println("match: " + m.group(1));
				// System.out.println("match: " + m.group(2));
				// System.out.println("match: " + m.group(3));

				// System.out.println("m.end(): " + m.end());
				// System.out.println("now in buffer: " + _sb.length());

				// System.out.println("start parsing");
				Float x = Float.parseFloat(m.group(1));
				Float y = Float.parseFloat(m.group(2));
				Float z = Float.parseFloat(m.group(3));
				// System.out.println("done parsing");

				cutoff(m.end());

				Main.getInstance()._mainform.setMachineX(x);
				Main.getInstance()._mainform.setMachineY(y);
				// System.out.println("set y");

				Main.getInstance()._mainform.setMachineZ(z);
				// System.out.println("set z");

			}
		}
	}

	/** */
	public static class SerialWriter implements Runnable {
		private OutputStream out;

		public SerialWriter(OutputStream out) {
			this.out = out;
		}

		public void run() {
		}

		public void write(String s) {
			byte[] b = s.getBytes();
			System.out.println("wrote: " + s.replace("\n", "[\\n]"));
			try {
				out.write(b);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	int x = 10;

	public void foo() {

	}

	public void motorsoff() {

		x += 5;
		String s = "M18\n";

		sw.write(s);
	}

	public void raw(String string) {
		String s = string + "\n";

		sw.write(s);
	}

	public void queryposition() {
		String s = "M114\n";
		sw.write(s);

	}

	public void movetoX(Float x) {
		String s = "G0 X" + x + "\n";
		sw.write(s);
	}

	public void movetoY(Float y) {
		String s = "G0 Y" + y + "\n";
		sw.write(s);
	}

	public void movetoZ(Float z) {
		String s = "G0 Z" + z + "\n";
		sw.write(s);
	}

	public void movetoXY(Float x, Float y) {
		String s = "G0 X" + x + " Y" + y + "\n";
		sw.write(s);
	}

	public void moveto(Float x2, Float y, Float z) {

		String s = "G0 X" + x2 + " Y" + y + " Z" + z + "\n";
		sw.write(s);
	}

}