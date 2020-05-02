<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function canDelete (studentName) {
		return confirm("Are you sure you want to delete " + studentName + "?");
	}
</script>
<link href="${pageContext.request.contextPath}/resources/course.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Book Antiqua' rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Source Code Pro' rel='stylesheet'>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Course management - Student</title>
</head>
<body>
<body>
	<h1>Course management</h1>
	<ul class="topnav">
		<li><a href="/course_management/courses">Courses</a></li>
		<li><a href="/course_management/teachers">Teachers</a></li>
		<li><a class="active" href="/course_management/students">Students</a></li>
		<li><a href="/course_management/students">All persons</a></li>
	</ul>	
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th colspan="3"></th>
			
		</tr>
		<c:forEach items="${students}" var="student">
			<tr>
				<td>${student.id}</td>
				<td>${student.firstName} ${student.lastName}
					<span class="notes"><br>Enrolled since: ${student.enrolledsince}</span>
				</td>
				<td>
					<a href="student/update/${student.id}">Edit..</a>
				</td>
				<td>
					<a href="student/delete/${student.id}" 
					onclick="return canDelete('Student ${student.firstName} ${student.lastName} with id: ${student.id}');">Delete..</a>
				</td>
				<td>
					<a href="student/enroll/${student.id}">Enroll to course..</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<button type="submit" onclick="location.href='addStudent'">Add Student</button>

</body>
</html>