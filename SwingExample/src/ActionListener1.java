import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ActionListener1 extends JFrame {

	public ActionListener1()
	{		
		// define parent container for components
		JPanel formField = new JPanel();
		
		// define on screen components
		JButton btnHello = new JButton("Hello World");
		final JLabel lblHello = new JLabel("Hello World");
		
		// setup the layout
		formField.setLayout(new FlowLayout());
		
		// add components to the screen
		formField.add(lblHello);
		formField.add(btnHello);
		
		// setup an Action Listener
		btnHello.addActionListener(new ActionListener()
		{
			int counter = 0;
			
			@Override
			public void actionPerformed(ActionEvent event) {
				lblHello.setText(""+counter);
				
				counter++;
			}
			
		});
		// set the layout of the parent JFrame
		getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

		// add parent container to our JFrame
		getContentPane().add(formField);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		setSize(300,400);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActionListener1 test = new ActionListener1();
	}

}
