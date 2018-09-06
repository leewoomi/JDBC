<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<style>
#msg {
	color: red;
}
</style>
</head>
<body>
	<!-- 로그인이 안되어 있는 경우 출력  -->
	<c:if test="${user==null}">
		<br />
		<form action="user/login" method="post" autocomplete="off">
			<div id="msg">${msg}</div>
			<label for="email">이메일</label><input type="email" name="email"
				id="email" required="required" /><br /> <label for="pw">비밀번호</label><input
				type="password" name="pw" id="pw" required="required" /><br /> <input
				type="submit" value="로그인"> <input type="button" value="회원가입"
				onclick="location.href='user/register'" /> <br />
		</form>
	</c:if>

	<!--로그인이 되어 있는 경우 -->
	<c:if test="${user!=null}">
	${user.name} 접속 중 <br />
		<a href="user/logout">로그아웃</a>
	</c:if>
</body>
</html>