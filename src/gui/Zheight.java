package gui;

import java.awt.Color;
import java.awt.Graphics;

public class Zheight extends javax.swing.JPanel {

	Float toolz = 0.0F;

	Float toolz_should = 0.0F;

	private static final long serialVersionUID = 1L;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight() - toolz.intValue());

		g.setColor(Color.red);
		g.fillRect(0, this.getHeight() - toolz_should.intValue(),
				this.getWidth(), 2);
		//System.out.println("filled hblack");
	}
}
