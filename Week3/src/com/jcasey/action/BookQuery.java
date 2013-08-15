package com.jcasey.action;

import java.util.ArrayList;

import com.jcasey.controller.BookManager;
import com.jcasey.model.Book;
import com.opensymphony.xwork2.Action;

public class BookQuery
{	
	private BookManager linkController;
	
	public BookQuery()
	{
		linkController = new BookManager();
		books = new ArrayList<Book>();
	}
	
	private String genre;
	private String title;
	private String author;
	private Long bookId;
	
	ArrayList <Book> books;

	public String delete()
	{
		linkController.delete(getBookId());
		return Action.SUCCESS;
	}
	
	public String query()
	{
		//TODO get a list of books back from our linkController
		books.addAll(linkController.list(genre, title, author));
		
		return Action.SUCCESS;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
}
