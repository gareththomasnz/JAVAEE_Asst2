package controller;

import java.util.LinkedList;
import java.util.List;

import model.Book;
import model.Genre;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.criteria.IdentifierEqAuditExpression;

import util.HibernateUtil;


public class BookManager extends HibernateUtil {

	public Book update(Book book) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(book);
		session.getTransaction().commit();
		return book;
	}
	
	public Genre update(Genre genre) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(genre);
		session.getTransaction().commit();
		return genre;
	}
	
	public Book add(Book book) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(book);
		session.getTransaction().commit();
		return book;
	}
	
	public Genre add(Genre genre) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(genre);
		session.getTransaction().commit();
		return genre;
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
	
	public Genre delete(Genre genre) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if(null != genre) {
			session.delete(genre);
		}
		session.getTransaction().commit();
		return genre;
	}
	
	public Book delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Book book = (Book) session.load(Book.class, id);
		if(null != book) {
			session.delete(book);
		}
		session.getTransaction().commit();
		return book;
	}
	
	public List<Object[]> getHistoricalBook(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		AuditReader auditReader = AuditReaderFactory.get(session); 
		// List<Number>revisions = auditReader.getRevisions(Book.class, id); // not that useful just returns the revision numbers
		
		AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(Book.class, false, true).add(new IdentifierEqAuditExpression(id, true));
		
		List<Object[]> bookRevisions = query.getResultList();
		
		session.getTransaction().commit();
		return bookRevisions;
	}
	
	public Book get(Long id) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		LinkedList<Book> books = new LinkedList<Book>();
		try {
			Criteria criteria = session.createCriteria(Book.class);
			criteria.add(Restrictions.eq("bookId", id));
			
			books.addAll((List<Book>)criteria.list());
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		if(!books.isEmpty())
		{
			return books.getFirst();
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




}
