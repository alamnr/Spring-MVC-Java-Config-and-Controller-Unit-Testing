<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Add Todo</title>
<script type="text/javascript"
	src='<spring:url value="/static/js/todo.form.js"></spring:url>'></script>
</head>
<body>
	<h1>
		<spring:message code="label.todo.add.page.title" />
	</h1>
	<div class="well page-content">
		<spring:url value="/todo/add" var="urlValue"></spring:url>
		<form:form action="${urlValue}" method="post" modelAttribute="todo">
			<div class="control-group">
				<label for="todo-title"><spring:message
						code="label.todo.title" />:</label>

				<div class="controls">
					<form:input id="todo-title" path="title" />
					<form:errors path="title" cssClass="help-inline" />
				</div>
			</div>
			<div class="control-group">
				<label for="todo-description"><spring:message
						code="label.todo.description" />:</label>

				<div class="controls">
					<form:textarea id="todo-description" path="description" />
					<form:errors path="description" cssClass="help-inline" />
				</div>
			</div>
			<div class="action-buttons">
				<a href='<spring:url value="/todos"></spring:url>' class="btn"><spring:message
						code="label.cancel" /></a>
				<button type="submit" class="btn btn-primary">
					<spring:message code="label.add.link" />
				</button>
			</div>

		</form:form>
	</div>
</body>
</html>