import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class HelloWorldSwing extends JFrame {

	public HelloWorldSwing()
	{
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(new JLabel("Hello World"));
		getContentPane().add(new JButton("Hello World"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		setSize(300,400);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloWorldSwing test = new HelloWorldSwing();
	}

}
