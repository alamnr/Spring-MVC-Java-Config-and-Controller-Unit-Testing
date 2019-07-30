<%@ page language="java" contentType="text/html;  charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="styleSheet" href='<spring:url value="/resources/css/home.css"/>' type="text/css"> 
<%-- <link rel="styleSheet" href='<spring:url value="/styles/home.css"/>' type="text/css"> --%>
<title>Insert title here</title>
</head>
<body>
<p><a class="dropdown-item" href="?lang=en"><span>English</span></a> |
               <a class="dropdown-item" href="?lang=hi"><span>Hindi</span></a>|
               <a class="dropdown-item" href="?lang=cn"><span>Chinese</span></a></p>
	<h1>Home View   </h1>
	<h2><spring:message code="welcome.springmvc"/></h2>
</body>
</html>