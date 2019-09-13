<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>User details</title>
<script type="text/javascript"
	src='<spring:url value="/static/js/todo.view.js"></spring:url>'></script>
</head>
<body>
	<div id="user-id" class="hidden">${user.id}</div>
	<h1>
		<spring:message code="label.user.view.page.title" />
	</h1>
	<div class="well page-content">
		<h2>
			<c:out value="${user.userName}" />
		</h2>
		<div>
			<p>
				Email :
				<c:out value="${user.email}" />
			</p>
			<p>
				First Name :
				<c:out value="${user.firstName}" />
			</p>
			<p>
				Last Name :
				<c:out value="${user.lastName}" />
			</p>
			<p>
				Roles:
				<c:forEach items="${user.roles}" var="role" varStatus="count">

					<c:choose>
						<c:when test="${fn:length(user.roles) == count.index+1 }">
											${role.roleName} 
										</c:when>
						<c:otherwise>
											${role.roleName} ,
										</c:otherwise>
					</c:choose>


				</c:forEach>
			</p>
		</div>
		<br>
		<div class="action-buttons">
			<a href='<spring:url value="/users"></spring:url>'
				class="btn btn-link"><spring:message code="label.ok" /></a>
		</div>
	</div>

	<script id="template-delete-todo-confirmation-dialog"
		type="text/x-handlebars-template">
        <div id="delete-todo-confirmation-dialog" class="modal">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">×</button>
                <h3><spring:message code="label.delete.dialog.title"/></h3>
            </div>
            <div class="modal-body">
                <p><spring:message code="label.delete.dialog.message"/></p>
            </div>
            <div class="modal-footer">
                <a id="cancel-todo-button" href="#" class="btn"><spring:message code="label.cancel"/></a>
                <a id="delete-todo-button" href="#" class="btn btn-primary"><spring:message code="label.delete.link"/></a>
            </div>
        </div>
    </script>

</body>
</html>