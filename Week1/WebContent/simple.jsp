<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<b>Hello World!</b>

	<% 
		String myName = "John Casey";
	
		int age = 17;
	%>
	
	<%!
		public int helloWorld()
		{
			// anything else ... ?
		
			return 500;
		}
	%>
	
	<%=
		helloWorld()
	%>
	
	
	
	other data<br>
	<%
		
	
	
		if(age >= 18)
		{
			out.println("would you like a beer?");
		}
		else
		{
			out.println("would you like a cordial?");
		}
		out.println("test"+myName);
		out.println("<p>");
		
		out.println("This is a new paragraph isn't it lovely.");
	%>
	

</body>
</html>