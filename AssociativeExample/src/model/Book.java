package model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Book")
public class Book implements Serializable {

	private static final long serialVersionUID = -6837174276610847586L;
	private Long bookId;
	
	private String title;
	private String author;
	private String genre;
	private List<Loan> loans; // define loans as a Java Collection

	@OneToMany
	@JoinColumn (name = "book_id") // define book_id as a foreign key in loan table
	public List<Loan> getLoans() {
		return loans;
	}
	public void setLoans(List<Loan> loan) {
		this.loans = loan;
	}
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
	@Column(name="genre")
	public String getGenre() {
		return genre;
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
	public void setGenre(String genre) {
		this.genre = genre;
	}
}