package com.jcasey.controller;


import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.jcasey.model.Book;
import com.jcasey.model.BookGenre;
import com.jcasey.model.Genre;
import com.jcasey.util.HibernateUtil;

public class Manager extends HibernateUtil {

	public Book update(Book book) {
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
	public BookGenre addBookGenre(BookGenre bookGenre) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(bookGenre);
		session.getTransaction().commit();
		return bookGenre;
	}
	
	public Genre addGenre(Genre genre) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(genre);
		session.getTransaction().commit();
		return genre;
	}
	
	public BookGenre deleteBookGenre(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		BookGenre bookGenre = (BookGenre) session.load(BookGenre.class, id);
		if(null != bookGenre) {
			session.delete(bookGenre);
		}
		session.getTransaction().commit();
		return bookGenre;
	}
	
	public Book deleteBook(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Book book = (Book) session.load(Book.class, id);
		if(null != book) {
			session.delete(book);
		}
		session.getTransaction().commit();
		return book;
	}

	public Book getBook(Long id) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		LinkedList<Book> books = new LinkedList<Book>();
		try {
			Criteria criteria = session.createCriteria(Book.class);
			criteria.add(Restrictions.eq("bookId", id));
			
			books.addAll((List<Book>)criteria.list());
			
			// TODO load related records
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
	
	public Genre getGenre(Long id) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		LinkedList<Genre> genres = new LinkedList<Genre>();
		try {
			Criteria criteria = session.createCriteria(Genre.class);
			criteria.add(Restrictions.eq("id", id));
			
			genres.addAll((List<Genre>)criteria.list());
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		if(!genres.isEmpty())
		{
			return genres.getFirst();
		}
		else
		{
			return null;
		}
	}


	public LinkedList <Genre> listGenres() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		LinkedList<Genre> genres = new LinkedList<Genre>();
		try {
			Criteria criteria = session.createCriteria(Genre.class);
			genres.addAll((List<Genre>)criteria.list());
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		

		return genres;

	}
	
	public List<Book> list(String genre, String title, String author) {
		
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	List<Book> books = null;
	try {
		
		Criteria criteria = session.createCriteria(Book.class);
		books = (List<Book>)criteria.list(); // get the list
		
		//TODO load related objects / records
		} catch (HibernateException e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	}
	session.getTransaction().commit();
	return books;
	}

}
