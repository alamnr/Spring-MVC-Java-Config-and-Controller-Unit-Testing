<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Todo Update</title>
</head>
<body>

	<spring:url value="/todo/update" var="urlValue"></spring:url>
	<form:form action="${urlValue}" method="post" modelAttribute="todo">
		<label>Title :</label><form:input path="title" id="todoTitle" /><form:errors path="title"/>
		<br><label>Description :</label><form:textarea path="description" id="todoDescription"/><form:errors path="description"/>
		<br><form:button>Submit</form:button>
		 <%-- <a href='<spring:url value="/todo/${todo.id}"></spring:url>'>Cancel</a> --%>
		 <a href='<spring:url value="/"></spring:url>'>Cancel</a>
	
	</form:form>

</body>
</html>