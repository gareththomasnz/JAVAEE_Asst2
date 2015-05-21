package books;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
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
	private String genre;

	public Book()
	{

	}

	public Book(Long bookId, String title, String author, String genre)
	{
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.genre = genre;
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

	@Column (name="genre")
	public String getGenre()
	{
		return genre;
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

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	@Override
	public String toString()
	{
		return "Book [bookId=" + bookId + ", title=" + title + ", author="
				+ author + ", genre=" + genre + "]";
	}
}
