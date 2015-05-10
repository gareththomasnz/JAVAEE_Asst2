package books;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // defines BookController class as a Spring MVC Controller component
public class BookController
{
	private BookRepository bookRepo;

	@Autowired // inject BookRepository dependency
	public BookController(BookRepository bookRepo)
	{
		super();
		this.bookRepo = bookRepo;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/books") // handle get requests that are mapped to /books
	public String home(Map<String, Object> model) // the model object in Spring MVC is a Map with a key,value pair we could also use a org.springframework.ui.Model object
	{
		List<Book> books = bookRepo.findAll();
		model.put("books", books);
		return "home"; // name of the view
	}

	@RequestMapping(method = RequestMethod.POST, value = "/books") // handle post requests that are mapped to /books
	public String save(Book book)
	{
		
		bookRepo.save(book);

		return "redirect:/books"; // redirect POST request so that we don't submit the form twice
	}
}
