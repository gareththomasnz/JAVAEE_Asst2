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
		<%= request.getParameter("genre") %>
	</b>
	<p>
	<% String firstname = "fred"; %>
	
	<%= firstname.toUpperCase() %>
	
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
		
		out.println("genre"+genre);
		
		// send query to database
		ResultSet rs = statement.executeQuery("select * from book");
		ResultSetMetaData rsmd = rs.getMetaData();
		
		// iterate through the results
		while(rs.next())
		{
			out.println(rs.getString(2));
		}
		
		// close the database resources
		rs.close();
		statement.close();
		conn.close();
	%>
	
	<form method="get" action="demo.jsp">
		Genre:<input type="text" name="genre"></input>
		<input type="submit" name="Go"></input>
	</form>
	
</body>
</html>