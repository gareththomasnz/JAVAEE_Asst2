package com.jcasey.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Genre")
public class Genre {
	private Long id;
	private String genre;
	
	private List <BookGenre> bookGenre;

	@Id
	@GeneratedValue
	@Column(name="genre_id")
	public Long getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		Genre other = (Genre) obj;
		return genre.equals(other.getGenre());
	}
}
