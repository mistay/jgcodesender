package gui;

import gcode.Commands;
import gcode.GCodeFactory;
import helpers.Easyfile;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import main.Main;

public class Mainform extends JPanel {

	private static final long serialVersionUID = 1L;

	public static class PositionPoller implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			while (true) {

				if (Main.getInstance()._mainform.jc != null
						&& Main.getInstance()._mainform.jc.isSelected()) {
					System.out.println("isinit: " + Main.isInitialized());
					if (Main.isInitialized()) {
						Main.getInstance()._test.sendCommand(GCodeFactory
								.queryposition());

					}
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public Mainform() {
		createAndShowGUI();
	}

	public JTextPane txt2 = null;

	public JLabel machinex = null;
	public JLabel machiney = null;
	public JLabel machinez = null;

	public JLabel progress = null;

	JLabel machineunits = null;
	JCheckBox jc = null;

	public Millingtable _millingtable = null;

	public Millingtable getMillingtable() {
		return _millingtable;
	}

	Zheight zheight = null;
	JTextPane txt = null;

	private void createAndShowGUI() {
		JFrame frame = new JFrame("jgcodesender");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Thread t = (new Thread(new PositionPoller()));
		t.start();

		jc = new JCheckBox("continously query position");
		frame.getContentPane().add(jc);
		jc.setBounds(800, 50, 300, 30);

		JButton start = new JButton("start");
		frame.getContentPane().add(start);
		start.setBounds(1000, 300, 100, 30);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getInstance()._gcodesender.enabled = true;
				System.out.println("trying to notify()");

				Main.getInstance()._gcodesender.handleCommandResult();
				System.out.println("done with notify()");

			}
		});

		JButton stop = new JButton("stop");
		frame.getContentPane().add(stop);
		stop.setBounds(1000, 350, 100, 30);
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getInstance()._gcodesender.enabled = false;
			}
		});

		JButton reset = new JButton("reset");
		frame.getContentPane().add(reset);
		reset.setBounds(1000, 400, 100, 30);

		JButton emergency = new JButton("emergency");
		frame.getContentPane().add(emergency);
		emergency.setBounds(1000, 450, 100, 30);

		emergency.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean succ = Main.getInstance()._gcodereader
						.load("/Users/arminlanghofer/snullv2top.nc");
				System.out.println("read gcode: " + succ);

			}
		});

		JButton motoroff = new JButton("motoroff");
		frame.getContentPane().add(motoroff);
		motoroff.setBounds(1000, 500, 100, 30);
		motoroff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getInstance()._test.sendCommand(GCodeFactory.motorsoff());
			}
		});

		JLabel loadedGcode = new JLabel("Loaded G-Code");
		frame.getContentPane().add(loadedGcode);
		loadedGcode.setBounds(400, 170, 200, 30);

		txt = new JTextPane();
		JScrollPane jsp = new JScrollPane(txt);

		frame.getContentPane().add(jsp);
		jsp.setBounds(400, 200, 600, 600);

		JLabel toMachine = new JLabel("Machine Communication");
		frame.getContentPane().add(toMachine);
		toMachine.setBounds(1100, 170, 200, 30);

		JButton clear = new JButton("Clear");
		frame.getContentPane().add(clear);
		clear.setBounds(1500, 300, 100, 30);

		txt2 = new JTextPane();
		JScrollPane jsp2 = new JScrollPane(txt2);
		frame.getContentPane().add(jsp2);
		jsp2.setBounds(1100, 200, 400, 600);

		JMenuBar jmenubar = new JMenuBar();
		JMenu jmenu = new JMenu("jgcodesender");
		jmenubar.add(jmenu);
		JMenuItem loadgcode = new JMenuItem("load g-code");
		jmenu.add(loadgcode);

		loadgcode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("new permoance");
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				File f = fc.getSelectedFile();
				if (f.exists()) {
					txt.setText(Easyfile.getFileContents(fc.getSelectedFile()
							.toString()));
				}
				System.out.println("new donw");
			}
		});

		JMenuItem serialportsetup = new JMenuItem("serial port setup");
		jmenu.add(serialportsetup);
		serialportsetup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Settings();
			}
		});

		JMenuItem menuexit = new JMenuItem("exit");
		jmenu.add(menuexit);
		menuexit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		frame.setJMenuBar(jmenubar);

		JLabel jlabel = new JLabel("G-Code");
		frame.getContentPane().add(jlabel);
		jlabel.setBounds(0, 100, 100, 30);

		final JTextField jtf = new JTextField(10);
		frame.getContentPane().add(jtf);
		jtf.setBounds(100, 100, 100, 30);

		JButton jbutton1 = new JButton("send");
		frame.getContentPane().add(jbutton1);
		jbutton1.setBounds(200, 100, 100, 30);
		jbutton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Main.getInstance()._test.raw(jtf.getText());
			}
		});

		JLabel jlabel2 = new JLabel("X");
		frame.getContentPane().add(jlabel2);
		jlabel2.setBounds(0, 130, 100, 30);

		final JTextField jtf2 = new JTextField(10);
		frame.getContentPane().add(jtf2);
		jtf2.setBounds(100, 130, 100, 30);

		JLabel jlabel3 = new JLabel("Y");
		frame.getContentPane().add(jlabel3);
		jlabel3.setBounds(0, 160, 100, 30);

		final JTextField jtf3 = new JTextField(10);
		frame.getContentPane().add(jtf3);
		jtf3.setBounds(100, 160, 100, 30);

		JLabel jlabel4 = new JLabel("Z");
		frame.getContentPane().add(jlabel4);
		jlabel4.setBounds(0, 190, 100, 30);

		final JTextField jtf4 = new JTextField(10);
		frame.getContentPane().add(jtf4);
		jtf4.setBounds(100, 190, 100, 30);

		JButton jbutton2 = new JButton("g0");
		frame.getContentPane().add(jbutton2);
		jbutton2.setBounds(100, 220, 30, 30);
		jbutton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Float x = null;
				try {
					x = Float.parseFloat(jtf2.getText());
				} catch (Exception e1) {

				}
				Float y = null;
				try {
					y = Float.parseFloat(jtf3.getText());
				} catch (Exception ex) {

				}
				Float z = null;
				try {
					z = Float.parseFloat(jtf4.getText());
				} catch (Exception exc) {

				}
				if (x != null && y != null && z != null) {
					Main.getInstance()._test.sendCommand(GCodeFactory.moveto(x,
							y, z));
					return;
				}
				if (z != null) {
					Main.getInstance()._test.sendCommand(GCodeFactory
							.movetoZ(z));
					return;
				}

				if (x != null && y != null) {
					Main.getInstance()._test.sendCommand(GCodeFactory.movetoXY(
							x, y));
					return;
				}
				if (x != null) {
					Main.getInstance()._test.sendCommand(GCodeFactory
							.movetoX(x));
				}
				if (y != null) {
					Main.getInstance()._test.sendCommand(GCodeFactory
							.movetoY(y));
				}

			}
		});

		JButton jbutton3 = new JButton("g1");
		jbutton3.setBounds(150, 220, 30, 30);
		frame.getContentPane().add(jbutton3);

		JButton up = new JButton("Y+");
		frame.getContentPane().add(up);
		up.setBounds(300, 0, 30, 30);
		JButton down = new JButton("Y-");
		frame.getContentPane().add(down);
		down.setBounds(300, 60, 30, 30);
		JButton left = new JButton("X-");
		frame.getContentPane().add(left);
		left.setBounds(270, 30, 30, 30);
		JButton right = new JButton("X+");
		right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getInstance()._test.sendCommand(GCodeFactory
						.queryposition());

			}
		});
		frame.getContentPane().add(right);
		right.setBounds(330, 30, 30, 30);
		JLabel eins = new JLabel("1");
		frame.getContentPane().add(eins);
		eins.setBounds(300, 30, 30, 30);

		JButton up2 = new JButton("Y+");
		frame.getContentPane().add(up2);
		up2.setBounds(300 + 200, 0, 30, 30);
		JButton down2 = new JButton("Y-");
		frame.getContentPane().add(down2);
		down2.setBounds(300 + 200, 60, 30, 30);
		JButton left2 = new JButton("X-");
		frame.getContentPane().add(left2);
		left2.setBounds(270 + 200, 30, 30, 30);
		JButton right2 = new JButton("X+");
		frame.getContentPane().add(right2);
		right2.setBounds(330 + 200, 30, 30, 30);

		JLabel zehn = new JLabel("10");
		frame.getContentPane().add(zehn);
		zehn.setBounds(300 + 200, 30, 30, 30);

		JButton up3 = new JButton("Y+");
		frame.getContentPane().add(up3);
		up3.setBounds(300 + 400, 0, 30, 30);
		JButton down3 = new JButton("Y-");
		frame.getContentPane().add(down3);
		down3.setBounds(300 + 400, 60, 30, 30);
		JButton left3 = new JButton("X-");
		frame.getContentPane().add(left3);
		left3.setBounds(270 + 400, 30, 30, 30);
		JButton right3 = new JButton("X+");
		frame.getContentPane().add(right3);
		right3.setBounds(330 + 400, 30, 30, 30);

		JLabel hundert = new JLabel("100");
		frame.getContentPane().add(hundert);
		hundert.setBounds(300 + 400, 30, 30, 30);

		JButton homex = new JButton("home x");
		frame.getContentPane().add(homex);
		homex.setBounds(790, 30, 100, 30);
		homex.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Main.getInstance()._test.raw("G28 X");

			}
		});

		JButton homey = new JButton("home y");
		frame.getContentPane().add(homey);
		homey.setBounds(920, 30, 100, 30);
		homey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Main.getInstance()._test.raw("G28 Y");

			}
		});

		JButton homez = new JButton("home z");
		frame.getContentPane().add(homez);
		homez.setBounds(1050, 30, 100, 30);
		homez.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Main.getInstance()._test.raw("G28 Z");

			}
		});

		JButton homeall = new JButton("homeall");
		frame.getContentPane().add(homeall);
		homeall.setBounds(1150, 0, 100, 30);
		homeall.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Main.getInstance()._test.raw("G28");

			}
		});

		final JLabel mousex = new JLabel();
		mousex.setBounds(0, 520, 50, 30);
		frame.getContentPane().add(mousex);
		mousex.setText("X: 0");

		final JLabel mousey = new JLabel();
		mousey.setBounds(50, 520, 50, 30);
		frame.getContentPane().add(mousey);
		mousey.setText("Y: 0");

		final JLabel mousez = new JLabel();
		mousez.setBounds(210, 520, 50, 30);
		frame.getContentPane().add(mousez);
		mousez.setText("Z: 0");

		machinex = new JLabel();
		machinex.setBounds(800, 0, 100, 30);
		frame.getContentPane().add(machinex);
		machinex.setText("Machine X: 0");
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		machinex.setBorder(border);
		machiney = new JLabel();
		machiney.setBounds(920, 0, 100, 30);
		frame.getContentPane().add(machiney);
		machiney.setText("Machine Y: 0");
		Border border2 = BorderFactory.createLineBorder(Color.BLACK, 2);
		machiney.setBorder(border2);

		machinez = new JLabel();
		machinez.setBounds(1040, 0, 100, 30);
		frame.getContentPane().add(machinez);
		machinez.setText("Machine Z: 0");
		Border border3 = BorderFactory.createLineBorder(Color.BLACK, 2);
		machinez.setBorder(border3);

		progress = new JLabel();
		progress.setBounds(1040, 100, 100, 30);
		frame.getContentPane().add(progress);
		progress.setText("Progess: 0%");
		Border border4 = BorderFactory.createLineBorder(Color.BLACK, 2);
		progress.setBorder(border4);

		machineunits = new JLabel();
		machineunits.setBounds(1300, 0, 200, 30);
		frame.getContentPane().add(machineunits);
		machineunits.setText("Machine Units:");
		Border border5 = BorderFactory.createLineBorder(Color.BLACK, 2);
		machineunits.setBorder(border5);

		zheight = new Zheight();
		frame.getContentPane().add(zheight);
		zheight.setBounds(210, 300, 10, 200);
		zheight.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseMoved(java.awt.event.MouseEvent evt) {
				mousez.setText("Z: " + (zheight.getHeight() / 2 - evt.getY()));
			}
		});
		zheight.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				mousez.setText("mouse clicked at Z: "
						+ (zheight.getHeight() - e.getY()));

				zheight.toolz_should = new Float(zheight.getHeight() / 2
						- e.getY());
				zheight.updateUI();

				// todo: pixels in echte maschinenkoordinaten umrechnen

				Main.getInstance()._test.sendCommand(GCodeFactory
						.movetoZ(new Float(zheight.getHeight() / 2 - e.getY())));

			}
		});

		_millingtable = new Millingtable();
		frame.getContentPane().add(_millingtable);
		_millingtable.setBounds(0, 300, 200, 200);
		_millingtable
				.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
					public void mouseMoved(java.awt.event.MouseEvent evt) {
						mousex.setText("X: " + evt.getX());
						mousey.setText("Y: " + evt.getY());
					}
				});
		_millingtable.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("mouse clicked at " + e.getX() + " "
						+ e.getY());

				// todo: pixels in echte maschinenkoordinaten umrechnen
				Float tmpx = new Float(e.getX());
				Float tmpy = new Float(e.getY());

				_millingtable.toolx_should = (tmpx);
				_millingtable.tooly_should = (tmpy);
				_millingtable.updateUI();

				Main.getInstance()._test.sendCommand(GCodeFactory.movetoXY(
						tmpx, tmpy));

			}
		});

		// txt.setText(Easyfile
		// .getFileContents("/Users/arminlanghofer/snullv2top.nc"));

		frame.setSize(1600, 900);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	public void setMachineX(Float x) {

		machinex.setText("X: " + x);
		if (_millingtable != null) {
			_millingtable.toolx = x;
			_millingtable.updateUI();
		}
	}

	public void setMachineY(Float y) {

		machiney.setText("Y: " + y);
		if (_millingtable != null) {
			_millingtable.tooly = y;
			_millingtable.updateUI();
		}
	}

	public void setMachineZ(Float z) {
		// System.out.println("setMachineZ()");

		machinez.setText("Z: " + z);

		if (zheight != null) {
			// System.out.println("setMachineZ not null");
			zheight.toolz = z;
			zheight.updateUI();
			// System.out.println("updated ui");
		}
	}

	public void setProgress(int current, int max) {
		System.out.println("progress: " + current);
		System.out.println("max: " + max);
		Float percent = new Float(current) / new Float(max);
		System.out.println("percent: " + percent);

		progress.setText("Progress: " + (((int) (percent * 10000)) / 100 + "%"));
	}

	public void updateGCode() {

		StyledDocument doc = txt.getStyledDocument();

		Style style = txt.addStyle("I'm a Style", null);
		StyleConstants.setForeground(style, Color.red);
		for (Commands command : Main.getInstance()._gcodereader.gCodeCommands) {
			insertLine(txt, command);
		}

	}

	public void setMachineUnits(boolean milimeters) {
		String text = milimeters ? "millimeters" : "inch";
		machineunits.setText("Machine Units: " + text);
	}

	public void machineCommand(Commands command) {
		insertLine(txt2, command);
		command.updateUI();
	}

	private void insertLine(JTextPane txt, Commands command) {
		StyledDocument doc = txt.getStyledDocument();

		Style style = txt.addStyle("I'm a Style", null);
		StyleConstants.setForeground(style, Color.red);
		try {
			if (command.getCommand() == "") {
				StyleConstants.setForeground(style, Color.red);
				doc.insertString(doc.getLength(), command.toString(), style);
			}

			if (command.linenumber > -1) {
				doc.insertString(doc.getLength(), "" + command.linenumber
						+ ": ", style);
			}

			StyleConstants.setForeground(style, Color.green);
			doc.insertString(doc.getLength(), command.getCommand() + " ", style);
			StyleConstants.setForeground(style, Color.blue);
			doc.insertString(doc.getLength(), command.getArgs(), style);
			StyleConstants.setForeground(style, Color.black);
			doc.insertString(doc.getLength(), " ;" + command.getDescription()
					+ "\n", style);

		} catch (Exception e) {
			System.out.println("error inserting command: " + e.getMessage());
			e.printStackTrace();
		}

	}

	public void setGcodetarget(Float x, Float y, Float z) {
		_millingtable.setGcodetarget(x, y);

		// todo: z pos visualisieren

	}
}
