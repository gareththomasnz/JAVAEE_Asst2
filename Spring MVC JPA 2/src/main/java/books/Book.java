package books;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table (name="Book")
//@NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b where b.title like (1?)")
public class Book implements Serializable
{
	private static final long serialVersionUID = 5340045799000477703L;
	
	private Long bookId;
	private String title;
	private String author;
	private List<Genre> genres;

	public Book()
	{

	}

	public Book(Long bookId, String title, String author, List<Genre> genres)
	{
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.genres = genres;
	}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long getBookId()
	{
		return bookId;
	}

	@Column (name="title")
	public String getTitle()
	{
		return title;
	}

	@Column (name="author")
	public String getAuthor()
	{
		return author;
	}

	@ManyToMany
	@JoinTable (name="BookGenre")
	public List<Genre> getGenres()
	{
		return genres;
	}

	public void setBookId(Long bookId)
	{
		this.bookId = bookId;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public void setGenres(List<Genre> genres)
	{
		this.genres = genres;
	}

	@Override
	public String toString()
	{
		return "Book [bookId=" + bookId + ", title=" + title + ", author="
				+ author + ", genre=" + genres + "]";
	}
}
