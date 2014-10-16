package com.jcasey.model;

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
@Table(name="Genre")
@Audited
public class Genre {
	private Long id;
	private String genre;
	
	private List <BookGenre> bookGenre;

	private long version;
	
	@Id
	@GeneratedValue
	@Column(name="genre_id")
	public Long getId() {
		return id;
	}
	
	@Column (name="genre")
	public String getGenre() {
		return genre;
	}

	@OneToMany (mappedBy="genre")
	public List<BookGenre> getBookGenre() {
		return bookGenre;
	}
	@Column (name="version")
	@Version
	public long getVersion() {
		return version;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public void setBookGenre(List<BookGenre> bookGenre) {
		this.bookGenre = bookGenre;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj)
	{
		Genre other = (Genre) obj;
		return genre.equals(other.getGenre());
	}
}
