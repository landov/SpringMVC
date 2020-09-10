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
	<c:set var="actionPath" value="${pageContext.request.contextPath}/doAddCourse"/> 
	<form method="post" action="${actionPath}"> 
		<table> 
		<tr>
			<th colspan="4">${title}: ${actionPath}</th>
			
		</tr>
			<tr> 
				<td>Course Name:</td><td><input type="text" name="name" value="${course.name}"></td> 
			</tr> 
			<tr> 
				<td>Credits:</td> <td><input type="text" name="credits" value="${course.credits}"> </td> 
			</tr> 
			<tr> 
				<td>Held by:</td> <td>				 
				<select id="teacherId" name="teacherId">
					<c:forEach items="${teachers}" var="teacher">
						<c:choose>
							<c:when test = "${teacher.id == course.teacherId}">
								<option value="${teacher.id}" selected>${teacher.lastName}</option>
							</c:when>
							<c:otherwise>
								<option value="${teacher.id}">${teacher.lastName}</option>
							</c:otherwise>
						</c:choose>
    				</c:forEach>
					</select>
				</td> 
			</tr> 
			<tr> 
				<td colspan="2"> <button type="submit">Submit</button> </td> 
			</tr> 
		</table> 
		<input type="hidden" name="id" value="${course.id}"> 
	</form>
		
</body>
</html>