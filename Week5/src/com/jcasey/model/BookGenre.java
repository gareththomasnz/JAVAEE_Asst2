package com.jcasey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BookGenre")
public class BookGenre {
	
	private Long id;
	private Book book;
	private Genre genre;
	
	@Id
	@GeneratedValue
	@Column(name="book_genre_id")
	public Long getId() {
		return id;
	}
	@ManyToOne
	@JoinColumn (name="book_id")
	public Book getBook() {
		return book;
	}
	
	@ManyToOne
	@JoinColumn (name="genre_id")
	public Genre getGenre() {
		return genre;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	
}
