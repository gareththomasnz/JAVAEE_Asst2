import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

class BallBounceDoubleBuffered extends JFrame implements Runnable {
	int x=300,y=0;
	int deltax=1, deltay=1;
	
	
	BufferedImage buffer;
	Graphics offScreen;
	
	public BallBounceDoubleBuffered() {
		super ("Bouncing Balls");
		setSize (600, 600);
		setVisible(true);
		
		setIgnoreRepaint(true);
		
		buffer = new BufferedImage(600,600, BufferedImage.TYPE_INT_ARGB);
		offScreen = buffer.getGraphics();
		
		Thread animationThread = new Thread(this);
		animationThread.start();
	} 
	
	@Override
	public void run() {
		while(true)
		{
			Graphics g = offScreen;
			
			g.setColor(Color.white);
			g.fillRect(0, 0, 600, 600);
	
			g.setColor(Color.red);
			g.fillArc(x, y, 20, 20, 0, 360);
			
			x=x+deltax;
			y=y+deltay;
			
			if(x > getWidth())
			{
				deltax = -1;
			}
	
			if(x < 0)
			{
				deltax = 1;
			}
			
			if(y > getHeight())
			{
				deltay = -1;
			}
			
			if(y < 0)
			{
				deltay = 1;
			}
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			repaint(); // cause a screen refresh
		}
	}
	

	public void paint (Graphics g) {
		g.drawImage(buffer, 0, 0, this);
	}

	public static void main (String[] args) {
		BallBounceDoubleBuffered s = new BallBounceDoubleBuffered();
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


}
