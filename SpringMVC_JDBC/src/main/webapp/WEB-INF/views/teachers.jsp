<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function canDelete (courseName) {
		return confirm("Are you sure you want to delete " + courseName + "?");
	}
</script>
<link href="${pageContext.request.contextPath}/resources/course.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Book Antiqua' rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Source Code Pro' rel='stylesheet'>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Course management - Teachers</title>
</head>
<body>
<body>
	<h1>Course management</h1>
	<ul class="topnav">
		<li><a href="/course_management/courses">Courses</a></li>
		<li><a class="active" href="/course_management/teachers">Teachers</a></li>
		<li><a href="/course_management/students">Students</a></li>
		<li><a href="/course_management/students">All persons</a></li>
	</ul>	
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach items="${teachers}" var="teacher">
			<tr>
				<td>${teacher.id}</td>
				<td>${teacher.firstName} ${teacher.lastName}
					<span class="notes"><br>${teacher.designation}</span>
				</td>
				<td>
					<a href="teacher/update/${teacher.id}">Edit</a>
				</td>
				<td>
					<a href="teacher/delete/${teacher.id}" onclick="return canDelete('${teacher.firstName}');">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<button type="submit" onclick="location.href='addTeacher'">Add Teacher</button>

</body>
</html>