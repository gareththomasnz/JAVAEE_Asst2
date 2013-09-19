<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<html>
<head>
	<sj:head/>

	<title>Struts 2 - Hibernate - Book Database</title>

	
</head>

<body>
<h2>Struts 2 - Book Database</h2>

	<script>
	function ajaxUpdate(input,bookId)
	{
		$.ajax({
			  type: "POST",
			  url: "price.action",
			  data: {price: input, bookId: bookId},
			}).done(new function(){
				$("#price").val(input);				
			});
	}
	</script>

<table border="1">

<s:form id="updatePrice" action="price.action" method="POST" theme="simple"  >
<s:iterator value="books">

	<tr>
		<td>
			<s:hidden name="bookId" readonly="true"/>
			<s:property value="bookId"/>
		</td>
		<td>
			<s:property value="title"/>
		</td>
		<td>
			<s:property value="author"/>
		</td>
		<td>
			<s:iterator value="bookGenre">
				<s:property value="genre.genre"/>
			</s:iterator>
		</td>
		<td>
			<s:property value="isbn"/>
		</td>
		<td>
			<s:property value="blurb"/>
		</td>
		
		<td>
			<input name="price" type="range" min="0" max="<s:property value="maxPrice"/>" step="1" value="<s:property value="price"/>" onchange="ajaxUpdate(this.value,<s:property value="bookId"/>);"/>
		</td>
		<td>
			<s:textfield id="price" name="price" readonly="true"/>
		</td>
		<td>
			<a href="delete.action?bookId=<s:property value="bookId"/>">Delete</a>
		</td>
		<td>
			<a href="modify.action?bookId=<s:property value="bookId"/>">Modify</a>
		</td>
	</tr>
</s:iterator>

</s:form>

</table>
</body>
</html>
