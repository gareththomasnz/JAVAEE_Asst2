<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>

<title>Struts 2 - Book Database</title>
</head>

<body>
<h2>Struts 2 - Book Database</h2>
<s:actionerror />
<s:form action="save.action" method="post">

	<s:textfield name="bookId" label="Id" size="20" readonly="true" />


	<s:submit name="Add" label="Add" align="right" type="button"/> 
</s:form>

</body>
</html>
