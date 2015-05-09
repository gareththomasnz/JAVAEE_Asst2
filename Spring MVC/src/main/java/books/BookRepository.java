package books;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
	private JdbcTemplate jdbc;



	@Autowired
	public BookRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}



	public List<Book> findAll()
	{
		return jdbc.query(
				"select book_id,title,author,genre " +
						"from Book order by title",
						new RowMapper<Book>() {
					public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
						Book book = new Book();
						book.setBookId(rs.getLong(1));
						book.setTitle(rs.getString(2));
						book.setAuthor(rs.getString(3));
						book.setGenre(rs.getString(4));

						return book;
					}
				}
				);

	}

	public void save(Book book)
	{		
		jdbc.update("insert into book " +
				"(title,author,genre) " +
				"values (?, ?, ?)",
				book.getTitle(), book.getAuthor(),book.getGenre());


	}
}
