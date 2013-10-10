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

import org.hibernate.envers.Audited;

@Entity
@Table(name="Book")
@Audited
public class Book implements Serializable {

	private static final long serialVersionUID = -6837174276610847586L;
	private Long bookId;
	
	private String title;
	private String author;
	
	private List<Genre>genres;

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
	public List<Genre> getGenres() {
		return genres;
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
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
}