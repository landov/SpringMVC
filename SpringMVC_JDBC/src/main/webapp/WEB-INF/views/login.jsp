<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="${pageContext.request.contextPath}/resources/course.css"
    rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

<body ><h1>Welcome to course management!</h1>
	<h2 style="text-align:right">Please login!</h2>

	<form id="loginForm" method="POST">
	<table style="float: right">
		<tr><td><label for="userName">User name:</label></td><td><input type="text" name="userName" required="required" value="${user.userName}"></td></tr> 
		<tr><td><label for="password">Password:</label></td><td><input type="password" name="password"></td></tr>
		<tr class="error"><td colspan="2">
			<c:if test="${user.messageExists()}">
				<h6> ${user.message}</h6>
			</c:if></td></tr>
		<tr><td></td><td>
			<button type="submit" style="float: right">Submit</button>
		</td></tr>
	</table>
	</form><br>
		

</body>
</html>