import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.jdbc.JDBCDataSource;

public class ConsoleJDBC {
	public static void main(String[] args)
	{
		try {
			JDBCDataSource ds = new JDBCDataSource();
			
			// setup URL according to HSQLDB specs
			ds.setUrl("jdbc:hsqldb:hsql://localhost/");
			
			// set other data source properties
			ds.setPassword("");
			ds.setUser("SA");
			
			// setup connection and query
			Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();
			
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
	}
}