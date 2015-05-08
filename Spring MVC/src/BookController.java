import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {
	private BookRepository bookRepo;
	
    @RequestMapping("/Book")
    @ResponseBody
    String home() {
        return "<b>Hello World!</b>";
    }
}
