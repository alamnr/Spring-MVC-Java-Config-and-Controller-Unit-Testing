<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="user.register.title" /></title>
</head>
<body>

	<h1>
		<spring:message code="user.register.title" />
	</h1>
	<c:url value="/register" var="registerVar" />

	<form:form action="${registerVar}" method="post" modelAttribute="user">
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
		
		<div >
			
			<button type="submit" class="btn btn-primary">
				<spring:message code="user.register.title" />
			</button>
		</div>

	</form:form>

</body>
</html>