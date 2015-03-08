import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

public class CustomPanel extends Panel
{
	public CustomPanel()
	{

	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		
		
		g.drawRect(50, 50, 100, 100);
		
		g.fillRect(150, 50, 100, 100);
		
		g.drawLine(20, 20, 800, 600);
	}
	
	
}