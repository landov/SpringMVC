<%@ page language="java" contentType="text/html; charset=UTF-8 "
        pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/course.css"
	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Book Antiqua'
	rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Source Code Pro'
	rel='stylesheet'>
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
		<span style="color: red;">${error}<br></span>
	</c:if>
	<table>
		<tr>
			<th colspan="2">Enroll student:</th>
		</tr>
		<tr>
			<td>First name:</td>
			<td>${student.firstName}</td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td>${student.lastName}</td>
		</tr>
		<tr>
			<td>Enrolled since:</td>
			<td>${student.enrolledsince}</td>
		</tr>
		<tr>
			<th colspan="2">Courses:</th>
		</tr>

	
			<form:form modelAttribute="student"	action="${pageContext.request.contextPath}/student/doEnroll">
				
				<c:forEach items="${allCourses}" var="course">
					<tr><td>
					<label class="container">${course.name}
						<form:checkbox class="checkbox" path="courses" value="${course}" /><br>
						<!c:out value="${course.name}" />
						<span class="checkmark"></span>
					</label>
					</td></tr>										
				</c:forEach>
				<form:hidden path="id" valur="${student.id}"/>
				<tr><td><button type="submit">Update</button></td></tr>
				
			</form:form>
		</table>
				
</body>
</html>