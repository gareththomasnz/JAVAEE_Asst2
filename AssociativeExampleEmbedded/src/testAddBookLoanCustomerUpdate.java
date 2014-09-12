import java.util.List;

import controller.BookManager;
import model.Book;
import model.Customer;
import model.Loan;


public class testAddBookLoanCustomerUpdate {
	public static void main(String[] args)
	{
		BookManager linkController = new BookManager();
		
		Book book = new Book();
		book.setAuthor("John Casey");
		book.setTitle("Book1");
		book.setGenre("Technology");
		
		book = linkController.addBook(book);
		
		Customer customer = new Customer();
		customer.setName("Jacob");
		
		customer = linkController.addCustomer(customer);
		
		Loan loan = new Loan();
		loan.setBook(book);
		loan.setCustomer(customer);
		
		loan = linkController.addLoan(loan);
		
		Book bookTest = linkController.getBook(book.getBookId());
		List<Loan> associatedLoans = bookTest.getLoans();
		
		// iterate through the book -> loan -> customer mappings
		// these mappings have been pre-loaded inside the linkController.getBook() method
		for(Loan loanTest: associatedLoans)
		{
			Customer cus = loanTest.getCustomer();
			System.out.println(loanTest.getBook().getTitle()+""+cus.getName());
		}
		
		customer.setName("Francis");
		
		linkController.updateCustomer(customer);
	}
}
