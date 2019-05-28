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
	<form:form role="form" commandName="checkEmail" action="/biz/sendMail"
		method="post">
		<form:input type="text" placeholder="Email" path="email" />
		<form:errors path="email" />
		<br>
		<button type="submit" class="btn btn-lg btn-success btn-block">인증번호
			받기</button>
	</form:form>
</body>
</html>