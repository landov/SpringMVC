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
	<table> 
		<tr>
			<th colspan="2">Course details:</th>
			
		</tr>
			<tr> 
				<td>Course Name:</td><td>${course.name}</td> 
			</tr> 
			<tr> 
				<td>Credits:</td> <td>${course.credits}</td> 
			</tr>
			<tr> 
				<td>Held by:</td> <td>${course.teacherName}</td> 
			</tr>  
			<tr> 
				<th colspan="2">Enrolled students:</th>
			</tr> 
			<c:forEach items="${students}" var="student">
				<tr><td = colspan="2">
					${student.firstName} ${student.lastName}
				</td></tr>
			</c:forEach>
	</table> 		
</body>
</html>