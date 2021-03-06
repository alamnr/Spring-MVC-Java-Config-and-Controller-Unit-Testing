<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Todo List</title>
<script type="text/javascript" src='<spring:url value="/static/js/todo.list.js"></spring:url>'></script>
</head>
<body>
	<h1>
		<spring:message code="label.todo.list.page.title" />
	</h1>
	<div>
		<a href='<spring:url value="/todo/add"></spring:url>'
			class="btn btn-primary"><spring:message
				code="label.add.link" /></a>
	</div>
	<div id="todo-list" class="well page-content">
		<c:choose>
			<c:when test="${empty todos}">
				<p>
					<spring:message code="label.list.empty" />
				</p>
			</c:when>
			<c:otherwise>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Title</th>
							<th>Description</th>
							<th>Created By</th>
							<th>Last Modified By</th>
							<th>Creation Date</th>
							<th>Last Modified date</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${todos}" var="todo" varStatus="">
							<tr>
								<td><a
									href='<spring:url value="/todo/${todo.id}"></spring:url>'>${todo.id}</a>
									
									</td>
								<td>${todo.title}</td>
								<td>${todo.description}</td>
								<td>${todo.createdBy}</td>
								<td>${todo.lastModifiedBy}</td>
								<td>${todo.createdDate}</td>
								<td>${todo.lastModifiedDate}</td>
								<td style='white-space: nowrap'><a
									href='<spring:url value="/todo/update/${todo.id}"></spring:url>'
									class="btn btn-primary btn-xs"><spring:message
											code="label.update.link" /></a> <a id="delete-link" 
									class="btn btn-primary btn-xs" onclick="openDialog(${todo.id},'todo')"><spring:message
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