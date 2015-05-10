package books;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

// Data Transfer Object (DTO) for transferring data between our controller and our view.
public class Book
{
	private Long bookId;
	
	@NotNull
	@NotBlank
	@Size(max=50)
	private String title;

	@NotNull
	@NotBlank
	@Size(max=50)
	private String author;

	@NotNull
	@NotBlank
	@Size(max=50)
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

	public Long getBookId()
	{
		return bookId;
	}

	public String getTitle()
	{
		return title;
	}

	public String getAuthor()
	{
		return author;
	}

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
