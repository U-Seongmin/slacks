<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>index.jsp</h1>
	<c:catch>
		<c:choose>
			<c:when test="${empty authInfoLogin }">
				<li><a href="/biz/login">로그인</a></li>
				<li><a href="/biz/register/step2"> 회원가입</a>
				<li><a href="/biz/checkEmail">비밀번호 찾기</a>
			</c:when>
			<c:otherwise>
				<li>
					${authInfoLogin.name }님,반갑습니다!
				</li>
				<li><a href="/biz/logout">로그아웃</a></li>
			</c:otherwise>
		</c:choose>
	</c:catch>
</body>
</html>