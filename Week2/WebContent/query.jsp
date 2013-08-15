<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>

<title>Struts 2 - Book Database</title>
</head>

<body>
<h2>Struts 2 - Book Database</h2>

<s:actionerror />
<s:form action="bookQuery" method="post">

	<s:textfield name="title" key="label.title" size="20" />
	<s:textfield name="author" key="label.author" size="20" />
	<s:textfield name="genre" key="label.genre" size="20" />

	<s:submit method="execute" key="label.query" align="center" /> 
</s:form>

<table>
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
	</tr>
</s:iterator>
</table>
</body>
</html>
