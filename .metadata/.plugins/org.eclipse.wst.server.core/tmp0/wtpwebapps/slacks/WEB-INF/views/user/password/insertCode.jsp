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
	<form:form role="form" commandName="insertCode" action="/biz/checkCode"
		method="post">
		<form:input type="text" placeholder="인증 코드" path="code" />
		<form:errors path="code" />
		<button type="submit" class="btn btn-lg btn-success btn-block">인증하기</button>
	</form:form>
</body>
</html>