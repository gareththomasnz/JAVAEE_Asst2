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
		session.saveOrUpdate(book);
		return book;
	}

	public Book addBook(Book book) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.save(book);
		return book;
	}

	public BookGenre addBookGenre(BookGenre bookGenre) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.save(bookGenre);
		return bookGenre;
	}

	public Genre addGenre(Genre genre) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.save(genre);
		return genre;
	}

	public BookGenre deleteBookGenre(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		BookGenre bookGenre = (BookGenre) session.load(BookGenre.class, id);
		if (null != bookGenre) {
			session.delete(bookGenre);
		}
		return bookGenre;
	}

	public Book deleteBook(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Book book = (Book) session.load(Book.class, id);
		if (null != book) {
			session.delete(book);
		}
		return book;
	}

	public Book getBook(Long id) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		LinkedList<Book> books = new LinkedList<Book>();
		try {
			Criteria criteria = session.createCriteria(Book.class);
			criteria.add(Restrictions.eq("bookId", id));

			books.addAll((List<Book>) criteria.list());

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		if (!books.isEmpty()) {
			return books.getFirst();
		} else {
			return null;
		}
	}

	public Genre getGenre(Long id) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		LinkedList<Genre> genres = new LinkedList<Genre>();
		try {
			Criteria criteria = session.createCriteria(Genre.class);
			criteria.add(Restrictions.eq("id", id));

			genres.addAll((List<Genre>) criteria.list());

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		if (!genres.isEmpty()) {
			return genres.getFirst();
		} else {
			return null;
		}
	}

	public LinkedList<Genre> listGenres() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		LinkedList<Genre> genres = new LinkedList<Genre>();
		try {
			Criteria criteria = session.createCriteria(Genre.class);
			genres.addAll((List<Genre>) criteria.list());

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return genres;

	}

	public List<Book> list(String genre, String title, String author) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		List<Book> books = null;
		try {

			Criteria criteria = session.createCriteria(Book.class);
			books = (List<Book>) criteria.list(); // get the list

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return books;
	}

}
