package books;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchController
{
	@Autowired
	private BookRepository bookRepo;
	
	@RequestMapping (method = RequestMethod.GET, value = "/search")
	public String search()
	{
		return "search";
	}
	
	@RequestMapping (method = RequestMethod.POST, value = "/search")
	public String listBook(Book book, Map<String, Object> model)
	{
		Iterable<Book> books = bookRepo.findTitleWildcard(book.getTitle()); //bookRepo.findByTitleIgnoreCaseOrAuthorIgnoreCaseOrGenreIgnoreCase(book.getTitle(),book.getAuthor(),book.getGenre());
		model.put("books", books);
		
		return "search";
	}
}
