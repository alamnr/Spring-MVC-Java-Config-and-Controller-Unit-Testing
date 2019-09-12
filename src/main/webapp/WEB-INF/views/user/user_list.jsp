<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>User List</title>
<script type="text/javascript" src='<spring:url value="/static/js/todo.list.js"></spring:url>'></script>
</head>
<body>
	<h1>
		<spring:message code="label.user.list.page.title" />
	</h1>
	<div>
		<a href='<spring:url value="/user/add"></spring:url>'
			class="btn btn-primary"><spring:message
				code="label.add.link" /></a>
	</div>
	<div id="user-list" class="well page-content">
		<c:choose>
			<c:when test="${empty users}">
				<p>
					<spring:message code="label.list.empty" />
				</p>
			</c:when>
			<c:otherwise>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>User Name</th>
							<th>Email</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Roles</th>
							
							
							<th>Created By</th>
							<th>Last Modified By</th>
							<th>Creation Date</th>
							<th>Last Modified date</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user" varStatus="">
							<tr>
								<td><a
									href='<spring:url value="/user/${user.id}"></spring:url>'>${user.id}</a>
									
									</td>
								<td>${user.userName}</td>
								<td>${user.email}</td>
								<td>${user.firstName}</td>
								<td>${user.lastName}</td>
								<td></td>
								<td>${todo.createdBy}</td>
								<td>${todo.lastModifiedBy}</td>
								<td>${todo.createdDate}</td>
								<td>${todo.lastModifiedDate}</td>
								<td style='white-space: nowrap'><a
									href='<spring:url value="/user/update/${user.id}"></spring:url>'
									class="btn btn-primary btn-xs"><spring:message
											code="label.update.link" /></a> <a id="delete-link" 
									class="btn btn-primary btn-xs" onclick="openDialog(${user.id},'user')"><spring:message
											code="label.delete.link" /></a></td>
							</tr>
						</c:forEach>

					</tbody>

				</table>
			</c:otherwise>
		</c:choose>
		</div>
		 <script id="template-delete-confirmation-dialog" type="text/x-handlebars-template">
        <div id="delete-confirmation-dialog" class="modal">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">×</button>
                <h3><spring:message code="label.delete.dialog.title"/></h3>
            </div>
            <div class="modal-body">
                <p><spring:message code="label.delete.dialog.message"/></p>
            </div>
            <div class="modal-footer">
                <a id="cancel-button" href="#" class="btn"><spring:message code="label.cancel"/></a>
                <a id="delete-button" href="#" class="btn btn-primary"><spring:message code="label.delete.link"/></a>
            </div>
        </div>
    </script>
</body>
</html>