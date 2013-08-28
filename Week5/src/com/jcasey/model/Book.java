package com.jcasey.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Book")
public class Book implements Serializable
{
	private static final long serialVersionUID = -6837174276610847586L;
	private Long bookId;
	private String title;
	private String author;
	
	private List<BookGenre> bookGenre;
	
	private String isbn;
	private String blurb;

	@Id
	@GeneratedValue
	@Column(name="book_id")
	public Long getBookId() {
		return bookId;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	@Column(name="author")
	public String getAuthor() {
		return author;
	}
	@OneToMany (mappedBy="book")
	public List<BookGenre> getBookGenre() {
		return bookGenre;
	}
	@Column(name="isbn")
	public String getIsbn() {
		return isbn;
	}
	@Column(name="blurb")
	public String getBlurb() {
		return blurb;
	}
	
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setBookGenre(List<BookGenre> genre) {
		this.bookGenre = genre;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}
}