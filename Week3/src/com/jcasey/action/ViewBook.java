package com.jcasey.action;

import com.jcasey.controller.BookManager;
import com.jcasey.model.Book;
import com.opensymphony.xwork2.Action;

public class ViewBook
{	
	private static final long serialVersionUID = -4016279576053983645L;

	private BookManager linkController;
	
	private Long bookId;
	private String title;
	private String author;
	private String genre;
	private String isbn;
	private String blurb;
	
	public ViewBook()
	{
		linkController = new BookManager();
	}
	
	public String add()
	{
		Book book = new Book();
		
		book.setAuthor(author);
		book.setTitle(title);
		book.setBlurb(blurb);
		book.setGenre(genre);
		book.setIsbn(isbn);
		
		linkController.add(book);
		
		return Action.SUCCESS;
	}
	
	public String update()
	{
		// get the current book
		
		Book book = linkController.get(getBookId());
		
		// update the book setters based on new form data
		book.setAuthor(author);
		
		// etc...
		
		// actually update the book using the linkController
		linkController.update(book);
		
		return "success";
	}
	
	public String execute()
	{
		Book book = linkController.get(getBookId());
		
		if(book !=  null)
		{
			this.author = book.getAuthor();
			this.title = book.getTitle();
			this.genre = book.getGenre();
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
}
