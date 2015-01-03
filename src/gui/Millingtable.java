package gui;

import java.awt.Color;
import java.awt.Graphics;

public class Millingtable extends javax.swing.JPanel {

	Float toolx = 0.0F;
	Float tooly = 0.0F;

	Float toolx_should = 0.0F;
	Float tooly_should = 0.0F;

	private static final long serialVersionUID = 1L;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		g.fillRect(0, 0, 2000, 2000);
		g.setColor(Color.black);

		g.setColor(Color.red);
		g.fillOval(toolx_should.intValue(), tooly_should.intValue(), 5, 5);

		g.setColor(Color.black);
		g.fillOval(toolx.intValue(), tooly.intValue(), 5, 5);

	}
}
