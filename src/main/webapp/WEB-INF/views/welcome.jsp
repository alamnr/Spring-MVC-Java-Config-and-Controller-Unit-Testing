<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to  our App</title>
</head>
<body>
<h2>Welcome to our Todo App.</h2>

<sec:authorize access="isAuthenticated()">
	<p>Welcome back <sec:authentication property="principal.firstName"/> <sec:authentication property="principal.lastName"/></p>
</sec:authorize>

</body>
</html>