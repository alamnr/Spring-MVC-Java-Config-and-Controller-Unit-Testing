<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>User Custom Login</title>
</head>
<body>
	<!-- <div class="container"> -->
	<div class="row">
		<h1>User Login</h1>
	</div>
	<c:if test="${param.error!=null}">
		<p class="error">Username or password not correct</p>
	</c:if>
	<c:if test="${param.logout!=null}">
		<p>You have successfully been logged out</p>
	</c:if>
	<c:url value="/login" var="loginUrl"></c:url>
	<form id="loginForm" action="${loginUrl}" method="post">
		<div class="form-group">
			<label for="custom_userName"><spring:message
					code="label.login.userName"></spring:message></label> <input
				name="custom_username" class="form-control">
		</div>
		<div class="form-group">
			<label for="custom_password"><spring:message
					code="label.login.password"></spring:message></label> <input
				type="password" name="custom_password" class="form-control">
		</div>
		<!-- <div class="form-group">
			<label for="custom-auth-filed">Automobile Make</label> <input
				id="custom-auth-field" name="make" class="form-control" />
		</div> -->

		<%-- <div class="form-group">
				<label for="remember"><spring:message code="label.login.rememberMe"></spring:message></label>
				<input type="checkbox" id="remember" name="remember-me"  >
			</div> --%>
		 <sec:csrfInput /> 

		<%-- <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> --%>
		 <br>
		<button type="submit" id="btn-save" class="btn btn-primary">
			<spring:message code="label.login.buttonText"></spring:message>
		</button>
	</form>
	<!-- </div> -->

</body>
</html>