package test;
import controller.BookManager;
import model.Book;
import model.Customer;
import model.Loan;


public class testAddBookLoanCustomerCascadeAdd {
	public static void main(String[] args)
	{
		BookManager linkController = new BookManager();

		Book book = new Book();
		book.setAuthor("John Casey");
		book.setTitle("Java Hibernate");
		book.setGenre("Fun");
		
		Customer customer = new Customer();
		customer.setName("Peter");
		
		Loan loan = new Loan();
		
		loan.setBook(book);
		loan.setCustomer(customer);
		
		loan = linkController.addLoan(loan);
	}
}
