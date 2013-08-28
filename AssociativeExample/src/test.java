import java.util.List;

import model.Book;
import model.Customer;
import model.Loan;
import controller.BookManager;


public class test {
	public static void main(String[] args)
	{
		BookManager linkController = new BookManager();
		
		Book book = new Book();
		book.setAuthor("John Casey");
		book.setTitle("Book1");
		book.setGenre("Technology");
		
		book = linkController.add(book);
		
		Customer customer = new Customer();
		customer.setName("Jacob");
		
		customer = linkController.addCustomer(customer);
		
		Loan loan = new Loan();
		loan.setBook(book);
		loan.setCustomer(customer);
		
		loan = linkController.addLoan(loan);
		
		Book test = linkController.get(book.getBookId());
		List<Loan> loans = test.getLoans();
		
		for(Loan loanTest: loans)
		{
			Customer cus = loanTest.getCustomer();
			System.out.println(loanTest.getBook().getTitle()+""+cus.getName());
			
			cus.getLoan().size();
		}
	}
}
