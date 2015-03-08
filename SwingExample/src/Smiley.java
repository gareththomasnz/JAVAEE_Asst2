import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

class Smiley extends JFrame {

	public Smiley() {
		super ("Smiley");
		setSize (600, 600);
		setVisible(true);
	} 
	public void paint (Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.yellow);
		g.fillOval(100,100,400,400);
		g.setColor(Color.black);
		g.fillArc(233, 350, 132, 50, 0, -180);
		g.fillOval(200, 233, 40, 40);
		g.fillOval(360, 233, 40, 40);
		g.setColor(Color.yellow);
		g.fillArc(233, 340, 132, 50, 0, -180);
	}

	public static void main (String[] args) {
		Smiley s = new Smiley();
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
