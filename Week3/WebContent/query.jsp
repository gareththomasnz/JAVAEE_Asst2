<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>

<title>Struts 2 - Hibernate - Book Database</title>
</head>

<body>
<h2>Struts 2 - Book Database</h2>
<s:actionerror />
<s:form action="query" method="post">

	<s:textfield name="title" key="label.title" size="20" />
	<s:textfield name="author" key="label.author" size="20" />
	<s:textfield name="genre" key="label.genre" size="20" />


	<s:submit name="search" method="execute" label="Search" align="right" type="button"/> 
	

</s:form>

<table>

<s:form action="bookAddForm" method="post">
<s:iterator value="books">

	<tr>
		<td>
			<s:property value="bookId"/>
		</td>
		<td>
			<s:property value="title"/>
		</td>
		<td>
			<s:property value="author"/>
		</td>
		<td>
			<s:property value="genre"/>
		</td>
		<td>
			<s:property value="isbn"/>
		</td>
		<td>
			<s:property value="blurb"/>
		</td>
		<td>
			<a href="delete.action?bookId=<s:property value="bookId"/>">Delete</a>
		</td>
		<td>
			Update ...
		</td>
	</tr>
</s:iterator>

	<s:submit name="add" method="execute" label="Add Record" align="left" type="button"/> 

</s:form>
</table>
</body>
</html>
