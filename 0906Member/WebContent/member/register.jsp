<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<h1>회원가입</h1>
<form action="insert" method="">
<label for="email">이메일</label><input type="email" name="email" id="email" required="required" /><br />
<label for="pw">비밀번호</label><input type="password" name="pw" id="pw" required="required" /><br />
<label for="name">이름</label><input type="text" name="name" id="name" /><br />
<label for="phone">전화번호</label><input type="tel" name="phone" id="phone" required="required"/><br />
<label for="addr">주소</label><input type="text" size="100" name="addr" id="addr" /><br />

<input type="submit" value="회원가입" /> 
<input type="button" value="메인으로" onclick="location.href='../'" /> <br />

</form>

</body>
</html>