<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Todo Update</title>
<script type="text/javascript" src='<spring:url value="/static/js/todo.form.js"></spring:url>'></script>
</head>
<body>
	 <h1><spring:message code="label.todo.update.page.title"/></h1>
    <div class="well page-content">
	<spring:url value="/todo/update" var="urlValue"></spring:url>
	<form:form action="${urlValue}" method="post" modelAttribute="todo">
		<form:hidden path="id"/>
            <div id="control-group-title" class="control-group">
                <label for="todo-title"><spring:message code="label.todo.title"/>:</label>

                <div class="controls">
                    <form:input id="todo-title" path="title"/>
                    <form:errors id="error-title" path="title" cssClass="help-inline"/>
                </div>
            </div>
            <div id="control-group-description" class="control-group">
                <label for="todo-description"><spring:message code="label.todo.description"/>:</label>

                <div class="controls">
                    <form:textarea id="todo-description" path="description"/>
                    <form:errors id="error-description" path="description" cssClass="help-inline"/>
                </div>
            </div>
            <div class="action-buttons">
                <a href='<spring:url value="/todos"></spring:url>' class="btn"><spring:message code="label.cancel"/></a>
                <button id="update-todo-button" type="submit" class="btn btn-primary"><spring:message
                        code="label.update.link"/></button>
            </div>
        </form:form>
    </div>

</body>
</html>