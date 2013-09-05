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

Test


<table border="1" >
<tr>
<s:form id="updateAttendance" action="listAttendance" method="get" theme="simple">	
	<s:iterator value="sessions" status="status">


			<td><s:textfield id="sessions[%{#status.index}].name" name="sessions[%{#status.index}].name" theme="simple"/></td>
			<td><s:checkbox id="sessions[%{#status.index}].present" name="sessions[%{#status.index}].present" theme="simple"/></td>
			
			<sx:bind sources="sessions[%{#status.index}].present" events="onchange" formId="updateAttendance"/>

	</s:iterator>
	
	<s:submit name="save" method="execute" label="Submit" align="right" type="button"/>
	
</s:form>
</tr>
</table>
</body>
</html>
