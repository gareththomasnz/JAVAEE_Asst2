
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BookServerInterface extends Remote {
	
	// parameters need to be Serializable
	List <BookDTO> listBooks(String title, String author, String genre) throws RemoteException;
	
}
