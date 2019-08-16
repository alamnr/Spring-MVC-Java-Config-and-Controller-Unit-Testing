<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Todo</title>
</head>
<body>

	<spring:url value="/todo/save" var="urlValue"></spring:url>
	<form:form action="${urlValue}" method="post" modelAttribute="todo">
	<form:errors></form:errors>
		<label>Title :</label><form:input path="title" id="todoTitle" /><form:errors path="title"/>
		<br><label>Description :</label><form:input path="description" id="todoDescription"/><form:errors path="description"/>
		<br><form:button>Submit</form:button>
		
	</form:form>

</body>
</html>