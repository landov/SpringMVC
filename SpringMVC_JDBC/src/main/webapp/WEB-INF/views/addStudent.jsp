<%@ page language="java" contentType="text/html; charset=UTF-8 "
        pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/course.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Book Antiqua' rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Source Code Pro' rel='stylesheet'>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Course management</title>
</head>
<body>
<h1>Course management</h1>
	<ul class="topnav">
		<li><a href="/course_management/courses">Courses</a></li>
		<li><a href="/course_management/teachers">Teachers</a></li>
		<li><a href="/course_management/students">Students</a></li>
		<li><a href="/course_management/students">All persons</a></li>
	</ul>
	<c:if test="${not empty error}"> 
		<span style="color:red;">${error}<br></span> 
	</c:if> 
	<c:set var="actionPath" value="${pageContext.request.contextPath}/doAddStudent"/> 
	<form method="post" action="${actionPath}"> 
		<table> 
		<tr>
			<th colspan="3">${title}:</th>
			
		</tr>
			<tr> 
				<td>First name:</td><td><input type="text" name="firstName" value="${student.firstName}"></td> 
			</tr> 
			<tr> 
				<td>Last name:</td> <td><input type="text" name="lastName" value="${student.lastName}"> </td> 
			</tr>
			<tr> 
				<td colspan="2"> <button type="submit">Submit</button> </td> 
			</tr> 
		</table> 
		<input type="hidden" name="id" value="${student.id}">
		<!input type="hidden" name="enrolledsince" value="${student.enrolledsince}"> 
	</form>
		
</body>
</html>