package com.jcasey.controller;


import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.jcasey.model.Book;
import com.jcasey.util.HibernateUtil;

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
		
		if(StringUtils.isNotBlank(genre))
		{
			criteria.add(Restrictions.eq("genre", genre));
		}
		if(StringUtils.isNotBlank(title))
		{
			criteria.add(Restrictions.eq("title", title));
		}
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
