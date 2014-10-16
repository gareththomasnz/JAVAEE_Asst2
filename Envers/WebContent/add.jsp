<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>

<title>Struts 2 - Book Database</title>
</head>

<body>
<h2>Struts 2 - Book Database</h2>
<s:actionerror />
<s:form action="save" method="post"> 

	<s:textfield name="bookId" label="Id" size="20" readonly="true" />
	<s:textfield name="title" key="label.title" size="20" />
	<s:textfield name="author" key="label.author" size="20" />
	<s:textfield name="genre" key="label.genre" size="20" />
	<s:textfield name="blurb" label="Blurb" size="20" />
	<s:textfield name="isbn" label="isbn" size="20" />

	<s:submit label="Add" align="right" type="button"/> 
</s:form>

</body>
</html>
