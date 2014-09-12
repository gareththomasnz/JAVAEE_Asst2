package test;
import java.util.List;

import controller.BookManager;
import model.Book;
import model.Customer;
import model.Loan;


public class testAddBookLoanCustomer {
	public static void main(String[] args)
	{
		BookManager linkController = new BookManager();
		
		Book book = new Book();
		book.setAuthor("John Casey");
		book.setTitle("Book1");
		book.setGenre("Technology");
		
		Customer customer = new Customer();
		customer.setName("Jacob");
		
		Loan loan = new Loan();
		loan.setBook(book);
		loan.setCustomer(customer);
		
		book = linkController.addBook(book);
		customer = linkController.addCustomer(customer);
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
		
		for(Loan loanTest: associatedLoans)
		{
			Customer cus = loanTest.getCustomer();
			System.out.println(loanTest.getBook().getTitle()+""+cus.getName());
			
			System.err.println(cus.getLoan().size()); // The inverse getLoan() mapping is not loaded so this will fail with a LazyInitializationException
		}
	}
}
