import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.jdbc.JDBCDataSource;
import org.hsqldb.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class JDBCTest {

	Server server = null;
	Connection conn = null;

	@Before
	public void setUp() throws Exception {
		// setup a HSQLDB Server programmatically you normally would not do this except in a unit test

		server = new Server();
		server.setAddress("localhost");
		server.setDatabasePath(0, "mem:contacts");
		server.setDatabaseName(0, "contacts");

		server.start();

		JDBCDataSource ds = new JDBCDataSource();

		// setup URL according to HSQLDB specs
		ds.setUrl("jdbc:hsqldb:hsql://localhost/contacts");

		// set other data source properties
		ds.setPassword("");
		ds.setUser("SA");

		// setup global database connection		
		conn = ds.getConnection();
	}

	@After
	public void tearDown() throws Exception {
		
		conn.close();
		server.shutdown();
	}

	@Test
	public void testDeleteRecord()
	{
		try
		{
			Statement statement = conn.createStatement();
			statement.execute("create table if not exists contact (name varchar(50), mobile varchar(50),  email varchar(50))");
			statement.executeUpdate("delete from contact");
			statement.execute("insert into contact values ('Thomas','021123123','jcasey@unitec.ac.nz')");
			
			ResultSet rs = statement.executeQuery("select name from contact");
			
			boolean result = rs.next();
			
			assert(result);
			
			if(result)
			{
				String name = rs.getString("name");
				assertEquals("Names do not match something went wrong","Thomas",name);
			}
			
			result = rs.next();
			
			assert(result == false);

			int rowsUpdated = statement.executeUpdate("delete from contact");
			
			assert(rowsUpdated == 1);
						
			rs = statement.executeQuery("select name from contact");
			
			result = rs.next();
			
			assert(result == false);			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
			fail(e.getMessage());
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	@Test
	public void testUpdateRecord()
	{
		try
		{
			Statement statement = conn.createStatement();
			statement.execute("create table if not exists contact (name varchar(50), mobile varchar(50),  email varchar(50))");
			statement.execute("delete from contact");
			statement.execute("insert into contact values ('Thomas','021123123','jcasey@unitec.ac.nz')");
			
			ResultSet rs = statement.executeQuery("select name from contact");
			
			boolean result = rs.next();
			
			assert(result);
			
			if(result)
			{
				String name = rs.getString("name");
				assertEquals("Names do not match something went wrong","Thomas",name);
			}
			
			result = rs.next();
			
			assert(result == false);
			
			int rowsUpdated = statement.executeUpdate("update contact set name='Gordon' where name = 'Thomas'");
			
			assert(rowsUpdated == 1);
						
			rs = statement.executeQuery("select name from contact");
			
			result = rs.next();
			
			assert(result);
			
			if(result)
			{
				String name = rs.getString("name");
				assertEquals("Names do not match something went wrong, name NOT updated","Gordon",name);
			}
			
			result = rs.next();
			
			assert(result == false);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
			fail(e.getMessage());
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testQueryRecords()
	{
		try
		{
			Statement statement = conn.createStatement();
			statement.execute("create table if not exists contact (name varchar(50), mobile varchar(50),  email varchar(50))");
			statement.execute("delete from contact");
			statement.execute("insert into contact values ('Toby','021123123','jcasey@unitec.ac.nz')");
			
			ResultSet rs = statement.executeQuery("select name from contact where name = 'Toby'");
			
			boolean result = rs.next();
			
			if(result)
			{
				String name = rs.getString("name");
				assertEquals("Names do not match something went wrong","Toby",name);
			}
			
			result = rs.next();
			
			assert(result == false);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
			fail(e.getMessage());
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testQueryMetaDataRecords()
	{
		try
		{
			Statement statement = conn.createStatement();
			statement.execute("create table if not exists contact (name varchar(50), mobile varchar(50),  email varchar(50))");
			statement.execute("delete from contact");
			statement.execute("insert into contact values ('Toby','021123123','jcasey@unitec.ac.nz')");
			
			ResultSet rs = statement.executeQuery("select name from contact where name = 'Toby'");			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			assert(rsmd.getColumnCount() == 1);
			
			assert(rsmd.getColumnClassName(0).equals("name"));
			
			boolean result = rs.next();
			
			if(result)
			{
				String name = rs.getString("name");
				assertEquals("Names do not match something went wrong","Toby",name);
			}
			
			result = rs.next();
			
			assert(result == false);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
			fail(e.getMessage());
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testInsertRecords()
	{
		try
		{
			Statement statement = conn.createStatement();
			statement.execute("create table if not exists contact (name varchar(50), mobile varchar(50),  email varchar(50))");
			statement.execute("insert into contact values('James','021123123','jcasey@unitec.ac.nz')");
			
			ResultSet rs = statement.executeQuery("select name from contact");
			
			while(rs.next())
			{
				assertEquals("Names do not match something went wrong",rs.getString("name"),"James");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
			fail(e.getMessage());
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testInsertRecordsPreparedStatement()
	{
		try
		{
			Statement statement = conn.createStatement();
			statement.execute("create table if not exists contact (name varchar(50), mobile varchar(50),  email varchar(50))");
			
			String insert = "insert into contact values(?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(insert);
			
			ps.setString(1, "James");
			ps.setString(2, "021123123");
			ps.setString(3, "jcasey@unitec.ac.nz");
			
			int rowsInserted = ps.executeUpdate();
			
			assert(rowsInserted == 1);
			
			ResultSet rs = statement.executeQuery("select name from contact");
			
			while(rs.next())
			{
				assertEquals("Names do not match something went wrong",rs.getString("name"),"James");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
			fail(e.getMessage());
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	@Test
	public void testCreateDatabase()
	{
		try
		{
			Statement statement = conn.createStatement();
			statement.execute("create table if not exists contact (name varchar(50), mobile varchar(50),  email varchar(50))");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			fail();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
