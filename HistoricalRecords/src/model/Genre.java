package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name="Genre")
@Audited
public class Genre implements Serializable
{
	private static final long serialVersionUID = -6860847004834577048L;
	private Long id;
	private Book book;
	private String genre;

	@Id
	@GeneratedValue
	@Column(name="genre_id")
	public Long getId() {
		return id;
	}
	@ManyToOne (targetEntity=Book.class)
	public Book getBook() {
		return book;
	}
	@Column(name="genre")
	public String getGenre() {
		return genre;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
