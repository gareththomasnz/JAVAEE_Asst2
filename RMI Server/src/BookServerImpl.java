import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import com.jcasey.controller.BookManager;
import com.jcasey.model.Book;


public class BookServerImpl implements BookServerInterface {

	private BookManager linkController;
	
	public BookServerImpl()
	{
		super();
		
		linkController = new BookManager();
	}
	
	@Override
	public List<BookDTO> listBooks(String title, String author, String genre)
			throws RemoteException {
		
		List<Book> books = linkController.list(genre, title, author);
		
		ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
		
		// translate Hibernate POJO to Book DTO
		for(Book book: books)
		{
			BookDTO bookDTO = new BookDTO();
			
			bookDTO.setBookId(book.getBookId());
			bookDTO.setTitle(book.getTitle());
			bookDTO.setAuthor(book.getAuthor());
			bookDTO.setBlurb(book.getBlurb());
			bookDTO.setGenre(book.getGenre());
			bookDTO.setIsbn(book.getIsbn());
			
			bookList.add(bookDTO);
		}
		
		return bookList;
	}
	
	public static void main(String[] args)
	{
		if (System.getSecurityManager() == null) {
            SecurityManager securityManager = new SecurityManager();
                        
            System.setProperty("java.security.policy", "wideopen.policy");

            //TODO update this URL to suit your own file system
            // Note the /bin/ at the end of the file URL - the RMI registry is only interested in .class not .java files
			// System.setProperty("java.rmi.server.codebase","file:///C:/Documents%20and%20Settings/Administrator/workspace/RMI%20Server/bin/");
			
            System.setProperty("java.rmi.server.codebase","file:///C:/Users/JCASEY/7426/RMI%20Server/bin/");
//            System.setProperty("java.rmi.server.useCodebaseOnly","true");
			
			System.setSecurityManager(securityManager);
        }
        try {
            BookServerInterface proxy = new BookServerImpl();
            BookServerInterface stub = (BookServerInterface) UnicastRemoteObject.exportObject(proxy, 0);
            
            // create a new RMI Registry and bind to user port 50000
            LocateRegistry.createRegistry(50000);
            
            // get a reference to the registry
            Registry registry = LocateRegistry.getRegistry(50000);           
            
            registry.rebind("BookServer", stub);
            System.out.println("BookServer bound");
        } catch (Exception e) {
            System.err.println("BookServer exception:");
            e.printStackTrace();
        }
	}

}
