package com.jcasey.test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.jcasey.jaxb.Books;
import com.jcasey.jaxb.Books.Book;
import com.jcasey.jaxb.Books.Book.Genres;
import com.jcasey.jaxb.Books.Book.Genres.Genre;

public class BookTest {

  private static final String BOOKS_XML = "./books.xml";

  public static void main(String[] args) throws JAXBException, IOException {

    Books books = new Books();
    
    // create books
    Book book1 = new Book();
    book1.setIsbn("978-1617290060");
    book1.setTitle("The Well-Grounded Java Developer: Vital techniques of Java 7 and polyglot programming");
    book1.setAuthor("Benjamin J Evans");
    
    // create genre objects
    Genre java = new Genre();
    java.setType("Java");
    
    Genre scala = new Genre();
    scala.setType("Scala");
    
    book1.setGenres(new Genres()); //Books do not need to *have* a list of Genres so create a list here
    
    // link genres with genre list    
	book1.getGenres().getGenre().add(java);
	book1.getGenres().getGenre().add(scala);
	
	// add book to list of books
    books.getBook().add(book1);
    
    
    // create book2 note there are no genres in this book
    Book book2 = new Book();
    book2.setIsbn("978-3832180577");
    book2.setTitle("Java Concurrency in Practice");
    book2.setAuthor("Brian Goetz");

    // add book to list of books
    books.getBook().add(book2);
    
    // create JAXB context and instantiate marshaller
    JAXBContext context = JAXBContext.newInstance(Books.class);
    Marshaller m = context.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

    // write to System.out
    m.marshal(books, System.out);

    // write to File
    m.marshal(books, new File(BOOKS_XML));

    // get variables from our xml file, created before
    System.out.println();
    System.out.println("Output from our XML File: ");
    Unmarshaller um = context.createUnmarshaller();
    Books books2 = (Books) um.unmarshal(new FileReader(BOOKS_XML));

    for (Book book : books2.getBook()) {
      System.out.println("Book: " + book.getTitle() + " from " + book.getAuthor());
    }
  }
} 