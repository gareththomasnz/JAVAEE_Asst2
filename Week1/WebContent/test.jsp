<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.sql.*"  %>
	<%@ page import="javax.sql.*"  %>
	<%@ page import="javax.naming.*"  %>
	<%@ page import="org.hsqldb.jdbc.JDBCDataSource" %>

	<% String myName = "John";
	
		out.println("dnyamic data from JSP");
		out.println(
			myName.toUpperCase());
	
	%>

	<b>
	</b>
	<p>
	
	<%	
	
		JDBCDataSource ds = new JDBCDataSource();
		
		// setup URL according to HSQLDB specs
		ds.setUrl("jdbc:hsqldb:hsql://localhost/");
		
		// set other data source properties
		ds.setPassword("");
		ds.setUser("SA");
			
		// setup connection and query
		Connection conn = ds.getConnection();
		Statement statement = conn.createStatement();
		
		String genre = request.getParameter("genre");
		
		String querySQL = "select * from book where genre='"+genre+"'";
		
		System.out.println(querySQL);
		
		// send query to database
		ResultSet rs = statement.executeQuery(querySQL);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		%>
		<table border="1">
		<%
		// iterate through the results
		while(rs.next())
		{
			%>
			<tr>
				<%
					for(int i =1 ; i<rsmd.getColumnCount(); i++)
					{
						%>
						<td>
						<%
						out.println(rs.getString(i));
						%>
						</td>
						<%
					}
				%>
			</tr>
			<%
		}
		
		%>
		</table>
		<%
		
		// close the database resources
		rs.close();
		statement.close();
		conn.close();
	%>
	
	<form method="get" action="test.jsp">
		Genre:<input type="text" name="genre"></input>
		<input type="submit" name="Go"></input>
	</form>
	
</body>
</html>