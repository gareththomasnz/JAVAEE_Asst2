import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;


public class SwingTest3 extends JFrame {

	public SwingTest3()
	{
		JPanel formField = new JPanel();
		formField.setLayout(new FlowLayout());
		formField.add(new JLabel("Hello World"));
		JButton myButton = new JButton("Hello World");
		
		myButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				System.err.println("Hello World");
				
			}
		});
		
		formField.add(myButton);
		
		JPanel formField2 = new JPanel();
		formField2.setLayout(new FlowLayout());
		formField2.add(new JLabel("Hello World2"));
		formField2.add(new JButton("Hello World2"));
		
		getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		getContentPane().add(formField);
		getContentPane().add(formField2);
		
		
		JPanel parent = new JPanel();
		parent.setLayout(new FlowLayout());
		
		JComboBox<String> items = new JComboBox<String> ();
		
		items.addItem("acorn");
		items.addItem("apple");
		items.addItem("apricot");
		
		parent.add(items);
		
		getContentPane().add(parent);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		setSize(300,400);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingTest3 test = new SwingTest3();
	}

}
