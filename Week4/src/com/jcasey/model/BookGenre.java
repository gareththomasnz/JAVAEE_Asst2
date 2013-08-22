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
}
