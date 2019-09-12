<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title><spring:message code="spring.test.mvc.example.title" /></title>
<link rel="stylesheet" type="text/css"
	href='<spring:url value="/static/css/example.css"></spring:url>' />
<link rel="stylesheet" type="text/css"
	href='<spring:url value="/static/css/bootstrap.css"></spring:url>' />
<link rel="stylesheet" type="text/css"
	href='<spring:url value="/static/css/bootstrap-responsive.css"></spring:url>' />
<script type="text/javascript"
	src='<spring:url value="/static/js/vendor/jquery-1.8.2.js"></spring:url>'></script>
<script type="text/javascript"
	src='<spring:url value="/static/js/vendor/bootstrap.js"></spring:url>'></script>
<script type="text/javascript"
	src='<spring:url value="/static/js/vendor/bootstrap-transition.js"></spring:url>'></script>
<script type="text/javascript"
	src='<spring:url value="/static/js/vendor/bootstrap-collapse.js"></spring:url>'></script>
<script type="text/javascript"
	src='<spring:url value="/static/js/vendor/handlebars-1.0.rc.1.js"></spring:url>'></script>
<script type="text/javascript"
	src='<spring:url value="/static/js/todo.js"></spring:url>'></script>
<sitemesh:write property="head" />
</head>
<body>
	<div class="page">
		<div class="navbar">
			<div class="navbar-inner">
				<div class="container">
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
					</a> <span class="brand"><spring:message
							code="label.navigation.brand" /></span>

					<div class="nav navbar-nav">
						<ul class="nav">
							<li><a href='<spring:url value="/"></spring:url>'><spring:message
										code="label.navigation.homepage.link" /></a></li>
							<sec:authorize url="/todos">
								<li><a href='<spring:url value="/todos"></spring:url>'><spring:message
											code="label.todo.view.page.title" /></a></li>
							</sec:authorize>

							<sec:authorize url="/users">
								<li><a href='<spring:url value="/users"></spring:url>'><spring:message
											code="label.user.view.page.title" /></a></li>
							</sec:authorize>

							<sec:authorize access="authenticated" var="authenticated" />

							<c:choose>
								<c:when test="${authenticated}">
									<li>
										<p class="navbar-text">
											Welcome!
											<sec:authentication property="name" />
											<a id="logoutLink" href="#">Logout</a>
										</p>
									</li>

									<form id="logoutForm" action='<c:url value="/logout"/>'
										method="post">
										<sec:csrfInput />
									</form>
								</c:when>
								<c:otherwise>
									<li><a href='<spring:url value="/login"/>'>Sign in</a></li>
									<li><a href='<spring:url value="/register"/>'><spring:message
												code="user.register.title" /></a></li>
								</c:otherwise>
							</c:choose>

						</ul>


						<span class="navbar-text "> <a href="?lang=en"><span>English</span></a>
							| <a href="?lang=hi"><span>Hindi</span></a>| <a href="?lang=cn"><span>Chinese</span></a>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="content">
			<div id="message-holder">
				<c:if test="${feedbackMessage != null}">
					<div class="messageblock hidden">${feedbackMessage}</div>
				</c:if>
				<c:if test="${errorMessage != null}">
					<div class="errorblock hidden">${errorMessage}</div>
				</c:if>
			</div>
			<div id="view-holder">
				<sitemesh:write property="body" />
			</div>
		</div>
	</div>
	<script>var ctx = "${pageContext.request.contextPath}"</script>
	<script id="template-alert-message-error"
		type="text/x-handlebars-template">
        <div id="alert-message-error" class="alert alert-error fade in">
            <a class="close" data-dismiss="alert">&times;</a>
            {{message}}
        </div>
    </script>

	<script id="template-alert-message" type="text/x-handlebars-template">
        <div id="alert-message" class="alert alert-success fade in">
            <a class="close" data-dismiss="alert">&times;</a>
            {{message}}
        </div>
    </script>
	<script type="text/javascript">
    	$(document).ready(()=>{
    		$("#logoutLink").click((e)=>{
    			e.preventDefault();
    			$("#logoutForm").submit();
    		})
    	})
    </script>
</body>
</html>