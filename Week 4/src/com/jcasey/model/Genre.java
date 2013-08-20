package com.jcasey.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Genre")
public class Genre
{
	private Long id;
	private String genre;

}
