<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<div class="panel-body">
		<form:form role="form" commandName="loginCommand" action="/biz/login"
			method="post">
			<fieldset>
				<div class="form-group">
					<form:input type="email" class="form-control" placeholder="Email"
						path="email" />
					<form:errors path="email" />
				</div>
				<div class="form-group">
					<form:password class="form-control" placeholder="Password"
						path="pw" />
					<form:errors path="pw" />
				</div>
				<button type="submit" class="btn btn-lg btn-success btn-block">로그인</button>
			</fieldset>
		</form:form>
	</div>
</body>
</html>