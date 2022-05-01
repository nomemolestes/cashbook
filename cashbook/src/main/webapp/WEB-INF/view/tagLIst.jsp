<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import = "java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tagList</title>
</head>
<body>
	<%
		List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
	%>
		<h2>tag lank</h2>
		<div>수입/지출별 검색</div>
		<div>날짜별 검색</div>
		<table border="1">
			<tr>
				<td>rank</td>
				<td>tag</td>
				<td>count</td>
			</tr>
	<%
		for(Map<String, Object> map : list) {
	%>
			<tr>
				<td><%=map.get("rank")%></td>
				<td><a href=""><%=map.get("tag")%></a></td>
				<td><%=map.get("cnt")%></td>
			</tr>
	<%			
		}
	%>
		</table>
</body>
</html>