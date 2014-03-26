import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SwingTest2 extends JFrame {

	public SwingTest2()
	{
		JPanel formField = new JPanel();
		formField.setLayout(new FlowLayout());
		formField.add(new JLabel("Hello World"));
		JButton btnHello = new JButton("Hello World");
		formField.add(btnHello);
		
		JPanel formField2 = new JPanel();
		formField2.setLayout(new FlowLayout());
		formField2.add(new JLabel("Hello World2"));
		formField2.add(new JButton("Hello World2"));
		
		getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		getContentPane().add(formField);
		getContentPane().add(formField2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		setSize(300,400);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingTest2 test = new SwingTest2();
	}

}
