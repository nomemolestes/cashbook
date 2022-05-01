<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginForm</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h2>로그인</h2>
	<form action = "<%=request.getContextPath()%>/LoginController" method="post">
 		 <table class="table table-striped">
			<tr>
				<td>memberId</td>
				<td><input type="text" name="memberId"></td>
			</tr>
			<tr>
				<td>memberPw</td>
			<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button type="submit">로그인</button>
	</form>
</body>
</html>
