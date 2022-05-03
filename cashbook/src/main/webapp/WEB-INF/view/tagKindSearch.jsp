<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tagKindSearh</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<%
		List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");//콘트롤러 setAttribute
	%>
		<h2>수입/지출에 따른 태그검색</h2>
		<form action="<%=request.getContextPath()%>/tagKindSearchController" method="get">
			<table>
				<tr>
					<td>kind</td>
					<td>
						<input type="radio" name="kind" value="수입">수입
						<input type="radio" name="kind" value="지출">지출
					</td>
					<td>
						<button type="submit">검색</button>
					</td>	
				</tr>
			</table>
		</form>
		<h2>년도별 검색</h2>
			<form action="<%=request.getContextPath()%>/TagDateSearchController" method="get">
				<table>
					<tr>
						<td>year</td>
						<td>
							<input type="date" name="cashDate">
						</td>
						<td>
							<button type="submit">검색</button>
						</td>
					</tr>
				</table>	
			</form>
			<table>
				<tr>
					<th>tag</th>
					<th>cnt</th>
					<th>rank</th>
				</tr>
				<%
					for(Map<String, Object> map : list) {
				%>
					<tr>
						<td><%=map.get("tag") %></td>
						<td><%=map.get("cnt") %></td>
						<td><%=map.get("rank") %></td>
					</tr>
				<%		
					}
				%>
			</table>
</body>
</html>