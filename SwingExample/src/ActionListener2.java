import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class ActionListener2 extends JFrame {

	public ActionListener2()
	{		
		final Point point = new Point();
		
		// define parent container for components
		final JPanel formPaint = new JPanel()
		{
			@Override
			public void paint(Graphics g) {				
				g.setColor(Color.RED);
				g.fillArc(point.x, point.y, 15 /* width */, 15 /* height */ , 0, 360);
			}
		};
		
		formPaint.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {

			}
			
			@Override
			public void mouseDragged(MouseEvent event) {
				point.x = event.getX();
				point.y = event.getY();
				
				formPaint.repaint(); // force the paint panel to be redrawn 
				
			}
		});
		
		// add parent container to our JFrame
		getContentPane().add(formPaint);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		setSize(300,400);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActionListener2 test = new ActionListener2();
	}

}
