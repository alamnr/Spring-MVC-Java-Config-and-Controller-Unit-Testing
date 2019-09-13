<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Add User</title>
<script type="text/javascript"
	src='<spring:url value="/static/js/todo.form.js"></spring:url>'></script>
</head>
<body>
	<h1>
		<spring:message code="label.user.add.page.title" />
	</h1>
	<div class="well page-content">
		<spring:url value="/user/add" var="urlValue"></spring:url>
		<form:form action="${urlValue}" method="post" modelAttribute="user">
			<div class="control-group">
				<label for="userName"><spring:message
						code="label.user.username" />:</label>

				<div class="controls">
					<form:input id="userName" path="userName" />
					<form:errors path="userName" cssClass="help-inline" />
				</div>
			</div>
			<div class="control-group">
				<label for="userPassword"><spring:message
						code="label.user.password" />:</label>

				<div class="controls">
					<form:password id="userPassword" path="password" />
					<form:errors path="password" cssClass="help-inline" />
				</div>
			</div>
			
			<div class="control-group">
				<label for="userRole"><spring:message
						code="label.user.role" />:</label>

				<div class="controls">
					<form:select id="userRole" path="roles" cssClass="selectPicker" multiple="true">
						<c:forEach items="${roleOptions}" var="item">
							<form:option value="${item.id}" label="${item.roleName}"/>
						</c:forEach>						
					</form:select>
					<form:errors path="roles" cssClass="help-inline" />
				</div>
			</div>
			
			<div class="control-group">
				<label for="userEmail"><spring:message
						code="label.user.email" />:</label>

				<div class="controls">
					<form:input id="userEmail" path="email" />
					<form:errors path="email" cssClass="help-inline" />
				</div>
			</div>
			<div class="control-group">
				<label for="userFirstName"><spring:message
						code="label.user.firstName" />:</label>

				<div class="controls">
					<form:input id="userFirstName" path="firstName" />
					<form:errors path="firstName" cssClass="help-inline" />
				</div>
			</div>
			<div class="control-group">
				<label for="userLastName"><spring:message
						code="label.user.lastName" />:</label>

				<div class="controls">
					<form:input id="userLastName" path="lastName" />
					<form:errors path="lastName" cssClass="help-inline" />
				</div>
			</div>
			<div class="action-buttons">
				<a href='<spring:url value="/users"></spring:url>' class="btn"><spring:message
						code="label.cancel" /></a>
				<button type="submit" class="btn btn-primary">
					<spring:message code="label.add.link" />
				</button>
			</div>

		</form:form>
	</div>
</body>
</html>