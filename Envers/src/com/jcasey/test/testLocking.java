package com.jcasey.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.criteria.internal.IdentifierEqAuditExpression;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jcasey.controller.Manager;
import com.jcasey.model.Book;
import com.jcasey.model.BookGenre;
import com.jcasey.model.Genre;
import com.jcasey.util.HibernateUtil;

public class testLocking {

	@After
	public void after()
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		sf.getCurrentSession().close();
	}
	
	@Before
	public void before()
	{
		Configuration configuration = new Configuration();
		configuration.setProperty(Environment.DRIVER, "org.hsqldb.jdbcDriver");
		configuration.setProperty(Environment.URL, "jdbc:hsqldb:mem:test;shutdown=true");
		configuration.setProperty(Environment.USER, "SA");
		configuration.setProperty(Environment.PASS, "");
		configuration.setProperty(Environment.POOL_SIZE, "10");
		configuration.setProperty(Environment.SHOW_SQL, "true");
		configuration.setProperty(Environment.DIALECT, "org.hibernate.dialect.HSQLDialect");
		configuration.setProperty(Environment.AUTOCOMMIT, "false"); //"true");
		configuration.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		configuration.setProperty(Environment.HBM2DDL_AUTO, "update");
		
		configuration.addAnnotatedClass(Book.class);
		configuration.addAnnotatedClass(Genre.class);
		configuration.addAnnotatedClass(BookGenre.class);
		
		HibernateUtil.setConfiguration(configuration);
		HibernateUtil.getSessionFactory();
	}

	@Test
	public void testAuditRecords()
	{
		Manager manager = new Manager();

		// create book record
		Book book = new Book();
		book.setTitle("Gone with the wind");
		
		book = manager.addBook(book);
		
		book.setAuthor("Margaret Mitchell");
		book = manager.update(book);
		
		book.setTitle("Gone With The Wind");
		book = manager.update(book);
		
		manager.deleteBook(book.getBookId());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		AuditReader auditReader = AuditReaderFactory.get(session); 
		AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(Book.class, false /* complete revision data: if true just return Book objects */, true /* return deleted entities */).add(new IdentifierEqAuditExpression(book.getBookId(), true));
		
		List<Object[]> bookRevisions = query.getResultList();
		
		assertTrue("The entity does not have 4 revision records",bookRevisions.size() == 4);
		
		for(Object[] auditData : bookRevisions)
		{
			Book oldBook = (Book) auditData[0];
			
			System.out.print(oldBook);
			
			DefaultRevisionEntity revData = (DefaultRevisionEntity) auditData[1];
			
			System.out.print(" "+revData);
			
			RevisionType revType = (RevisionType) auditData[2];

			System.out.println(" "+revType);
		}
		
		session.getTransaction().commit();
	}

	@Test
	public void testOptimisticLocking()
	{
		Manager manager = new Manager();

		// create book record
		Book book1 = new Book();
		book1.setTitle("Gone");

		// persist
		book1 = manager.addBook(book1);

		// update the title in memory
		book1.setTitle("Gone with the wind");
		
		// load original record
		Book book2 = manager.getBook(book1.getBookId());
		
		// update the title in memory
		book2.setTitle("Test");

		// persist book2
		manager.update(book2);

		// attempt to persist out of date book1
		try
		{
			manager.update(book1);
			fail("Test failed: Concurrent update of book, StaleObjectStateException not thrown when it should be.");
		}
		catch(StaleObjectStateException e)
		{
			assertEquals( "com.jcasey.model.Book", e.getEntityName());
		}
	}
}
