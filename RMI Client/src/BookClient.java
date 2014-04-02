

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookClient {
    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
        	
        	System.setProperty("java.security.policy", "wideopen.policy");
            System.setSecurityManager(new SecurityManager());
        }
        try
        {
            String name = "BookServer";
            Registry registry = LocateRegistry.getRegistry("localhost",50000);
            final BookServerInterface comp = (BookServerInterface) registry.lookup(name);

            class Parent extends JFrame implements ActionListener
            {
            	JTextField titleInput = new JTextField(30);
            	JButton btnSearch = new JButton("Search");
            	
            	public Parent()
            	{
            		Container contentPane = getContentPane();
            		
            		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
            		contentPane.setSize(300, 200);
            		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            		
            		JPanel titleCriteria = new JPanel();
            		
            		titleCriteria.add(new JLabel("Title"));
            		titleCriteria.add(titleInput);
					
            		contentPane.add(titleCriteria);
            		
            		contentPane.add(btnSearch);
					
					btnSearch.addActionListener(this);
					
					pack();
					setVisible(true);
            	}

				@Override
				public void actionPerformed(ActionEvent event)
				{
					try {
						List<BookDTO> books = comp.listBooks(titleInput.getText(),null, null);
						
			            for(BookDTO book: books)
			            {
			            	System.err.println(book.getTitle());
			            }
					}
					catch (RemoteException e) {
						e.printStackTrace();
					}
				}
            }
            Parent parent = new Parent();
            parent.setVisible(true);
        }
        catch (RemoteException e) {
            System.err.println("BookServer exception:");
            e.printStackTrace();
        } catch (NotBoundException e) {
			e.printStackTrace();
		}
    }    
}