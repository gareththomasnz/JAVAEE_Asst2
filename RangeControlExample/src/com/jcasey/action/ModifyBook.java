package com.jcasey.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.jcasey.controller.Manager;
import com.jcasey.model.Book;
import com.jcasey.model.BookGenre;
import com.jcasey.model.Genre;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class ModifyBook implements SessionAware, RequestAware
{	
	private Manager controller;
	
	private Long bookId;
	private String title;
	private String author;
	private String genre="";
	private String isbn;
	private String blurb;
	
	private Long price;
	
	private List<BookGenre> bookGenre;
	
	public ModifyBook()
	{
		controller = new Manager();
	}
	
	public String add()
	{
		Book book = new Book();
		
		book.setAuthor(author);
		book.setTitle(title);
		book.setBlurb(blurb);
		
		// split incoming data up based on commas
		String genreData[] = StringUtils.split(genre, ",");
		
		List <BookGenre> mapping = new LinkedList <BookGenre>();
		final LinkedList <Genre> genres = controller.listGenres(); // get a list of genres already in the database
		
		for(String data: genreData)
		{
			if(StringUtils.isNotBlank(StringUtils.trimToEmpty(data)))
			{
				Genre newGenre = new Genre();
				newGenre.setGenre(data);
				
				if (!genres.contains(newGenre)) // is this a new genre?
				{
					controller.addGenre(newGenre);	
				}
				else
				{
					int idx = genres.indexOf(newGenre);
					newGenre = genres.get(idx);
				}
				
				BookGenre bookGenre = new BookGenre(); // setup the associative entity
				
				bookGenre.setGenre(newGenre);
				bookGenre.setBook(book);
				
				mapping.add(bookGenre);
			}
		}
		
		book.setBookGenre(mapping);
		book.setIsbn(isbn);
		
		controller.addBook(book); // save the book record
		
		// save mapping between book and genre
		for(BookGenre bookGenre: book.getBookGenre())
		{
			controller.addBookGenre(bookGenre);
		}
		
		return Action.SUCCESS;
	}
	
	public String delete()
	{
		Book book = controller.getBook(getBookId());
		
		// delete the dependent objects first
		
		for(BookGenre bookGenre: book.getBookGenre())
		{
			controller.deleteBookGenre(bookGenre.getId());
		}
		
		// delete the primary book record
		controller.deleteBook(book.getBookId());
		return Action.SUCCESS;
	}
	public String updatePrice()
	{
		Book book = controller.getBook(getBookId());
		
		System.err.println(getPrice());
		
		book.setPrice(getPrice());
		
		controller.update(book);
		
		return Action.SUCCESS;
	}
	
	public String update()
	{
		// get the current book
		Book book = controller.getBook(getBookId());
		
		// update the book setters based on new form data
		book.setAuthor(author);
		
		// split incoming data up based on commas
		String genreData[] = StringUtils.split(genre, ",");
		
		if(genreData.length >0)
		{
			// delete associations - a bit of a nasty way to do things...
			for(BookGenre bookGenre: book.getBookGenre())
			{
				controller.deleteBookGenre(bookGenre.getId());
			}
		}
		List <BookGenre> mapping = new LinkedList <BookGenre>();
		final LinkedList <Genre> genres = controller.listGenres(); // get a list of genres already in the database
		
		
		for(String data: genreData)
		{
			if(StringUtils.isNotBlank(StringUtils.trimToEmpty(data)))
			{
				Genre newGenre = new Genre();
				newGenre.setGenre(data);
				
				if (!genres.contains(newGenre)) // is this a new genre?
				{
					controller.addGenre(newGenre);	
				}
				else
				{
					int idx = genres.indexOf(newGenre);
					newGenre = genres.get(idx);
				}
				
				BookGenre bookGenre = new BookGenre(); // setup the associative entity
				
				bookGenre.setGenre(newGenre);
				bookGenre.setBook(book);
				
				mapping.add(bookGenre);
			}
		}
		
		book.setBookGenre(mapping);
		
		// actually update the book using the linkController
		controller.update(book);
		
		// save mapping between book and genre
		for(BookGenre bookGenre: book.getBookGenre())
		{
			controller.addBookGenre(bookGenre);
		}
		
		return "success";
	}
	
	public String execute()
	{
		Book book = controller.getBook(getBookId());
		
		if(book !=  null)
		{
			this.author = book.getAuthor();
			this.title = book.getTitle();

			bookGenre = book.getBookGenre();
			
			for(BookGenre data: bookGenre)
			{
				genre = genre + data.getGenre().getGenre() + ",";
			}
			
			this.blurb = book.getBlurb();
			this.isbn = book.getIsbn();
			
			return "update";
		}
		else
		{
			return "add";
		}
		
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBlurb() {
		return blurb;
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	public List<BookGenre> getBookGenre() {
		return bookGenre;
	}

	public void setBookGenre(List<BookGenre> bookGenre) {
		this.bookGenre = bookGenre;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;		
	}
}
