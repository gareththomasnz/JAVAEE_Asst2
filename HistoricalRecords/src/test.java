import java.util.List;

import model.Book;
import model.Genre;

import org.hibernate.classic.Session;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;

import util.HibernateUtil;
import controller.BookManager;


public class test {
	public static void main(String[] args)
	{
		BookManager linkController = new BookManager();
		
		// setup new book with some simple sample data and add to the database
		Book book = new Book();
		book.setAuthor("Margaret Mitchell");
		book.setTitle("Gone wyth the wind");

		// link the book with a new genre record
		Genre genre = new Genre();
		genre.setGenre("Clssic");
		genre.setBook(book); // create book genre link
		
		// add book and genre to the database
		book = linkController.add(book);
		genre = linkController.add(genre);
		
		// update the title of the book to correct spelling error
		book.setTitle("Gone with the wind");		
		book = linkController.update(book);

		genre.setGenre("Classic");
		genre = linkController.update(genre);
		
		
		linkController.delete(genre); // delete dependent child genre data first
		linkController.delete(book); // delete parent book record
		
		List <Object[]> bookRevisions = linkController.getHistoricalBook(book.getBookId());

		for(Object[] revisionData: bookRevisions)
		{		
			Book temp = (Book) revisionData[0]; // note use of array notation to retrieve historical records
			DefaultRevisionEntity rev = (DefaultRevisionEntity) revisionData[1];
	
			System.out.print(temp.getTitle()+" ");
			System.out.print(temp.getAuthor()+" ");
			
			System.out.print(rev.getId()+" ");
			System.out.print(rev.getRevisionDate()+" ");
			
			RevisionType revType = (RevisionType) revisionData[2];
			System.out.println(revType);
		}
	}
	
}
