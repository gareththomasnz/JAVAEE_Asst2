package com.jcasey.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

@Entity
@Table(name="Book")
@Audited
public class Book implements Serializable
{
	private static final long serialVersionUID = -6837174276610847586L;
	private Long bookId;
	private String title;
	private String author;
	
	private List<BookGenre> bookGenre;
	
	private String isbn;
	private String blurb;
	private long version;

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
	@Column (name="version")
	@Version
	public long getVersion() {
		return version;
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
	public void setVersion(long version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author="
				+ author + ", bookGenre=" + bookGenre + ", isbn=" + isbn
				+ ", blurb=" + blurb + ", version=" + version + "]";
	}	
}