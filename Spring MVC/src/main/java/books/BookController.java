package books;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
	private BookRepository bookRepo;
	
	@Autowired
    public BookController(BookRepository bookRepo) {
		super();
		this.bookRepo = bookRepo;
	}

	@RequestMapping (method=RequestMethod.GET,value="/books")
	public String home(Map<String,Object> model) {
		List<Book> books = bookRepo.findAll();
		model.put("books", books);
		return "home";
	}
	
	@RequestMapping (method=RequestMethod.POST,value="/books")
	public String save(Book book) {
		
		bookRepo.save(book);
		
		return "redirect:/books";
	}
}
