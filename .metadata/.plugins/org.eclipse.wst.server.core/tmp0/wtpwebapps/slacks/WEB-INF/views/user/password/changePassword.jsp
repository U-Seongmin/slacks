<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form role="form" commandName="changePassword"
		action="/biz/changePassword" method="post">
		<form:password class="form-control" placeholder="Password"
			path="password" />
		<form:errors path="password" />
		<form:password class="form-control" placeholder="Password Check"
			path="checkPw" />
		<form:errors path="checkPw" />
		<button type="submit" class="btn btn-default">변경하기</button>
	</form:form>
</body>
</html>