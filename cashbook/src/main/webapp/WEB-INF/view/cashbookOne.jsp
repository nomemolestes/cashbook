<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%
	Cashbook cashbook = (Cashbook)request.getAttribute("cashbook");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cashbookOne</title>
<!-- 부트스트랩적용 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h2>상세보기</h2>
  	<table class="table table-striped">
		<tr>
			<td>cashDate</td>
			<td><%=cashbook.getCashDate()%></td>
		</tr>
		<tr>
			<td>kind</td>
			<td><%=cashbook.getKind() %></td>
		</tr>
		<tr>
			<td>cash</td>
			<td><%=cashbook.getCash() %></td>
		</tr>
		<tr>
			<td>memo</td>
			<td><%=cashbook.getMemo()%></td>
		</tr>
		<tr>
			<td>updateDate</td>
			<td><%=cashbook.getUpdateDate() %></td>
		</tr>
		<tr>
			<td>createDate</td>
			<td><%=cashbook.getCreateDate() %></td>
		</tr>
	</table>
		<a href="<%=request.getContextPath()%>/UpdateCashbookController?cashbookNo=<%=cashbook.getCashbookNo()%>" type ="button" >수정</a>
		<a href="<%=request.getContextPath()%>/DeleteCashbookController?cashbookNo=<%=cashbook.getCashbookNo()%>" type ="button">삭제</a>
		<a href="<%=request.getContextPath()%>/CashbookListByMonthController" type ="button" class="btn btn-secondary">리스트</a>
</body>
</html>