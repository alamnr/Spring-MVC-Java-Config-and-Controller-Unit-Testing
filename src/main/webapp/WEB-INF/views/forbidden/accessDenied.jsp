<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="access.denied"></spring:message></title>
</head>
<body>
<h2><spring:message code="access.denied.message.part1"></spring:message></h2>
 
<a href='<spring:url value="/todos"></spring:url>'/><spring:message code="access.denied.message.part2"></spring:message></a>

</body>
</html>