import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.jdbc.JDBCDataSource;
import org.hsqldb.server.Server;

public class ConsoleJDBC {
	
	public static void main(String[] args)
	{
		Server server = startServer();

		try {
			JDBCDataSource ds = new JDBCDataSource();

			// setup URL according to HSQLDB specs
			ds.setUrl("jdbc:hsqldb:hsql://localhost/books");

			// set other data source properties
			ds.setPassword("");
			ds.setUser("SA");

			// setup connection and query
			Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();

			// create the database table
			statement.execute("create table if not exists Book(book_id int primary key, title varchar(50), author varchar(50),  genre varchar(50), isbn varchar(50), blurb varchar(250))");
			
			// create a bulk insert statement
			String insertStatements = "insert into Book (BOOK_ID,TITLE, AUTHOR, GENRE, ISBN, BLURB) " +
					"values (1,'Gone with the wind','Margaret Mitchell', 'Classic','1451635621','blah blah blah');" + 

			"insert into Book (BOOK_ID,TITLE, AUTHOR, GENRE, ISBN, BLURB) " +
			"values (2,'A Clock Work Orange','Anthony Burgess','Dystopian Novella','0393312836','blah blah blah');" + 

			"insert into Book (BOOK_ID,TITLE, AUTHOR, GENRE, ISBN, BLURB) " + 
			"values (3,'2001: A Space Odyssey','Arthur C Clarke','Science Fiction','0451457994','blah blah blah');" + 

			"insert into Book (BOOK_ID,TITLE, AUTHOR, GENRE, ISBN, BLURB) " + 
			"values (4,'Make Room! Make Room!','Harry Harrison', 'Science Fiction','0765318857','blah blah blah');" + 

			"insert into Book (BOOK_ID,TITLE, AUTHOR, GENRE, ISBN, BLURB) " + 
			"values (5,'The Catcher in the Rye','JD Salinger', 'General','0316769177','blah blah blah');";
			
			// batch all updates to db at once - though we would normally do one at a time
			statement.executeUpdate(insertStatements);

			// send query to database
			ResultSet rs = statement.executeQuery("select * from Book");
			ResultSetMetaData rsmd = rs.getMetaData();

			int cols = rsmd.getColumnCount();

			// iterate through the results
			while(rs.next())
			{
				for(int i=1 ; i<cols; i++ )
				{
					System.out.println(rs.getString(i));
				}
			}
			//
			//			System.out.println(rs.getString(1));
			//			System.out.println(rs.getString(2));
			//			System.out.println(rs.getString(3));
			//			System.out.println(rs.getString(4));


			// close the database resources
			rs.close();
			statement.close();
			conn.close();

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		server.shutdown();
	}

	private static Server startServer() {
		// setup a HSQLDB Server programmatically you normally would not do this except in a unit test

		Server server = new Server();
		server.setAddress("localhost");
		server.setDatabasePath(0, "mem:books");
		server.setDatabaseName(0, "books");

		server.start();
		
		return server;
	}
}