import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class BallBouncePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	int x=300,y=0;
	int deltax=1, deltay=1;
	
	public BallBouncePanel()
	{	

	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());

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
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		repaint(); // cause a screen refresh
	}
	
}
