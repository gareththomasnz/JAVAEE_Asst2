package com.jcasey.action;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jcasey.controller.Manager;
import com.jcasey.model.Book;
import com.jcasey.model.BookGenre;
import com.jcasey.model.Genre;
import com.opensymphony.xwork2.Action;

public class ModifyBook
{	
	private Manager controller;
	
	private Long bookId;
	private String title;
	private String author;
	private String genre;
	private String isbn;
	private String blurb;
	
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
		book.setIsbn(isbn);
		
		//TODO split incoming data up based on commas
		
		List <BookGenre> mapping = new LinkedList <BookGenre>();
		
		//TODO delete any previous mappings - and recreate them later (yes a little bit nasty).
		
		controller.addBook(book); // save the book record
		
		//TODO save mapping between book and genre
		for(BookGenre bookGenre: book.getBookGenre())
		{
			controller.addBookGenre(bookGenre);
		}
		
		return Action.SUCCESS;
	}
	
	public String delete()
	{
		Book book = controller.getBook(getBookId());
		
		//TODO delete the dependent objects first		
		//TODO delete the primary book record

		return Action.SUCCESS;
	}
	
	public String update()
	{
		// get the current book
		Book book = controller.getBook(getBookId());
		
		// update the book setters based on new form data
		book.setAuthor(author);
		
		//TODO split incoming data up based on commas
		String genreData[] = StringUtils.split(genre, ",");
		
		if(genreData.length >0)
		{
			//TODO delete associations - a bit of a nasty way to do things...
		
		}
		List <BookGenre> mapping = new LinkedList <BookGenre>();
		final LinkedList <Genre> genres = controller.listGenres(); // get a list of genres already in the database
		
		
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
			
			//TODO split genre data up for the view
			for(BookGenre data: bookGenre)
			{

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
}
