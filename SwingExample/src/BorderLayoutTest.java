
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class BorderLayoutTest extends JFrame {

	public BorderLayoutTest()
	{

		getContentPane().setLayout(new BorderLayout());
		
		JLabel grrr = new JLabel("Center");
		grrr.setBorder(BorderFactory.createLineBorder(Color.red));
		
		getContentPane().add(grrr,BorderLayout.CENTER);
		JLabel center = new JLabel("East");
		center.setBorder(BorderFactory.createLineBorder(Color.RED));
		getContentPane().add(center,BorderLayout.EAST);
		JLabel jLabel = new JLabel("West");
		jLabel.setBorder(BorderFactory.createLineBorder(Color.red));
		getContentPane().add(jLabel,BorderLayout.WEST);
		getContentPane().add(new JLabel("North"),BorderLayout.NORTH);
		
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
