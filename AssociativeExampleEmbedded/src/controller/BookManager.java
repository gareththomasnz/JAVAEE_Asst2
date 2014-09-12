package controller;

import java.util.LinkedList;
import java.util.List;

import model.Book;
import model.Customer;
import model.Loan;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;


public class BookManager extends HibernateUtil {

//	public void clearDatabase()
//	{
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		
//		Query deleteLoan = session.createQuery("delete from Loan");
//		deleteLoan.executeUpdate();
//		
//		Query deleteCustomer = session.createQuery("delete from Customer");
//		deleteCustomer.executeUpdate();
//		
//		
//		Query deleteBook = session.createQuery("delete from Book");
//		deleteBook.executeUpdate();
//		
//		session.getTransaction().commit();
//	}
	
	public Customer updateCustomer(Customer customer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(customer);
		session.getTransaction().commit();
		return customer;
	}
	
	public Book updateBook(Book book) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(book);
		session.getTransaction().commit();
		return book;
	}
	
	public Book addBook(Book book) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(book);
		session.getTransaction().commit();
		return book;
	}
	
	public Book deleteBook(Book book) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if(null != book) {
			session.delete(book);
		}
		session.getTransaction().commit();
		return book;
	}
	
	public Customer deleteCustomer(Customer customer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if(null != customer) {
			session.delete(customer);
		}
		session.getTransaction().commit();
		return customer;
	}
	
	public Loan deleteLoan(Loan loan) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if(null != loan) {
			session.delete(loan);
		}
		session.getTransaction().commit();
		return loan;
	}
	
	public Book getBook(Long id) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		LinkedList<Book> books = new LinkedList<Book>();
		try {
			Criteria criteria = session.createCriteria(Book.class);
			criteria.add(Restrictions.eq("bookId", id));
			
			books.addAll((List<Book>)criteria.list());
			
			for(Book book: books)
			{
				List<Loan> loans = book.getLoans();
				
				for(Loan loan: loans)
				{
					loan.getLoanId();
					Customer customer = loan.getCustomer();
					for(Loan loan2: customer.getLoan())
					{
						loan2.getLoanId();
					}
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		if(!books.isEmpty())
		{
			Book first = books.getFirst();
			return first;
		}
		else
		{
			return null;
		}
	}

	public List<Book> list(String genre, String title, String author) {
		
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	List<Book> books = null;
	try {
		
		Criteria criteria = session.createCriteria(Book.class);
		
		// programmatically add genre criteria to our criteria obj
		

		criteria.createCriteria("genres").add(Restrictions.eq("genre", genre));
		
		// criteria.add(Restrictions.eq("genre",genre));
		
		books = (List<Book>)criteria.list(); // get the list
		
		// do the same thing using HQL
		
//		Query query = session.createQuery("from Book b where b.genre = :genre");
//		query.setString("genre", genre);

	} catch (HibernateException e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	}
	session.getTransaction().commit();
	return books;
	}

	public Customer addCustomer(Customer customer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(customer);
		session.getTransaction().commit();
		return customer;
	}

	public Loan addLoan(Loan loan) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(loan);
		session.getTransaction().commit();
		return loan;
	}


}
