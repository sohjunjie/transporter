<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Management</title>
</head>
<body>
<h1>Students Data</h1>
<form:form action="student.do" method="POST" commandName="authUser">
	<table>
		<tr>
			<td>User ID</td>
			<td><form:input path="userId" /></td>
		</tr>
		<tr>
			<td>First name</td>
			<td><form:input path="firstname" /></td>
		</tr>
		<tr>
			<td>Last name</td>
			<td><form:input path="lastname" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" name="action" value="Add" />
				<input type="submit" name="action" value="Edit" />
				<input type="submit" name="action" value="Search" />
			</td>
		</tr>
	</table>
</form:form>
<br>
<table border="1">
	<tr>
		<th>User ID</th>
		<th>First name</th>
		<th>Last name</th>
	</tr>
	<c:forEach items="${authUserList}" var="authUser">
		<tr>
			<td>${authUser.userId}</td>
			<td>${authUser.firstname}</td>
			<td>${authUser.lastname}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>