package com.jcasey;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.opensymphony.xwork2.Action;


public class BookQuery {
	private static final String BOOK_ID = "BOOK_ID";
	private static final String TITLE = "TITLE";
	private static final String AUTHOR = "AUTHOR";
	private static final String GENRE = "GENRE";
	private static final  String ISBN = "ISBN";
	private static final  String BLURB = "BLURB";
	
	private String genre;
	private String title;
	private String author;

	ArrayList <Book> books = new ArrayList<Book>();

	class Book
	{
		public Book() {

		}
		private int bookId;
		private String title;
		private String author;
		private String genre;
		private String isbn;
		private String blurb;
		
		public int getBookId() {
			return bookId;
		}
		public void setBookId(int bookId) {
			this.bookId = bookId;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getGenre() {
			return genre;
		}
		public void setGenre(String genre) {
			this.genre = genre;
		}
		public String getIsbn() {
			return isbn;
		}
		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}
		public String getBlurb() {
			return blurb;
		}
		public void setBlurb(String blurb) {
			this.blurb = blurb;
		}
	}
	
	public String query()
	{		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			System.out.println(genre);
			System.out.println(title);
			System.out.println(author);

			JDBCDataSource ds = new JDBCDataSource();
			
			// setup URL according to HSQLDB specs
			ds.setUrl("jdbc:hsqldb:hsql://localhost/");
			
			// set other data source properties
			ds.setPassword("");
			ds.setUser("SA");

			
			conn = ds.getConnection();
			stmt = conn.createStatement();
			
			String querySQL = "select * from book"; 
			
			if(StringUtils.isNotBlank(title) || StringUtils.isNotBlank(author) || StringUtils.isNotBlank(genre))
			{
				querySQL = querySQL + " where";
			}
			
			if(StringUtils.isNotBlank(title))
			{
				querySQL = querySQL + " title ='"+title+"'";
			}
			
			
			System.err.println(querySQL);
			
			rs = stmt.executeQuery(querySQL);
			
			// build up the output from the result set
			// keep iterating through the result set while another row exists
			
			while(rs.next() == true)
			{
				Book book = new Book();
				
				book.setBookId(rs.getInt(BOOK_ID));
				book.setTitle(rs.getString(TITLE));
				book.setAuthor(rs.getString(AUTHOR));
				book.setGenre(rs.getString(GENRE));
				book.setIsbn(rs.getString(ISBN));
				book.setBlurb(rs.getString(BLURB));
				
				books.add(book);
			}
			
			return Action.SUCCESS;
		}
		catch (SQLException e)
		{
			return Action.ERROR;
		}
		finally
		{

			if(rs != null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null)
			{
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
}
