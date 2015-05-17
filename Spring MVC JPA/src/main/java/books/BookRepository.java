package books;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository // extend CrudRepository to automatically create a book repository class
public interface BookRepository extends CrudRepository<Book, Long>
{
	 Iterable<Book> findByTitle(String title);
	 Iterable<Book> findByAuthor(String author);
	 Iterable<Book> findByGenre(String genre);
	 
	 // need to follow the scheme for automatically creating queries see: http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods
	 Iterable<Book> findByTitleAndAuthorAndGenre(String title,String author,String genre);
	 
	 Iterable<Book> findByTitleIgnoreCaseOrAuthorIgnoreCaseOrGenreIgnoreCase(String title,String author,String genre);
	 
	 @Query("SELECT b FROM Book b WHERE b.title like :title")
	 public List<Book> findTitleWildcard(@Param("title") String title);
}
