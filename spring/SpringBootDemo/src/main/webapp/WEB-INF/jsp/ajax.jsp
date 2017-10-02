<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
			<c:if test="${users!=null}">

			<h2>All User Display</h2>
			<table border="1" width="60%">

				<tr>
					<th>Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>email</th>
					<th>Mobile No</th>
					<th>edit</th>
					<th>Delete</th>

				</tr>
				<c:if test="${user_list!=null}">
				<c:forEach items="${user_list}" var="user_lt">
					<tr>
						<td><c:out value="${user_lt.getId()}" /></td>
						<td><c:out value="${user_lt.getFirstname()}" /></td>
						<td><c:out value="${user_lt.getLastname()}" /></td>
						<td><c:out value="${user_lt.getEmail()}" /></td>
						<td><c:out value="${user_lt.getMobile()}" /></td>
						<td><a
							href="<spring:url value="edit_user?id=${user_lt.getId()}"/>">Edit</a>
						</td>
						<td><a href="<spring:url value='/delete_user?id=${user_lt.getId()}' />">Delete</a></td>
					</tr>
				</c:forEach>
				</c:if>

				<tr>

					<td colspan="7" align="center">Login Here :- <a
						href="<spring:url value="/index" />"> Login Here</a>

					</td>
				</tr>
				
			</table>
		</c:if>
		
		</center>
</body>
</html>