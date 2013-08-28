package controller;

import java.util.LinkedList;
import java.util.List;

import model.Book;
import model.Customer;
import model.Loan;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;


public class BookManager extends HibernateUtil {

	public Book update(Book book) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(book);
		session.getTransaction().commit();
		return book;
	}
	
	public Book add(Book book) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(book);
		session.getTransaction().commit();
		return book;
	}
	
	public Book delete(Book book) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if(null != book) {
			session.delete(book);
		}
		session.getTransaction().commit();
		return book;
	}
	
	public Book get(Long id) {
		
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
