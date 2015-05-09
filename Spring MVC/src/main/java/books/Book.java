package books;

// Model object for transferring data between our controller and our view.
public class Book
{
	private Long bookId;
	private String title;
	private String author;
	private String genre;

	public Book()
	{

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
}
