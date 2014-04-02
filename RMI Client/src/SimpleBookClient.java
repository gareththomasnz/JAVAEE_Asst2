

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class SimpleBookClient {
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

			List<BookDTO> books = comp.listBooks(null,null, null);
			
            for(BookDTO book: books)
            {
            	System.err.println(book.getTitle());
            }

        }
        catch (RemoteException e) {
            System.err.println("BookServer exception:");
            e.printStackTrace();
        } catch (NotBoundException e) {
			e.printStackTrace();
		}
    }    
}