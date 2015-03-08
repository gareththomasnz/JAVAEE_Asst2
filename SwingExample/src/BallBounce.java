import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

class BallBounce extends JFrame {

	public BallBounce() {
		super ("Bouncing Balls");
		setSize (600, 600);
		setVisible(true);
	} 
	
	int x=300,y=0;
	int deltax=1, deltay=1;
	
	public void paint (Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.red);
		g.fillArc(x, y, 15, 15, 0, 360);
		
		x=x+deltax;
		y=y+deltay;
		
		if(x > getWidth())
		{
			deltax = -1;
		}

		if(x < 0)
		{
			deltax = 0;
		}
		
		// extra guard conditions needed for y variable
		
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		repaint(); // cause a screen refresh
	}

	public static void main (String[] args) {
		BallBounce s = new BallBounce();
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
