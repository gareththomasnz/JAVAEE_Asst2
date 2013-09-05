<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<html>
<head>
<head>
    <sx:head debug="true" cache="false" compressed="false" />
<title>Struts 2 AJAX Example</title>
</head>
<body>

<s:form id="toggleForm" action="toggle" method="post">
	<s:checkbox id="toggleCheckbox" name="toggle" value="toggle" >Test</s:checkbox>
	<sx:bind sources="toggleCheckbox" events="onchange" formId="toggleForm"/>

	<s:submit name="toggleAction" method="execute" label="Submit" align="right" type="button"/> 
</s:form>
</body>
</html>
