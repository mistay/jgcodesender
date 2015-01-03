package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import jgcodesender.Main;

public class Settings extends javax.swing.JPanel {

	public static String SETTING_BAUDRATE = "baudrate";
	public static String SETTING_PORT = "port";

	private static final long serialVersionUID = 1L;

	public Settings() {
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		JFrame frame = new JFrame("settings");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel jlabel = new JLabel("Serial Port");
		frame.getContentPane().add(jlabel);
		jlabel.setBounds(0, 100, 100, 30);

		final JComboBox serialports = new JComboBox();

		frame.getContentPane().add(serialports);
		File dir = new File("/dev/");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				if (child.toString().startsWith("/dev/tty.")) {
					System.out.println("child: " + child.toString());
					serialports.addItem(child.toString());
				}
			}
		}

		serialports.setBounds(100, 100, 300, 30);

		final JTextField jtf2 = new JTextField();
		frame.getContentPane().add(jtf2);
		Preferences prefs = Preferences
				.userNodeForPackage(jgcodesender.Main.class);
		System.out.println("loaded setting baudrate: "
				+ prefs.get(SETTING_BAUDRATE, "9600"));
		System.out.println("loaded setting port: "
				+ prefs.get(SETTING_PORT, "9600"));
		jtf2.setText(prefs.get("baudrate", "9600"));
		jtf2.setBounds(450, 100, 100, 30);

		JButton jbutton1 = new JButton("save");
		frame.getContentPane().add(jbutton1);
		jbutton1.setBounds(600, 100, 100, 30);
		jbutton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Preferences prefs = Preferences
						.userNodeForPackage(jgcodesender.Main.class);
				prefs.put(SETTING_BAUDRATE, jtf2.getText());
				prefs.put(SETTING_PORT, serialports.getSelectedItem()
						.toString());
				try {
					prefs.flush();
				} catch (BackingStoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				Main.getInstance()._test.tryToConnect();

			}
		});

		frame.setSize(800, 400);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}
