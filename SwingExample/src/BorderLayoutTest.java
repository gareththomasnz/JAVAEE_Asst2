
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;


public class BorderLayoutTest extends JFrame {

	public BorderLayoutTest()
	{
		getContentPane().setLayout(new BorderLayout());
		
		JLabel lblNorth = new JLabel("North");
		JLabel lblSouth = new JLabel("South");
		JLabel lblEast = new JLabel("East");
		JLabel lblWest = new JLabel("West");
		JLabel lblCenter = new JLabel("Center");

		final Border lineBorder = BorderFactory.createLineBorder(Color.RED);
		
		lblCenter.setBorder(lineBorder);
		lblEast.setBorder(lineBorder);
		lblWest.setBorder(lineBorder);
		lblNorth.setBorder(lineBorder);
		lblSouth.setBorder(lineBorder);
		
		getContentPane().add(lblCenter,BorderLayout.CENTER);
		getContentPane().add(lblEast,BorderLayout.EAST);
		getContentPane().add(lblWest,BorderLayout.WEST);
		getContentPane().add(lblNorth,BorderLayout.NORTH);
		getContentPane().add(lblSouth,BorderLayout.SOUTH);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		setSize(300,400);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BorderLayoutTest test = new BorderLayoutTest();
	}
}
