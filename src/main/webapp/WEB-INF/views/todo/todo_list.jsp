<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Todo List</title>
</head>
<body>
<h2><a href='<spring:url value="/todo/add"></spring:url>'>Add Todo</a></h2>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Title</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todoList}" var="todo">
				<tr>
					<td><a href='<spring:url value="/todo/${todo.id}"></spring:url>'>${todo.id}</a></td>
					<td>${todo.title}</td>
					<td>${todo.description}</td>
				</tr>
			</c:forEach>

		</tbody>

	</table>

</body>
</html>