package books;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Genre {
	Long id;
	String name;
	String description;
	List<Book> books;
	
	public Genre(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	@Column
	public String getName() {
		return name;
	}
	@Column
	public String getDescription() {
		return description;
	}
	
	@ManyToMany
	@JoinTable (name="BookGenre")
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + ", description="
				+ description + ", books=" + books + "]";
	}

}
